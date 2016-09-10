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
 * @aspect InstFunctionBinding
 * @declaredat /home/hobbypunk/Dokumente/Entwicklung/THM_Projekte/Projektphase/jModelica/Compiler/ModelicaFrontEnd/src/jastadd/instance/InstFunctionBinding.jrag:501
 */
public class ArgumentBinding extends Object {
    

        private InstFunctionArgument[] bound;

    
        private ArrayList<InstFunctionArgument> unbindable;

    
        private ArrayList<InstComponentDecl> modifiableInputs;

    
        private ArrayList<InstComponentDecl> allInputs;

    
        private InstCallable target;

    
        private InstFunctionCall source;

    
        private boolean ok = true;

    

        public ArgumentBinding(InstCallable target, InstFunctionCall source, boolean infArgs) {
            modifiableInputs = source.myInputsToBind(target);
            allInputs = modifiableInputs;
            if (target.isRecord()) {
                allInputs = new ArrayList<InstComponentDecl>();
                for (InstComponentDecl icd : target.asInstClassDecl().allInstComponentDecls()) {
                    allInputs.add(icd);
                }
            }
            unbindable = new ArrayList<InstFunctionArgument>();
            this.target = target;
            this.source = source;
            if (infArgs) 
                bindInfArgs();
            else 
                bindNormal();
        }

    

        /**
         * Is this binding a match?
         */
        public boolean isOK() {
            return ok;
        }

    

        /**
         * Get the target function.
         */
        public InstCallable target() {
            return target;
        }

    

        /**
         * Get the bound arguments.
         */
        public InstFunctionArgument[] bound() {
            return bound;
        }

    

        /**
         * Get the list of arguments that could not be bound to any input.
         */
        public ArrayList<InstFunctionArgument> unbindable() {
            return unbindable;
        }

    

        /**
         * A heuristic score for how well the arguments match the function, including 
         * type. Lower is better, 0 is a perfect match.
         */
        public int typeMatchScore() {
            int score = unbindable.size() * 2000;
            int i = 0;
            for (InstFunctionArgument arg : bound) {
                if (arg.isOKArg()) {
                    FType argType = arg.getFExpNoTransform().type();
                    FType inType = modifiableInputs.get(i).type();
                    if (!inType.dimensionCompatible(argType, true))
                        score++;
                    if (!inType.scalarType().typeCompatible(argType.scalarType(), true))
                        score++;
                } else {
                    score += 1000;
                }
                i++;
            }
            return score;
        }

    

        /**
         * Update the bound inputs of all arguments. Needed when several ArgumentBinding objects 
         * have been created for the same function call (used for overloaded constructors).
         */
        public void updateBoundInputs() {
            for (int i = 0; i < modifiableInputs.size(); i++) {
                bound[i].setBoundInput(modifiableInputs.get(i));
                if (!bound[i].isOKArg())
                    bound[i].setErrorArgLocation(source);
            }
        }

    
        
        private int[] argPositions(ArrayList<InstComponentDecl> icds1, ArrayList<InstComponentDecl> icds2) {
            int[] pos = new int[icds1.size()];
            int j = 0;
            for (int i = 0; i < pos.length; i++) {
                while (icds2.size() > j && icds1.get(i) != icds2.get(j)) {
                    j++;
                }
                pos[i] = j;
            }
            return pos;
        }

    
        
        private void bindNormal() {
            // Bind arguments
            bound = new InstFunctionArgument[allInputs.size()];
            int[] ap = argPositions(modifiableInputs, allInputs);
            for (InstFunctionArgument arg : source.getArgs()) {
                int pos = arg.findArgumentPosition(modifiableInputs);
                if (!bindArgument(arg, pos, bound, ap)) {
                    unbindable.add(arg);
                }
            }
            
            // Add default argument and check for problems.
            for (int i = 0; i < bound.length; i++) {
                if (bound[i] == null) {
                    bound[i] = allInputs.get(i).createInstDefaultArgument();
                }
                bound[i].setErrorArgLocation(source);
                ok = ok && bound[i].isOKArg();
            }
            ok = ok && unbindable.isEmpty();
        }

    
        
        public boolean bindArgument(InstFunctionArgument arg, int p1, InstFunctionArgument[] arr, int[] ap) {
            if (p1 < 0 || p1 >= modifiableInputs.size()) {
                return false;
            }
            
            int p2 = ap[p1];
            
            if (p2 < 0 || p2 >= arr.length) {
                return false;
            }
            
            if (arr[p2] == null) {
                arr[p2] = (InstFunctionArgument) arg.fullCopy();
            } else {
                arr[p2] = arr[p2].addMultipleBoundArgument(arg);
            }
            
            arr[p2].setBoundInput(modifiableInputs.get(p1));
            arr[p2].parent = arg.parent; // Temporarily add to tree
            return true;
        }

    


        private void bindInfArgs() {
            // Just collect all positional arguments
            ArrayList<InstFunctionArgument> args = new ArrayList<InstFunctionArgument>();
            for (InstFunctionArgument arg : source.getArgs()) {
                if (arg instanceof InstPositionalArgument)
                    args.add(arg);
                else
                    unbindable.add(arg);
            }

            // Make sure we have any fixed arguments
            for (int i = args.size(); i < modifiableInputs.size(); i++) {
                InstMissingArgument arg = new InstMissingArgument();
                args.add(arg);
                ok = false;
            }
            bound = args.toArray(new InstFunctionArgument[args.size()]);
            updateBoundInputs();
        }


}
