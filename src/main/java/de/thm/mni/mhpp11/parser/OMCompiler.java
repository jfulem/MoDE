package de.thm.mni.mhpp11.parser;

import de.thm.mni.mhpp11.parser.modelica.ComponentsLexer;
import de.thm.mni.mhpp11.parser.modelica.ComponentsParser;
import de.thm.mni.mhpp11.util.ImmutableListCollector;
import javafx.util.Pair;
import lombok.Getter;
import omc.corba.OMCClient;
import omc.corba.OMCInterface;
import omc.corba.Result;
import omc.corba.ScriptingHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by hobbypunk on 27.09.16.
 */
public class OMCompiler {
  
  private static final Pattern lineEnd = Pattern.compile(";\\s*$");
  private static final Pattern removeQuotes = Pattern.compile("(^\")|(\"$)");
  private static final Pattern connect = Pattern.compile("^\\s*connect\\s*\\(.*");
  private static OMCompiler ourInstance;
  
  public static OMCompiler getInstance(Path compiler, Path library, Locale locale) throws IOException, IllegalStateException {
    if (ourInstance == null) ourInstance = new OMCompiler(compiler, library, locale);
    return ourInstance;
  }
  
  public static OMCompiler getInstance() {
    return ourInstance;
  }
  
  public enum TYPE {
    TYPE,
    PACKAGE,
    CLASS,
    RECORD,
    FUNCTION,
    MODEL,
    CONNECTOR,
    ENUM,
    OPERATOR,
    OPERATOR_RECORD,
    NULL
  }
  
  private final Path library;
  private OMCInterface client;
  
  private Lock lock = new ReentrantLock();
  private Condition systemLibs;
  
  private List<Pair<String, Path>> systemLibraries = new ArrayList<>();
  @Getter private List<Pair<String, Path>> projectLibraries = new ArrayList<>();
  @Getter private Pair<String, Path> project = null;
  
  private OMCompiler(Path compiler, Path library, Locale locale) throws IOException, IllegalStateException {
    this.library = library;
    client = new OMCClient(compiler.toString(), locale.toString());
    client.connect();
    this.preloadSystemLibraries();
  }
  
  private void preloadSystemLibraries() {
    Thread t = new Thread(() -> {
      lock.lock();
      systemLibs = lock.newCondition();
      List<String> list = OMCompiler.this.getAvailableLibraries();
      for (String s : list) {
        sendExpression(String.format("loadModel(%s)", s), true);
      }
      loadSystemLibraries(list);
      Condition s = systemLibs;
      systemLibs = null;
      s.signal();
      lock.unlock();
    });
    t.start();
  }
  
  public void setProject(Path f) throws ParserException {
    if (this.project != null) throw new ParserException("project already set");
    Pair<String, Path> lib = addLibrary(f);
    if (lib == null) throw new ParserException("project not loaded");
    this.project = lib;
  }
  
  private Pair<String, Path> addLibrary(Path f) throws ParserException {
    f = f.toAbsolutePath().normalize();
    loadSystemLibraries(null); // if not loaded now;
    
    Result r = sendExpression(String.format("loadFile(\"%s\")", f));
    if (r.result.contains("false")) throw new ParserException("Cannot load file");
    if (r.error.isPresent()) throw new ParserException(r.error.get());
    
    r = sendExpression("getLoadedLibraries()");
    
    List<Pair<String, Path>> list = toLibraryArray(r.result);
    
    for (Pair<String, Path> entry : list) {
      if (entry.equals(project)) continue;
      if (systemLibraries.contains(entry) || projectLibraries.contains(entry)) continue;
      if (!f.startsWith(entry.getValue())) continue;
      return new Pair<>(entry.getKey(), f);
    }
    return null;
  }
  
  public void addProjectLibraries(List<Path> files) throws ParserException {
    for (Path f : files) {
      Pair<String, Path> p = addLibrary(f);
      if (p != null) this.projectLibraries.add(p);
    }
  }
  
  private List<String> getAvailableLibraries() {
//    Result result = client.sendExpression("getAvailableLibraries()");
//    List<String> list = toStringArray(result.result);
    String[] first = new String[]{"Modelica", "ModelicaServices", "ModelicaReference", "Complex"};
//    list.removeAll(Arrays.asList(first));
//    list.addAllAnnotations(0, Arrays.asList(first));
//    return list;
    return Arrays.asList(first);
  }
  
