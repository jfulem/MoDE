package org.jmodelica.modelica.compiler;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Locale;
import org.jmodelica.util.BinaryOperation;
import org.jmodelica.util.collections.ConstArrayIterator;
import org.jmodelica.util.collections.SingleIterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.LinkedHashSet;
import java.util.Set;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.math.BigInteger;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.DigestOutputStream;
import java.security.NoSuchAlgorithmException;
import org.jmodelica.util.streams.StreamGobbler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.jmodelica.util.logging.StreamingLogger;
import org.jmodelica.util.logging.Level;
import org.jmodelica.util.AbstractModelicaScanner;
import java.io.ByteArrayOutputStream;
import org.jmodelica.util.streams.CodeStream;
import org.jmodelica.util.streams.NotNullCodeStream;
import java.util.TreeSet;
import org.jmodelica.util.ErrorCheckType;
import org.jmodelica.util.problemHandling.SimpleProblemProducer;
import org.jmodelica.util.problemHandling.SimpleWarningProducer;
import org.jmodelica.util.problemHandling.SimpleErrorProducer;
import org.jmodelica.util.problemHandling.ComplianceFMUOnlyProducer;
import java.util.LinkedList;
import org.jmodelica.util.collections.ListMap;
import org.jmodelica.util.collections.LinkedHashListMap;
import org.jmodelica.api.problemHandling.Problem;
import org.jmodelica.api.problemHandling.ProblemSeverity;
import org.jmodelica.api.problemHandling.ProblemKind;
import org.jmodelica.util.problemHandling.ReporterNode;
import org.jmodelica.util.problemHandling.ProblemOptionsProvider;
import org.jmodelica.util.Criteria;
import org.jmodelica.util.problemHandling.ErrorProducerUnlessDisabled;
import org.jmodelica.util.problemHandling.AbstractErrorProducerUnlessDisabled;
import org.jmodelica.util.collections.ReverseListIterable;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import org.jmodelica.util.Enumerator;
import java.util.Stack;
import org.jmodelica.util.collections.FilteredIterator;
import java.util.SortedSet;
import java.util.Comparator;
import java.io.*;
import org.jmodelica.util.OptionRegistry;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.jmodelica.util.collections.ChainedIterator;
import org.jmodelica.util.formattedPrint.FormattingInfo;
import org.jmodelica.util.formattedPrint.FormattingItem;
import org.jmodelica.util.formattedPrint.EmptyFormattingItem;
import org.jmodelica.util.formattedPrint.FormattingLocator;
import org.jmodelica.util.formattedPrint.DefaultFormattingItem;
import org.jmodelica.util.formattedPrint.ScannedFormattingItem;
import beaver.Symbol;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.IdentityHashMap;
import org.jmodelica.util.streams.NullStream;
import org.jmodelica.util.problemHandling.SanityProblemProducer;
import java.util.EnumSet;
import org.jmodelica.util.collections.ParallelIterable;
import org.jmodelica.util.munkres.DenseMunkresProblem;
import org.jmodelica.util.munkres.IntegerCost;
import java.util.BitSet;
import org.jmodelica.util.exceptions.BLTException;
import org.jmodelica.util.collections.ChainedIterable;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.Random;
import org.jmodelica.util.collections.HashStack;
import org.jmodelica.util.exceptions.MunkresException;
import org.jmodelica.util.exceptions.IndexReductionException;
import org.jmodelica.util.munkres.SparseMunkresProblem;
import org.jmodelica.util.munkres.MunkresProblem;
import org.jmodelica.util.munkres.MunkresCost;
import org.jmodelica.util.Solvability;
import java.lang.reflect.Constructor;
import org.jmodelica.util.XMLUtil;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import org.jmodelica.util.test.GenericTestCase;
import org.jmodelica.util.test.Assert;
import org.jmodelica.util.CompiledUnit;
import org.jmodelica.util.logging.units.LoggingUnit;
import org.jmodelica.util.test.GenericTestSuite;
import org.jmodelica.util.test.TestTree;
import org.jmodelica.util.AbstractFlatModelicaScanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jmodelica.util.streams.CStringCodeStream;
import org.jmodelica.util.exceptions.CompilerException;
import java.util.Deque;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.InterruptedException;
import java.lang.StringBuilder;
import java.nio.channels.FileChannel;
import java.util.Hashtable;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.jmodelica.util.logging.ModelicaLogger;
import org.jmodelica.util.logging.MemoryLogger;
import org.jmodelica.util.OptionRegistry.UnknownOptionException;
import org.jmodelica.util.Arguments;
import org.jmodelica.util.SymbolValueFixer;
import org.jmodelica.util.exceptions.ModelicaException;
import org.jmodelica.util.exceptions.InternalCompilerError;
import org.jmodelica.util.MemorySpider;
import org.jmodelica.util.exceptions.IllegalCompilerArgumentException;
import org.jmodelica.util.exceptions.ModelicaClassNotFoundException;
import org.jmodelica.util.exceptions.PackingFailedException;
import org.jmodelica.util.exceptions.CcodeCompilationException;
import org.jmodelica.util.problemHandling.ProblemProducer;
import org.jmodelica.util.PassAndForget;
import org.jmodelica.util.logging.IllegalLogStringException;
import org.jmodelica.util.streams.CloneOutputStream;
import org.xml.sax.SAXException;
/**
 * @ast class
 * @declaredat /home/hobbypunk/Dokumente/Entwicklung/THM_Projekte/Projektphase/jModelica/Compiler/GenericCodeGen/src/jastadd/AbstractGenerator.jrag:76
 */
