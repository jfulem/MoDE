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
 * @aspect Connections
 * @declaredat /home/hobbypunk/Dokumente/Entwicklung/THM_Projekte/Projektphase/jModelica/Compiler/ModelicaFrontEnd/src/jastadd/flattening/Connections.jrag:630
 */
public class ConnectionSetManager extends Object {
    
	
		private ArrayList<ConnectionSet> list = new ArrayList<ConnectionSet>();

    
		private ArrayList<EqualityConstraintConnection> equalityConstraints = new ArrayList<EqualityConstraintConnection>();

    
		private Map<ConnectionSetEntry, ConnectionSet> cseMap = new HashMap<ConnectionSetEntry, ConnectionSet>();

    
		private Map<String, CSENameMapEntry> cseStreamMap = new HashMap<String, CSENameMapEntry>();

    
		private OverconstrainedConnectionGraph graph = null;

    
		private ExpandableConnectorSets expandable = null;

    
		
        private static class CSENameMapEntry {
            private ConnectionSetEntry outsideCSE = null;
            private ConnectionSet outside = null;
            private ConnectionSetEntry insideCSE = null;
            private ConnectionSet inside = null;
        }

    

        private void updateCSEMapEntry(ConnectionSetEntry cse, ConnectionSet newValue) {
            cseMap.put(cse, newValue);
            if (cse.isStream()) {
                for (String name : cse.names()) {
                    CSENameMapEntry entry = cseStreamMap.get(name);
                    if (entry == null) {
                        entry = new CSENameMapEntry();
                        cseStreamMap.put(name, entry);
                    }
                    if (cse.isOutside()) {
                        entry.outsideCSE = cse;
                        entry.outside = newValue;
                    } else {
                        entry.insideCSE = cse;
                        entry.inside = newValue;
                    }
                }
            }
        }

    
		
		public ArrayList<ConnectionSet> getConnectionSetList() {
			return list;
		}

    
		
		public OverconstrainedConnectionGraph getGraph() {
			if (graph == null) 
				graph = new OverconstrainedConnectionGraph();
			return graph;
		}

    
        
       public ExpandableConnectorSets getExpandable() {
           if (expandable == null) 
               expandable = new ExpandableConnectorSets();
           return expandable;
       }

    
       
      public boolean isExpandableConnectorsDone() {
          return expandable != null && expandable.isExpansionDone();
      }

    

        public void buildOverconstrainedConnectionTrees() {
            if (graph != null) 
                graph.buildTrees(this);
        }

    
        
        public void elaborateExpandableConnectors() {
            if (expandable != null) {
                expandable.elaborate(this);
                expandable = null;
            }
        }

    

        public void addInsideFlowVar(InstComponentDecl var, FQName name) {
            addVar(ConnectionSetEntry.create(var, false, name));
        }

    

        public void addExpandableArrayMember(InstComponentDecl var, FQName name) {
            // If var is present as inner, don't add it as outer
            if (getConnectionSet(ConnectionSetEntry.create(var, false, name)) == null)
                addVar(ConnectionSetEntry.create(var, true, name));
        }

    

		/**
		 * Add vars to connection sets.
		 * 
		 * Filters out parameters and constants.
		 */
		public void addVars(ConnectionSetEntry cse1, ConnectionSetEntry cse2) {

//			log.debug("ConnectionSetManager.addVars");
		
//		    System.out.println(namePrefix1.name()+" . "+var1.name() + " outside: " + outside1);
//		    System.out.println(namePrefix2.name()+" . "+var2.name() + " outside: " + outside2);
		
			// Don't add parameters or constants to connection set
			// TODO: Add them, but generate asserts instead of equations
			if (cse1.getVar().variability().parameterOrLess() || cse2.getVar().variability().parameterOrLess()) 
				return;

			ConnectionSet setA = getConnectionSet(cse1);
			ConnectionSet setB = getConnectionSet(cse2);
			
			if (setA != null && setB != null) {
				if (setA != setB)
					merge(setA, setB);
			} else if (setA != null && setB == null) {
				add(setA, cse2);
			} else if (setA == null && setB != null) {
				add(setB, cse1);
			} else if (setA == null && setB == null) {
				join(cse1, cse2);
			}
		}

    
		