  private void loadSystemLibraries(List<String> list1) {
    if (list1 == null) waitLibs();
    if (systemLibraries.isEmpty()) {
      Result result = sendExpression("getLoadedLibraries()", true);
      
      List<Pair<String, Path>> list = toLibraryArray(result.result);
      for (Pair<String, Path> entry : list) {
        String name = entry.getKey().toLowerCase();
        if (name.contains("obsolete") || name.contains("test")) continue;
        if (this.library != null && entry.getValue().startsWith(this.library))
          systemLibraries.add(entry);
      }
      systemLibraries.sort((o1, o2) -> list1.indexOf(o1.getKey()) - list1.indexOf(o2.getKey()));
    }
  }
  
  public List<Pair<String, Path>> getSystemLibraries() {
    waitLibs();
    return this.systemLibraries;
  }
  
  public List<String> getAnnotationStrings(String className) {
    List<String> list = new ArrayList<>();
    Result r = sendExpression(String.format("getAnnotationCount(%s)", className));
    Integer count = Integer.parseInt(r.result);
    for (Integer i = 1; i <= count; i++) {
      r = sendExpression(String.format("getNthAnnotationString(%s, %d)", className, i));
      if (!r.error.isPresent()) list.add(toString(r.result));
    }
    
    return list;
  }
  
  public List<String> getChildren(String className) {
    Result result = sendExpression(String.format("getClassNames(%s)", className));
    return toStringArray(result.result);
  }
  
  public List<String> getInheritedClasses(String className) {
    Result result = sendExpression(String.format("getInheritedClasses(%s)", className));
    return toStringArray(result.result);
  }
  
  public ClassInformation getClassInformation(String className) {
    Result r = sendExpression(String.format("getClassInformation(%s)", className));
    List<String> list = toStringArray(r.result, true, false);
    TYPE t;
    try {
      t = TYPE.valueOf(list.get(0).replaceAll(" ", "_").toUpperCase());
    } catch (Exception e) {
      t = TYPE.NULL;
    }
  
    return new ClassInformation(
                                   t,
                                   list.get(1),
                                   getPath(list.get(5)),
                                   Boolean.parseBoolean(list.get(6)),
                                   Integer.parseInt(list.get(7)),
                                   Integer.parseInt(list.get(9)),
                                   Integer.parseInt(list.get(8)),
                                   Integer.parseInt(list.get(10)));
  }
  
  public List<Map<String, String>> getVariables(String className) throws IOException {
    return getVariables(className, getClassInformation(className));
  }
  
  public List<Map<String, String>> getVariables(String className, ClassInformation ci) throws IOException {
    List<Map<String, String>> list = getComponents(className);
    for (Map<String, String> m : list) {
      Pattern regex = Pattern.compile(String.format("%s(\\[.*?\\])? %s[\\W]", m.get("type"), m.get("name")));
      
      try (Stream<String> lines = Files.lines(ci.getFileName())) {
        lines.limit(ci.getLineNumberEnd()).skip(ci.getLineNumberStart() - 1).filter(new Predicate<String>() {
          Boolean match = false;
          
          @Override
          public boolean test(String s) {
            Boolean alwaysReturn = false;
            if (regex.matcher(s).find()) alwaysReturn = match = true;
            if (lineEnd.matcher(s).find()) {
              if (match) alwaysReturn = true;
              match = false;
            }
            return alwaysReturn || match;
          }
        }).map(String::trim).reduce((s, s2) -> s + s2).ifPresent(s -> {
          m.put("line", s);
          if (s.contains("annotation")) m.put("annotation", s.substring(s.indexOf("annotation(")));
        });
      }
    }
    
    return list;
  }
  