public abstract class AbstractGenerator extends Object {
    private  void instantiateTags(FClass fclass) {
		AbstractTag.instantiateTags(getClass(), tagMap, this, this, fclass);
	}

    public  char escapeChar() {
	    return (char) escapeCharacter;
	}

    public  void addHookTag(AbstractTag tag) {
		String name = tag.getName();
		Set<AbstractTag> set = hookTagMap.get(name);
		if (set == null) {
			set = new LinkedHashSet<AbstractTag>();
			hookTagMap.put(name, set);
		}
		if (!set.contains(tag))
			set.add(tag);
	}

    public  void removeHookTag(AbstractTag tag) {
		String name = tag.getName();
		Set<AbstractTag> set = hookTagMap.get(name);
		if (set != null) {
			set.remove(tag);
			if (set.isEmpty())
				hookTagMap.remove(set);
		}
	}

    public  void generate(String templateFile, String outputFile) {
        try {
            generate(templateFile, new FileOutputStream(outputFile), false, "");
        } catch (IOException e) { // TODO: introduce specific exception type
            throw new RuntimeException("File I/O problem during code generation", e);
        }
    }

    public  void generate(String templateFile, OutputStream output, boolean autoFlush, String header) {
        try {
            CodeStream outStream = new NotNullCodeStream(new PrintStream(output, autoFlush, "UTF-8"));
            outStream.print(header);
            if (templateFile == null) {
                generate(null, outStream, null);
            } else {
                File tFile = new File(templateFile);
                Reader template = openTemplate(tFile);
                generate(template, outStream, tFile.getParentFile());
                template.close();
            }
            outStream.close();
        } catch (IOException e) { // TODO: introduce specific exception type
            throw new RuntimeException("File I/O problem during code generation", e);
        }
    }

    private  Reader openTemplate(File file) throws FileNotFoundException {
	    return new BufferedReader(new FileReader(file));
	}

    protected  String startOfBlurb() { return null; }

    protected  String endOfBlurb() {
		String end = startOfBlurb();
		if (end == null)
			return null;
		StringBuilder buf = new StringBuilder(end);
		buf.reverse();
		return buf.toString();
	}

    public  void generate(Reader templateReader, CodeStream genPrinter) {
        generate(templateReader, genPrinter, null);
    }