		public void addEqualityConstraint(ConnectionSetEntry cse1, ConnectionSetEntry cse2, FQName prefix) {
			equalityConstraints.add(new EqualityConstraintConnection(cse1, cse2, prefix));
		}

    
        
        private void addVar(ConnectionSetEntry entry) {
            if (getConnectionSet(entry) == null) {
                ConnectionSet set = ConnectionSet.create(entry);
                list.add(set);
                updateCSEMapEntry(entry, set);
            }
        }

    
		
		private void join(ConnectionSetEntry entryA, ConnectionSetEntry entryB) {
			ConnectionSet set = ConnectionSet.create(entryA);
			set.add(entryB);
			list.add(set);
			updateCSEMapEntry(entryA, set);
			updateCSEMapEntry(entryB, set);
		}

    
		
		private void add(ConnectionSet set, ConnectionSetEntry entry) {
			set.add(entry);
			updateCSEMapEntry(entry, set);
		}

    
		
		private void merge(ConnectionSet setA, ConnectionSet setB) {
			if (setA.size() < setB.size()) {
				ConnectionSet tmp = setA;
				setA = setB;
				setB = tmp;
			}
			for (ConnectionSetEntry entry : setB)
			    updateCSEMapEntry(entry, setA);
			setA.addAll(setB);
			list.remove(setB);
		}

    
		
		public ConnectionSet getConnectionSet(ConnectionSetEntry cse) {
			return cseMap.get(cse);
		}

    

		public ConnectionSet getStreamConnectionSet(String name, boolean outside) {
            CSENameMapEntry entry = cseStreamMap.get(name);
            if (entry == null)
                return null;
            if (outside)
                return entry.outside;
            else
                return entry.inside;
		}

    

		public ArrayList<ConnectionSetEntry> getFlowVariables(String prefix) {
			ArrayList<ConnectionSetEntry> cses = new ArrayList<ConnectionSetEntry>();
			for (ConnectionSet set : list) {
				cses.addAll(set.getFlowVariables(prefix));		
			}
			return cses;
		}

    
		
		public void disconnectFromInstanceTree() {
			for (ConnectionSet set : list)
				set.disconnectFromInstanceTree();
            if (graph != null) 
                graph.disconnectFromInstanceTree();
			equalityConstraints = null;
		}

    
		
		public void generateEqualityConstraints(FClass fc) {
			for (EqualityConstraintConnection ecc : equalityConstraints)
				ecc.generate(fc);
		}

    
		
		public String printConnectionSets() {
		
			StringBuffer str = new StringBuffer();
			
			str.append("Connection sets: " + list.size() + " sets\n");
			
			// Print connection sets 
			for(ConnectionSet set : list) {
				str.append(set);
			}

			return str.toString();
		}

    private Map<String,Enumerator> cardinality = null;

    public void countCardinality(String name) {
        if (cardinality == null)
            cardinality = new HashMap<String,Enumerator>();
        Enumerator e = cardinality.get(name);
        if (e == null)
            cardinality.put(name, new Enumerator(1));
        else
            e.next();
    }

    public int getCardinality(String name) {
        if (cardinality == null)
            return 0;
        Enumerator e = cardinality.get(name);
        return (e == null) ? 0 : e.peek();
    }

    public void scalarize() {
		for (ConnectionSet set : list)
			set.scalarize();
		Map<String, CSENameMapEntry> oldCSEStreamMap = cseStreamMap;
		cseStreamMap = new HashMap<String, CSENameMapEntry>();
		for (CSENameMapEntry entry : oldCSEStreamMap.values()) {
            if (entry.outsideCSE != null)
                updateCSEMapEntry(entry.outsideCSE, entry.outside);
            if (entry.insideCSE != null)
                updateCSEMapEntry(entry.insideCSE, entry.inside);
		}
	}


}