  public List<Map<String, String>> getComponents(String className) throws IOException {
    List<Map<String, String>> list = new ArrayList<>();
    Result r = sendExpression(String.format("getComponentsTest(%s)", className));
    ANTLRInputStream is;
    ComponentsParser p;
    is = new ANTLRInputStream(new ByteArrayInputStream(r.result.getBytes()));
    p = new ComponentsParser(new CommonTokenStream(new ComponentsLexer(is)));
    ComponentsParser.ComponentsContext cs = p.components();
    for (ComponentsParser.ComponentContext c : cs.component()) {
      Map<String, String> m = new HashMap<>();
      list.add(m);
      m.put("type", toString(c.className().val.getText()));
      m.put("name", toString(c.name().val.getText()));
      m.put("comment", toString(c.comment().val.getText()));
      m.put("isProtected", toString(c.isProtected().val.getText()));
      m.put("isFinal", toString(c.isFinal().val.getText()));
      m.put("isFlow", toString(c.isFlow().val.getText()));
      m.put("isStream", toString(c.isStream().val.getText()));
      m.put("isReplaceable", toString(c.isReplaceable().val.getText()));
      m.put("variability", toString(c.variability().val.getText()));
      m.put("innerOuter", toString(c.innerOuter().val.getText()));
      m.put("inputOutput", toString(c.inputOutput().val.getText()));
      m.put("dimensions", toString(c.dimensions().val.getText()));
    }
    
    return list;
  }
  
  public List<Map<String, String>> getConnections(String className) {
    return getConnections(getClassInformation(className));
  }
  
  public List<Map<String, String>> getConnections(ClassInformation ci) {
    try (Stream<String> lines = Files.lines(ci.getFileName())) {
      return lines.limit(ci.getLineNumberEnd()).skip(ci.getLineNumberStart() - 1).filter(new Predicate<String>() {
        Boolean match = false;
        
        @Override
        public boolean test(String s) {
          Boolean alwaysReturn = false;
          if (connect.matcher(s).matches()) alwaysReturn = match = true;
          if (s.endsWith(";")) {
            if (match) alwaysReturn = true;
            match = false;
          }
          return alwaysReturn || match;
        }
      }).filter(s -> s.contains("annotation")).map(String::trim).map(s -> {
        Map<String, String> map = new HashMap<>();
        Pattern p = Pattern.compile("connect\\(\\s*([^,\\)\\s]+)\\s*,\\s*([^,\\)\\s]+)\\s*\\)\\s");
        Matcher m = p.matcher(s);
        if (m.find()) {
          map.put("from", m.group(1));
          map.put("to", m.group(2));
          map.put("annotation", m.replaceAll(""));
        }
        return Collections.unmodifiableMap(map);
      }).filter(map -> !map.isEmpty()).collect(ImmutableListCollector.toImmutableList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return Collections.unmodifiableList(new ArrayList<>());
  }
  
  private String toString(String result) {
    return removeQuotes.matcher(result).replaceAll("");
  }
  
  public void disconnect() {
    waitLibs();
    try {
      client.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  private List<String> toStringArray(String s) {
    return toStringArray(s, true, true);
  }
  
  private List<String> toStringArray(String s, Boolean unsorted, Boolean removeEmpty) {
    List<String> l = ScriptingHelper.fromNestedArray(s);
    Stream<String> stream = l.stream();
    stream = stream.map(OMCompiler.this::toString);
  
    if (removeEmpty) stream = stream.filter(s1 -> !s1.isEmpty());
    if (!unsorted) stream = stream.sorted(String::compareToIgnoreCase);
  
    return stream.collect(ImmutableListCollector.toImmutableList());
  }
  
  private List<Pair<String, Path>> toLibraryArray(String s) {
    List<String> tmp = toStringArray(s);
    List<Pair<String, Path>> list = new ArrayList<>();
    for (int i = 0; i < tmp.size(); i += 2) {
      list.add(new Pair<>(tmp.get(i), Paths.get(tmp.get(i + 1))));
    }
    return list;
  }
  
  public Path getPath(String path) {
    Result r = sendExpression(String.format("uriToFilename(\"%s\")", path));
    return Paths.get(toStringArray(r.result).get(0));
  }
  
  public Result sendExpression(String s) {
    return sendExpression(s, false);
  }
  
  private Result sendExpression(String s, Boolean ignoreLoaded) {
    if (!ignoreLoaded)
      waitLibs();
    Result r = client.sendExpression(s);
    return r;
  }
  
  private void waitLibs() {
    lock.lock();
    try {
      if (systemLibs != null) {
        systemLibs.await();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    lock.unlock();
  }
}