    public  void generate(Reader templateReader, CodeStream genPrinter, File includeDir) {
		Mode mode = Mode.BEFORE;

		final String startStr = startOfBlurb();
		final String endStr = endOfBlurb();
		if (startStr == null || endStr == null)
			mode = Mode.NORMAL;
		
		StringBuilder tag_name = new StringBuilder();
		int i = 0;
		boolean read = true;
		try {
			int c = templateReader.read();
			while (c != -1) {
				switch (mode) {
				case NORMAL:
					if (c == escapeCharacter) {
						mode = Mode.TAG;
						tag_name.delete(0, tag_name.length());
					} else {
						genPrinter.print((char)c);
					}
					break;
					
				case TAG:
					if (c == escapeCharacter) {
					    mode = Mode.NORMAL;
					    String name = tag_name.toString();
					    if (name.startsWith(HOOK_PREFIX)) {
					    	name = name.substring(HOOK_PREFIX.length());
					    	Set<AbstractTag> set = hookTagMap.get(name);
					    	if (set != null)
					    		for (AbstractTag tag : set)
					    			tag.generateTag(genPrinter);
                        } else if (name.startsWith(INCLUDE_PREFIX)) {
                            if (includeDir == null)
                                throw new IllegalArgumentException("Can't include template file without include dir.");
                            name = name.substring(INCLUDE_PREFIX.length()).trim();
                            File subTemplateFile = new File(includeDir, name);
                            generate(openTemplate(subTemplateFile), genPrinter, includeDir);
					    } else {
					        getTag(name).generateTag(genPrinter);
					    }
					} else {
						tag_name.append((char) c);
					}
					break;
					
				case BEFORE:
					if (c == startStr.charAt(0)) {
						mode = Mode.START;
						i = 1;
					} else {
						mode = Mode.NORMAL;
						read = false;
					}
					break;
					
				case START:
					if (c == startStr.charAt(i)) {
						if (++i >= startStr.length())
							mode = Mode.BLURB;
					} else {
						mode = Mode.NORMAL;
						genPrinter.print(startStr.substring(0, i));
						read = false;
					}
					break;
					
				case BLURB:
					if (c == endStr.charAt(0)) {
						mode = Mode.END;
						i = 1;
					}
					break;
					
				case END:
					// TODO: Does not handle the case endStr = "-->", blurb ends with "--->"
					//       This can be solved with a pre-calculated prefix table
					if (c == endStr.charAt(i)) {
						if (++i >= endStr.length())
							mode = Mode.END_WS;
					} else {
						mode = Mode.BLURB;
					}
					break;
					
				case END_WS:
					if (!Character.isWhitespace((char) c)) {
						mode = Mode.NORMAL;
						read = false;
					}
					break;
				}
				
				if (read)
					c = templateReader.read();
				else
					read = true;
			}
		} catch (IOException e) { // TODO: introduce specific exception type
			throw new RuntimeException("File I/O problem during code generation", e);
		}
	}

    protected  AbstractTag getTag(String name) {
        AbstractTag tag = tagMap.get(name);
        if (tag != null)
            return tag;
        else // TODO: introduce specific exception type
            throw new RuntimeException("Unknown tag: " + name);
	}

    public  String toString() {
		StringBuilder str = new StringBuilder();
		for (AbstractTag t : tagMap.values()) {
			str.append(t.toString()+"\n");
		}
		return str.toString();
	}

    public static final  String HOOK_PREFIX = "HOOK__";

    public static final  String INCLUDE_PREFIX = "INCLUDE:";

    protected  Map<String,AbstractTag> tagMap;

    protected  Map<String,Set<AbstractTag>> hookTagMap;

    protected  Printer expPrinter;

    protected  int escapeCharacter;

    public  AbstractGenerator(Printer expPrinter, char escapeCharacter, FClass fclass) {
		this.expPrinter = expPrinter;
		this.escapeCharacter = (int)escapeCharacter;
		tagMap = new HashMap<String,AbstractTag>();
		hookTagMap = new HashMap<String,Set<AbstractTag>>();
		instantiateTags(fclass);
	}

    private  enum Mode { NORMAL, TAG, START, BLURB, END, END_WS, BEFORE }


}
