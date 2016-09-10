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
 * @declaredat /home/hobbypunk/Dokumente/Entwicklung/THM_Projekte/Projektphase/jModelica/Compiler/ModelicaCBackEnd/src/jastadd/codegen/CGenerator.jrag:24
 */
public class CGenerator extends GenericGenerator {
    protected  String startOfBlurb() { return "/*"; }

    protected static final  String INDENT = "    ";

    public  class DAETag_C_externalFuncIncludes extends DAETag {
		
		public DAETag_C_externalFuncIncludes(AbstractGenerator myGenerator, FClass fclass) {
			super("external_func_includes", myGenerator, fclass);
		}
		
		public void generate(CodeStream genPrinter) {
			for (String incl : fclass.externalIncludes()) 
				genPrinter.println(incl);
		}
	}

    public  class DAETag_C_scalingMethod extends DAETag {
		
		public DAETag_C_scalingMethod(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_scaling_method", myGenerator, fclass);
		}
	
		public void generate(CodeStream genPrinter) {
			if (fclass.myOptions().getBooleanOption("enable_variable_scaling")) {
				genPrinter.print("JMI_SCALING_VARIABLES");
			} else {
				genPrinter.print("JMI_SCALING_NONE");
			}
		}
	
	}

    public abstract  class DAEArrayTag<T extends ASTNode> extends DAETag {
	    
	    private String varName;
	    private String nullValue;
	    private String type;
        
        public DAEArrayTag(String name, AbstractGenerator myGenerator, FClass fclass, 
                String varName, String type, String nullValue) {
            super(name, myGenerator, fclass);
            this.varName = varName;
            this.type = type;
            this.nullValue = nullValue;
        }
        
        public void generate(CodeStream genPrinter) {
            Collection<T> elems = elements();
            int n = numElements(elems);
            genPrinter.format("static const int N_%s = %d;\n", varName, n);
            genPrinter.format("static const %s DAE_%s[] = { ", type, varName);
            if (n == 0) {
                genPrinter.print(nullValue);
            } else {
                boolean first = true;
                for (T e : elems) {
                    if (first)
                        first = false;
                    else
                        genPrinter.print(", ");
                    generateFor(e, genPrinter);
                }
            }
            genPrinter.print(" };");
        }
        
        protected int numElements(Collection<T> elems) {
            return elems.size();
        }
        
        protected abstract Collection<T> elements();
        
        protected abstract void generateFor(T e, CodeStream genPrinter);
	    
	}

    public  class DAETag_C_initial_relations extends DAEArrayTag<FRelExp> {
		
		public DAETag_C_initial_relations(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_initial_relations", myGenerator, fclass, "initial_relations", "int", "-1");
		}
        
        protected Collection<FRelExp> elements() {
            return fclass.eventExpInInitialEquations();
        }
        
        protected void generateFor(FRelExp e, CodeStream genPrinter) {
            e.genRelExpKind(genPrinter);
        }
	
	}

    public  class DAETag_C_relations extends DAEArrayTag<FExp> {
		
		public DAETag_C_relations(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_relations", myGenerator, fclass, "relations", "int", "-1");
		}
        
        @Override
        protected Collection<FExp> elements() {
            return fclass.stateEventExps();
        }
        
        @Override
        protected int numElements(Collection<FExp> elems) {
            return fclass.numEventIndicators();
        }
        
        protected void generateFor(FExp e, CodeStream genPrinter) {
            e.genRelExpKind(genPrinter);
        }
        
    }

    public  class DAETag_C_nominals extends DAEArrayTag<FRealVariable> {
        
        public DAETag_C_nominals(AbstractGenerator myGenerator, FClass fclass) {
            super("C_DAE_nominals", myGenerator, fclass, "nominals", "jmi_real_t", "0.0");
        }
        
        protected Collection<FRealVariable> elements() {
            return fclass.differentiatedRealVariables();
        }
        
        protected void generateFor(FRealVariable e, CodeStream genPrinter) {
            genPrinter.print(StrictMath.abs(e.nominalAttribute()));
        }
    
    }

    public  class DAETag_C_equationResiduals extends DAETag {
		
		public DAETag_C_equationResiduals(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_equation_residuals", myGenerator, fclass);
			addOptions("generate_dae");
		}
	
		public void generate(CodeStream genPrinter) {
			for (FAbstractEquation e : fclass.equations()) 
				e.genVarDecls_C(ASTNode.printer_C, genPrinter, INDENT);
			Enumerator enumerator = new Enumerator();
			for (FAbstractEquation e : fclass.equations())
				e.genResidual_C(ASTNode.printer_C, genPrinter, INDENT, enumerator, null, null);
		}
	
	}

    public  class DAETag_C_eventIndicatorResiduals extends DAETag {
		
		public DAETag_C_eventIndicatorResiduals(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_event_indicator_residuals", myGenerator, fclass);
		}
	
		public void generate(CodeStream genPrinter) {
            CodePrinter p = ASTNode.printer_C.eventIndicatorPrinter();
			if (!fclass.onlyInitBLT()) {
                for (FExp e : fclass.stateEventExps()) 
                    e.genEventResidualVarDecls_C(p, genPrinter, INDENT);
				Enumerator enumerator = new Enumerator();
                for (FExp e : fclass.stateEventExps())
                    e.genEventResidual_C(p, genPrinter, INDENT, enumerator);
			} else {
				genPrinter.print("  model_init_R0(jmi, res);\n");
			}
		}
	
	}

    public  class DAETag_C_initialEquationResiduals extends DAETag {
		
		public DAETag_C_initialEquationResiduals(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_initial_equation_residuals", myGenerator, fclass);
            addOptions("generate_dae");
		}
	
		public void generate(CodeStream genPrinter) {
		    CodePrinter printer = ASTNode.printer_C.initialSystemPrinter();
			for (FAbstractEquation e : fclass.equations()) 
				e.genVarDecls_C(printer, genPrinter, INDENT);
			for (FAbstractEquation e : fclass.initialEquations()) 
				e.genVarDecls_C(printer, genPrinter, INDENT);
			Enumerator enumerator = new Enumerator();
			for (FAbstractEquation e : fclass.equations())
				e.genResidual_C(printer, genPrinter, INDENT, enumerator, null, null);
			for (FAbstractEquation e : fclass.initialEquations())
				e.genResidual_C(printer, genPrinter, INDENT, enumerator, null, null);
		}	
	}

    public  class DAETag_C_initialEventIndicatorResiduals extends DAETag {
		
		public DAETag_C_initialEventIndicatorResiduals(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_initial_event_indicator_residuals", myGenerator, fclass);
		}
	
		public void generate(CodeStream genPrinter) {
            CodePrinter printer = ASTNode.printer_C.initialSystemPrinter().eventIndicatorPrinter();
            for (FExp e : fclass.stateEventExps()) 
                e.genEventResidualVarDecls_C(printer, genPrinter, INDENT);
            for (FExp e : fclass.eventExpInInitialEquations()) 
                e.genEventResidualVarDecls_C(printer, genPrinter, INDENT);
			Enumerator enumerator = new Enumerator();
            for (FExp e : fclass.stateEventExps())
                e.genEventResidual_C(printer, genPrinter, INDENT, enumerator);
            for (FExp e : fclass.eventExpInInitialEquations())
                e.genEventResidual_C(printer, genPrinter, INDENT, enumerator);
		}
	}

    public  class DAETag_C_initialGuessEquationResiduals extends DAETag {
		
		public DAETag_C_initialGuessEquationResiduals(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_initial_guess_equation_residuals", myGenerator, fclass);
		}
	
		public void generate(CodeStream genPrinter) {
			for (FRealVariable fv : fclass.realVariables())
                if (!fv.isFixed()) {
					fv.genVarDecls_C(ASTNode.printer_C, genPrinter, INDENT);
                }
			Enumerator enumerator = new Enumerator();
			for (FRealVariable fv : fclass.realVariables())
                if (!fv.isFixed()) {
					fv.genStartAttributeResidual_C(ASTNode.printer_C, genPrinter, INDENT, enumerator);
                }
		}
	
	}

    public  class DAETag_C_initialDependentParameterResiduals extends DAETag {
		
		public DAETag_C_initialDependentParameterResiduals(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_initial_dependent_parameter_residuals", myGenerator, fclass);
            addOptions("generate_dae");
		}
	
		public void generate(CodeStream genPrinter) {
			Enumerator enumerator = new Enumerator();
			for (FAbstractEquation e : fclass.getFParameterEquations())
				e.genResidual_C(ASTNode.printer_C, genPrinter, INDENT, enumerator, null, null);
		}
	
	}

    public  class DAETag_C_initialDependentParameterAssignments extends DAETag {
		
		public DAETag_C_initialDependentParameterAssignments(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_initial_dependent_parameter_assignments", myGenerator, fclass);
		}
	
        public void generate(CodeStream str) {
            CodePrinter p = ASTNode.printer_C;
            String indent = "";
            String next = p.indent(indent);
            
            CodeSplitter<FAbstractEquation> cs1 = new CodeSplitter<FAbstractEquation>(p, str, next, 
                    true, "model_init_eval_parameters_", fclass.getFParameterEquations().toArrayList()) {
                @Override
                public void genDecl(FAbstractEquation element) {
                    p.printVarDecls(element, str, indent);
                }
                @Override
                public void gen(FAbstractEquation element) {
                    element.genAssignment_C(p, str, indent);
                }
            };
            
            cs1.genFuncs();
            
            str.print("int model_init_eval_parameters_base(jmi_t* jmi) {\n");
            cs1.printStatusDecl();
            cs1.genCalls();
            cs1.printStatusReturn();
            str.print("}\n");
		}
	
	}

    public  class DAETag_C_variableAliases extends DAETag {
		
		public DAETag_C_variableAliases(AbstractGenerator myGenerator, FClass fclass) {
			super("C_variable_aliases", myGenerator, fclass);
		}
		
        private void generateVar(CodeStream genPrinter, FVariable fv, String offset, int index, boolean pre) {
            genPrinter.print("#define ");
            if (pre) {
                genPrinter.print("pre" + fv.name_C());
                genPrinter.print(" ((*(jmi->z))[jmi->offs_pre_");
            } else {
                genPrinter.print(fv.name_C());
                genPrinter.print(" ((*(jmi->z))[jmi->offs_");
            }
            genPrinter.print(offset);
            genPrinter.print("+");
            genPrinter.print(index);
            genPrinter.print("])\n");
        }
        
        private void generateVarList(CodeStream genPrinter, String offset, Iterable<? extends FVariable>... lists) {
            generateVarList(genPrinter, offset, false, lists);
        }
        private void generatePreVarList(CodeStream genPrinter, String offset, Iterable<? extends FVariable>... lists) {
            generateVarList(genPrinter, offset, true, lists);
        }
        private void generateVarList(CodeStream genPrinter, String offset, boolean pre, Iterable<? extends FVariable>... lists) {
            int index = 0;
            for (Iterable<? extends FVariable> list : lists)
                for (FVariable fv : list)
                    generateVar(genPrinter, fv, offset, index++, pre);
        }
        
        private void generateExtObject(CodeStream genPrinter, FVariable fv, int index) {
            genPrinter.print("#define ");
            genPrinter.print(fv.name_C());
            genPrinter.print(" ((jmi->ext_objs)[");
            genPrinter.print(index);
            genPrinter.print("])\n");
        }
        
        private void generateExtObjectList(CodeStream genPrinter, Iterable<? extends FExternalObjectVariable> list) {
            int index = 0;
            for (FExternalObjectVariable fv : list)
                generateExtObject(genPrinter, fv, index++);
        }
        
        public void generate(CodeStream genPrinter) {
            generateVarList(genPrinter, "real_ci",    fclass.independentRealConstants());
            generateVarList(genPrinter, "real_cd",    fclass.dependentRealConstants());
            generateVarList(genPrinter, "real_pi",    fclass.independentRealParameters());
            generateVarList(genPrinter, "real_pd",    fclass.dependentRealParameters());
            // Handle enums as Integers
            generateVarList(genPrinter, "integer_ci", fclass.independentIntegerConstants(),  fclass.independentEnumConstants());
            generateVarList(genPrinter, "integer_cd", fclass.dependentIntegerConstants(),    fclass.dependentEnumConstants());
            generateVarList(genPrinter, "integer_pi", fclass.independentIntegerEnumParameters());
            generateVarList(genPrinter, "integer_pd", fclass.dependentIntegerParameters(),   fclass.dependentEnumParameters());
            generateVarList(genPrinter, "boolean_ci", fclass.independentBooleanConstants());
            generateVarList(genPrinter, "boolean_cd", fclass.dependentBooleanConstants());
            generateVarList(genPrinter, "boolean_pi", fclass.independentBooleanParameters());
            generateVarList(genPrinter, "boolean_pd", fclass.dependentBooleanParameters());
            generateVarList(genPrinter, "real_dx",    fclass.derivativeVariables());
            generateVarList(genPrinter, "real_x",     fclass.differentiatedRealVariables());
            generateVarList(genPrinter, "real_u",     fclass.realInputs());
            generateVarList(genPrinter, "real_w",     fclass.algebraicContinousRealVariables());
            genPrinter.print("#define _time ((*(jmi->z))[jmi->offs_t])\n"); 
            genPrinter.print("#define __homotopy_lambda ((*(jmi->z))[jmi->offs_homotopy_lambda])\n"); 
            generateVarList(genPrinter, "real_d",     fclass.discreteRealVariables());
            generateVarList(genPrinter, "integer_d",  fclass.discreteIntegerVariables(), fclass.discreteEnumVariables());
            generateVarList(genPrinter, "integer_u",  fclass.integerInputs(),            fclass.enumInputs());
            generateVarList(genPrinter, "boolean_d",  fclass.discreteBooleanVariables());
            generateVarList(genPrinter, "boolean_u",  fclass.booleanInputs());
            if (fclass.myOptions().getBooleanOption("generate_ode")) {
                generatePreVarList(genPrinter, "real_dx",   fclass.derivativeVariables());
                generatePreVarList(genPrinter, "real_x",    fclass.differentiatedRealVariables());
                generatePreVarList(genPrinter, "real_u",    fclass.realInputs());
                generatePreVarList(genPrinter, "real_w",    fclass.algebraicContinousRealVariables());
                generatePreVarList(genPrinter, "real_d",    fclass.discreteRealVariables());
                generatePreVarList(genPrinter, "integer_d", fclass.discreteIntegerVariables(), fclass.discreteEnumVariables());
                generatePreVarList(genPrinter, "integer_u", fclass.integerInputs(),            fclass.enumInputs());
                generatePreVarList(genPrinter, "boolean_d", fclass.discreteBooleanVariables());
                generatePreVarList(genPrinter, "boolean_u", fclass.booleanInputs());
            }
            generateExtObjectList(genPrinter, fclass.externalObjectVariables());
        }
	}

    
    
    class ZOffsets {
        private LinkedHashListMap<FTypePrefixVariability.VariabilityCausality_C, FVariable> m = 
                new LinkedHashListMap<FTypePrefixVariability.VariabilityCausality_C, FVariable>();
        
        public ZOffsets(ArrayList<? extends FVariable> fvs) {
            for (FVariable fv : fvs) {
                m.add(fv.variabilityCausality_C(), fv);
            }
        }
        
        public int size() {
            int res = 0;
            for (java.util.List<FVariable> l : m.values()) {
                res = res + l.size();
            }
            return res;
        }
        
        public java.util.List<FVariable> get(FTypePrefixVariability.VariabilityCausality_C s) {
            return m.getList(s);
        }
    }

    public abstract  class DAETag_C_z_strings extends DAETag {
        public DAETag_C_z_strings(String name, AbstractGenerator myGenerator, FClass fclass) {
            super(name, myGenerator, fclass);
        }
        
        abstract int genList(CodeStream str, ZOffsets zo, int o, FTypePrefixVariability.VariabilityCausality_C s);
        void genLast(CodeStream str, int o) {
            
        }
        
        public void generate(CodeStream str) {
            ZOffsets zo = new ZOffsets(fclass.strings());
            int o = 0;
            o = genList(str, zo, o, FTypePrefixVariability.VariabilityCausality_C.CI);
            o = genList(str, zo, o, FTypePrefixVariability.VariabilityCausality_C.CD);
            o = genList(str, zo, o, FTypePrefixVariability.VariabilityCausality_C.PI);
            o = genList(str, zo, o, FTypePrefixVariability.VariabilityCausality_C.PS);
            o = genList(str, zo, o, FTypePrefixVariability.VariabilityCausality_C.PF);
            o = genList(str, zo, o, FTypePrefixVariability.VariabilityCausality_C.PE);
            o = genList(str, zo, o, FTypePrefixVariability.VariabilityCausality_C.PD);
            genLast(str, o);
        }
    }

    public  class DAETag_C_z_stringAliases extends DAETag_C_z_strings {
        public DAETag_C_z_stringAliases(AbstractGenerator myGenerator, FClass fclass) {
            super("C_z_aliases_strings", myGenerator, fclass);
        }
        
        private void genVar(CodeStream str, FVariable fv, int index) {
            str.print("#define ");
            str.print(fv.name_C());
            str.print(" jmi->z_t.strings.values[");
            str.print(index);
            str.print("]\n");
        }
        
        @Override
        int genList(CodeStream str, ZOffsets zo, int o, FTypePrefixVariability.VariabilityCausality_C s) {
            for (FVariable fv : zo.get(s)) {
                genVar(str, fv, o++);
            }
            return o;
        }
    }

    public  class DAETag_C_z_stringOffsets extends DAETag_C_z_strings {
        
        public DAETag_C_z_stringOffsets(AbstractGenerator myGenerator, FClass fclass) {
            super("C_z_offsets_strings", myGenerator, fclass);
        }
        
        @Override
        int genList(CodeStream str, ZOffsets zo, int o, FTypePrefixVariability.VariabilityCausality_C s) {
            int n = zo.get(s).size();
            str.print("o->o_");
            str.print(s);
            str.print(" = ");
            str.print(o);
            str.print(";\n");
            str.print("o->n_");
            str.print(s);
            str.print(" = ");
            str.print(n);
            str.print(";\n");
            return o + n;
        }
        
        void genLast(CodeStream str, int o) {
            str.print("o->n = ");
            str.print(o);
            str.print(";\n");
        }
    }

    public  class DAETag_C_dae_blocks_residual_functions extends DAETag {
		
		public DAETag_C_dae_blocks_residual_functions(AbstractGenerator myGenerator, FClass fclass) {
			super("C_dae_blocks_residual_functions", myGenerator, fclass);
            addOptions("generate_ode");
		}
		
        @Override
        public boolean isActive() {
            return !fclass.onlyInitBLT() && super.isActive();
        }

		public void generate(CodeStream genPrinter) {
			for (AbstractEquationBlock block : fclass.getDAEStructuredBLT().getAllBlocks())
				block.genBlockResidualFunction_C(ASTNode.blockPrinter_C, genPrinter, "", false);
		}
	}

    public  class DAETag_C_dae_add_blocks_residual_functions extends DAETag {

        public DAETag_C_dae_add_blocks_residual_functions(AbstractGenerator myGenerator, FClass fclass) {
            super("C_dae_add_blocks_residual_functions", myGenerator, fclass);
            addOptions("generate_ode");
        }

        @Override
        public boolean isActive() {
            return !fclass.onlyInitBLT() && super.isActive();
        }

        public void generate(CodeStream genPrinter) {
            if (!CADGenerator.shouldGenerateBlockJacobians(fclass)) {
                CodePrinter p = ASTNode.printer_C;
                String indent = p.indent("");
                for (AbstractEquationBlock block : fclass.getDAEStructuredBLT().getAllBlocks())
                    block.genBlockAddCall_C(p, genPrinter, indent, false, false, fclass.myOptions().getStringOption("nonlinear_solver"), -1);
            }
        }
	}

    public  class DAETag_C_dae_n_blocks extends DAETag {
		
		public DAETag_C_dae_n_blocks(AbstractGenerator myGenerator, FClass fclass) {
			super("n_dae_blocks", myGenerator, fclass);
		}

        public void generate(CodeStream genPrinter) {
            if (fclass.onlyInitBLT())
                genPrinter.print("0");
            else
                genPrinter.print(fclass.getDAEBLT().getNumLabledBlocks());
        }
	}

    public  class DAETag_C_dae_init_blocks_residual_functions extends DAETag {
		
		public DAETag_C_dae_init_blocks_residual_functions(AbstractGenerator myGenerator, FClass fclass) {
			super("C_dae_init_blocks_residual_functions", myGenerator, fclass);
            addOptions("generate_ode");
		}

		public void generate(CodeStream genPrinter) {
            CodePrinter printer = ASTNode.blockPrinter_C.initialSystemPrinter();
			for (AbstractEquationBlock block : fclass.getDAEInitBLT())
				block.genBlockResidualFunction_C(printer, genPrinter, "", true); 
		}
	}

    public  class DAETag_C_dae_init_add_blocks_residual_functions extends DAETag {

        public DAETag_C_dae_init_add_blocks_residual_functions(AbstractGenerator myGenerator, FClass fclass) {
            super("C_dae_init_add_blocks_residual_functions", myGenerator, fclass);
            addOptions("generate_ode");
        }

        public void generate(CodeStream genPrinter) {
            if (!CADGenerator.shouldGenerateBlockJacobians(fclass)) {
                CodePrinter p = ASTNode.printer_C;
                String indent = p.indent("");
                for (AbstractEquationBlock block : fclass.getDAEInitBLT())
                    block.genBlockAddCall_C(p, genPrinter, indent, false, true, fclass.myOptions().getStringOption("nonlinear_solver"), -1);
            }
        }
    }

    public  class DAETag_C_dae_init_n_blocks extends DAETag {
		
		public DAETag_C_dae_init_n_blocks(AbstractGenerator myGenerator, FClass fclass) {
			super("n_dae_init_blocks", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
			genPrinter.print(fclass.getDAEInitBLT().getNumLabledBlocks());
		}
	}

    public  class DAETag_C_ode_guards extends DAETag {
		
		public DAETag_C_ode_guards(AbstractGenerator myGenerator, FClass fclass) {
			super("C_ode_guards", myGenerator, fclass);
			addOptions("generate_ode");
		}

		public void generate(CodeStream genPrinter) {
			if (!fclass.onlyInitBLT()) {
				for (FExp e : fclass.guardExpInEquations()) 
					e.genVarDecls_C(ASTNode.printer_C, genPrinter, INDENT);
				int i=0;
				for (FExp e : fclass.guardExpInEquations()) {
				    genPrinter.print("  _guards(" + i + ") = ");
					e.prettyPrint_C(genPrinter,"");		
					genPrinter.print(";\n");		
					i++;
				}
			} else {
				genPrinter.print("  model_ode_guards_init(jmi);\n");
			}
		}
	}

    public  class DAETag_C_ode_guards_init extends DAETag {
		
		public DAETag_C_ode_guards_init(AbstractGenerator myGenerator, FClass fclass) {
			super("C_ode_guards_init", myGenerator, fclass);
            addOptions("generate_ode");
		}

		public void generate(CodeStream genPrinter) {
            CodePrinter printer = ASTNode.printer_C.initialSystemPrinter();
			for (FExp e : fclass.guardExpInInitialEquations()) 
				e.genVarDecls_C(printer, genPrinter, INDENT);
			int i=0;
			for (FExp e : fclass.guardExpInInitialEquations()) {
			    genPrinter.print("  _guards_init(" + i + ") = ");
				e.prettyPrint_C(printer, genPrinter,"");
				genPrinter.print(";\n");
				i++;
			}
		}
	}

    public  class DAETag_C_ode_time_events extends DAETag {
		
		public DAETag_C_ode_time_events(AbstractGenerator myGenerator, FClass fclass) {
			super("C_ode_time_events", myGenerator, fclass);
            addOptions("generate_ode");
		}

        @Override
        public boolean isActive() {
            return !fclass.onlyInitBLT() && super.isActive();
        }
        
        public void generate(CodeStream str) {
            CodePrinter p = ASTNode.printer_C.eventIndicatorPrinter();
            String indent = p.indent("");
            for (FExp e : fclass.timeEventExps()) 
                e.genTimeEventVarDecls_C(p, str, INDENT);
            
            str.formatln("%sjmi_time_event_t nextEvent = {0};", indent);
            str.formatln("%sjmi_real_t nSamp;", indent);
            
//            str.print("  printf(\"sample function called at time %f\\n\",_t);\n");	
            for (FExp e : fclass.timeEventExps()) {
                e.genTimeEvent_C(p, str, indent, "nextEvent");
            }
//            str.print("printf(\"Happ: %12.12f\\n\",nextTimeEvent);\n");
            
            str.formatln("%s*event = nextEvent;\n", indent);
        }
    }

    public  class DAETag_C_ode_derivatives extends DAETag {
		
		public DAETag_C_ode_derivatives(AbstractGenerator myGenerator, FClass fclass) {
			super("C_ode_derivatives", myGenerator, fclass);
		}

        public void generate(CodeStream str) {
            CodePrinter p = ASTNode.printer_C;
            String indent = "";
            String next = p.indent(indent);
            
            CodeSplitter<AbstractEquationBlock> cs = new CodeSplitter<AbstractEquationBlock>(p, str, next, true,
                    "model_ode_derivatives_") {
                @Override
                public void genDecl(AbstractEquationBlock element) {
                    element.genVarDecls_C(p, str, indent);
                }
                @Override
                public void genPre(AbstractEquationBlock element) {
                    element.genReinitTempInits_C(p, str, indent);
                }
                @Override
                public void gen(AbstractEquationBlock element) {
                    element.genBlock_C(p, str, indent, false);
                }
                @Override
                public void genPost(AbstractEquationBlock element) {
                    element.genReinitWritebacks_C(p, indent, str);
                }
            };
            
            if (!fclass.onlyInitBLT() && fclass.myOptions().getBooleanOption("generate_ode")) {
                cs.add(fclass.getDAEStructuredBLT().getAllBlocks());
            }
            
            cs.genFuncs();
            
            str.print("int model_ode_derivatives_base(jmi_t* jmi) {\n");
            cs.printStatusDecl();
            if (fclass.onlyInitBLT()) {
                str.print(next + "ef = model_ode_initialize(jmi);\n");
            } else {
                cs.genCalls();
            }
            cs.printStatusReturn();
            str.print("}\n");
        }
    }

    public  class DAETag_C_ode_initialization extends DAETag {
		
		public DAETag_C_ode_initialization(AbstractGenerator myGenerator, FClass fclass) {
			super("C_ode_initialization", myGenerator, fclass);
		}

        public void generate(CodeStream str) {
            
            CodePrinter p = ASTNode.printer_C.initialSystemPrinter();
            String indent = "";
            String next = p.indent(indent);
            
            CodeSplitter<AbstractEquationBlock> cs = new CodeSplitter<AbstractEquationBlock>(p, str, next, true,
                    "model_ode_initialize_") {
                @Override
                public void genDecl(AbstractEquationBlock element) {
                    element.genVarDecls_C(p, str, indent);
                }
                @Override
                public void genPre(AbstractEquationBlock element) {
                    element.genReinitTempInits_C(p, str, indent);
                }
                @Override
                public void gen(AbstractEquationBlock element) {
                    element.genBlock_C(p, str, indent, true);
                }
                @Override
                public void genPost(AbstractEquationBlock element) {
                    element.genReinitWritebacks_C(p, indent, str);
                }
            };
            
            if (fclass.myOptions().getBooleanOption("generate_ode")) {
                cs.add(fclass.getDAEInitBLT());
            }
            
            cs.genFuncs();
            
            str.print("int model_ode_initialize_base(jmi_t* jmi) {\n");
            cs.printStatusDecl();
            cs.genCalls();
            cs.printStatusReturn();
            str.print("}\n");
        }
    }

    public  class DAETag_C_ode_outputs extends DAETag {
		
		public DAETag_C_ode_outputs(AbstractGenerator myGenerator, FClass fclass) {
			super("C_ode_outputs", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
		}
	}

    public  class DAETag_C_reinit_writeback_temps extends DAETag {
        
        public DAETag_C_reinit_writeback_temps(AbstractGenerator myGenerator, FClass fclass) {
            super("C_reinit_temp_decls", myGenerator, fclass);
            addOptions("generate_ode");
        }

        public void generate(CodeStream genPrinter) {
            for (AbstractEquationBlock block : fclass.getDAEStructuredBLT().getAllBlocks())
                block.genReinitTempDecls_C(ASTNode.printer_C, genPrinter, "");
        }
    }

    public  class DAETag_C_reinit_writeback_temps_initial extends DAETag {
        
        public DAETag_C_reinit_writeback_temps_initial(AbstractGenerator myGenerator, FClass fclass) {
            super("C_reinit_temp_decls_initial", myGenerator, fclass);
            addOptions("generate_ode");
        }
        
        public void generate(CodeStream genPrinter) {
            for (AbstractEquationBlock block : fclass.getDAEInitBLT().getAllBlocks())
                block.genReinitTempDecls_C(ASTNode.printer_C.initialSystemPrinter(), genPrinter, "");
        }
    }

    public  class DAETag_C_function_headers extends DAETag {
		
		public DAETag_C_function_headers(AbstractGenerator myGenerator, FClass fclass) {
			super("C_function_headers", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
            for (FFunctionDecl func : fclass.myFFunctionDeclsPartial())
                func.genStructsPartial_C(genPrinter, "");
            for (FFunctionDecl func : fclass.myFFunctionDeclsPartialCalled())
                func.genHeadersPartial_C(genPrinter, "");
			for (FFunctionDecl func : fclass.getFFunctionDecls())
				func.genHeader_C(genPrinter, "");
		}
	}

    public  class DAETag_C_functions extends DAETag {
		
		public DAETag_C_functions(AbstractGenerator myGenerator, FClass fclass) {
			super("C_functions", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
            for (FFunctionDecl func : fclass.myFFunctionDeclsPartialCalled())
                func.prettyPrintPartial_C(genPrinter, "");
			for (FFunctionDecl func : fclass.getFFunctionDecls())
				func.prettyPrint_C(genPrinter, "");
		}
	}

    public  class DAETag_C_records extends DAETag {
		
		public DAETag_C_records(AbstractGenerator myGenerator, FClass fclass) {
			super("C_records", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
			for (FRecordDecl rec : fclass.getFRecordDecls())
				rec.prettyPrint_C(genPrinter, "");
		}
	}

    public  class DAETag_C_enums_strings extends DAETag {
		
		public DAETag_C_enums_strings(AbstractGenerator myGenerator, FClass fclass) {
			super("C_enum_strings", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
			for (FEnumDecl e : fclass.getFEnumDecls())
				e.prettyPrint_C(genPrinter, "");
		}
	}

    public  class DAETag_C_export_functions extends DAETag {
		
		public DAETag_C_export_functions(AbstractGenerator myGenerator, FClass fclass) {
			super("C_export_functions", myGenerator, fclass);
            addOptions("export_functions");
		}

		public void generate(CodeStream out) {
			for (FFunctionDecl func : fclass.getFFunctionDecls())
				if (func.hasExportWrapper_C())
					func.exportWrapper_C(out, "");
		}
	}

    public  class DAETag_C_export_wrappers extends DAETag {
		
		public DAETag_C_export_wrappers(AbstractGenerator myGenerator, FClass fclass) {
			super("C_export_wrappers", myGenerator, fclass);
            addOptions("export_functions_vba");
		}

		public void generate(CodeStream out) {
			// TODO: refactor out parts not specific to VBA to make it 
			//       easier to support other platforms with special needs
			int i = 0;
			String ind = ASTNode.printer_C.indent("");
            for (java.util.List<FFunctionDecl> grp : fclass.exportWrapperGroups()) {
				FFunctionDecl first = grp.get(0);
				String type = first.exportWrapperType_C();
				String name = "select_vba_" + (++i);
				
				out.format("char* %s_names[] = { ", name);
				String fmt = "\"%s\"";
				for (FFunctionDecl f : grp) {
					out.format(fmt, f.getFQName().nameUnderscore());
					fmt = ", \"%s\"";
				}
				out.print(" };\n");
				
				out.format("int %s_lengths[] = { ", name);
				fmt = "%d";
				for (FFunctionDecl f : grp) {
					out.format(fmt, f.getFQName().nameUnderscore().length());
					fmt = ", %d";
				}
				out.print(" };\n");
				
				out.format("%s (*%s_funcs[])(", type, name);
				first.exportWrapperArgumentTypeDecl_C(out);
				out.print(") = { ");
				fmt = "*%s";
				for (FFunctionDecl f : grp) {
					out.format(fmt, f.funcNameExportWrapper());
					fmt = ", *%s";
				}
				out.print(" };\n");
				
				String sep = first.myInputs().isEmpty() ? "" : ", "; 
				out.format("DllExport %s __stdcall %s(char* name%s", type, name, sep);
				first.exportWrapperArgumentDecl_C(out);
				out.print(") {\n");
				out.format("%sint i, j;\n", ind);
				out.format("%sfor (i = 0, j = 0; name[i] != 0; i++) \n", ind);
				out.format("%s%swhile (j < %d && i <= %s_lengths[j] && name[i] > %s_names[j][i]) j++;\n", ind, ind, grp.size(), name, name);
				out.format("%sif (j >= %d || strcmp(%s_names[j], name)) return 0;\n", ind, grp.size(), name);
				out.format("%sreturn %s_funcs[j](", ind, name);
				first.exportWrapperArgumentCall_C(out);
				out.print(");\n");
				out.print("}\n\n");
			}
		}
	}

    public  class DAETag_C_outputVrefs extends DAETag {
		
		public DAETag_C_outputVrefs(AbstractGenerator myGenerator, FClass fclass) {
			super("C_DAE_output_vrefs", myGenerator, fclass);
		}
	
		public void generate(CodeStream genPrinter) {

			if (fclass.numOutputs()>0) {
				genPrinter.print("static const int Output_vrefs[" + 
					fclass.numOutputs() + "] = {");		

				int ind = 0;
				for (FVariable fv : fclass.outputs()) {
					genPrinter.print(fv.valueReference());
					if (ind < fclass.numOutputs()-1) {
						genPrinter.print(",");
					}	
					ind++;
				}		
				genPrinter.print("};\n");
			} else {
				genPrinter.print("static const int Output_vrefs[1] = {-1};");
			} 
		}	
	}

    public  class DAETag_C_model_id extends DAETag {
		
		public DAETag_C_model_id(AbstractGenerator myGenerator, FClass fclass) {
			super("C_model_id", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
			genPrinter.print(fclass.nameUnderscore());
		}
	}

    public  class DAETag_C_jmodelica_version extends DAETag {
        
        public DAETag_C_jmodelica_version(AbstractGenerator myGenerator, FClass fclass) {
            super("C_jmodelica_version", myGenerator, fclass);
        }
        
        public void generate(CodeStream genPrinter) {
            genPrinter.print(Version.parseVersion());
        }
    }

    public  class DAETag_C_guid extends DAETag {
		
		public DAETag_C_guid(AbstractGenerator myGenerator, FClass fclass) {
			super("C_guid", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
			genPrinter.print("\""+fclass.guid()+"\"");
		}
	}

    public  class DAETag_C_DAE_INIT_homotopy_block extends DAETag {
        
        public DAETag_C_DAE_INIT_homotopy_block(AbstractGenerator myGenerator, FClass fclass) {
            super("C_DAE_INIT_homotopy_block", myGenerator, fclass);
        }
    
        public void generate(CodeStream genPrinter) {
            int num = -1;
            if (fclass.myOptions().getBooleanOption("generate_ode")) {
                for (AbstractEquationBlock block : fclass.getDAEInitBLT()) {
                    if (block.containsFHomotopyExp()) {
                        if (num != -1) {
                            throw new UnsupportedOperationException("There is more than one block which contains homotopy operator, this should not be possible!");
                        } else {
                            num = block.getSequenceNumber();
                        }
                    }
                }
            }
            genPrinter.print(num);
        }
    
    }

    public  class DAETag_C_set_start_values extends DAETag {
		
		public DAETag_C_set_start_values(AbstractGenerator myGenerator, FClass fclass) {
			super("C_set_start_values", myGenerator, fclass);
		}
		
        
        public void generate(CodeStream str) {
            CodePrinter p = ASTNode.printer_C;
            String indent = "";
            String next = p.indent(indent);
            
            CodeSplitter<FVariable> cs1 = new CodeSplitter<FVariable>(p, str, next, false,
                    "jmi_set_start_values_0_") {
                @Override
                public void genDecl(FVariable element) {
                    p.printVarDecls(element, str, indent);
                }
                @Override
                public void gen(FVariable element) {
                    element.genStartValue_C(p, str, indent);
                }
            };
            
            cs1.add(fclass.independentConstants());
            cs1.add(fclass.dependentConstants());
            cs1.add(fclass.independentParameters());
            cs1.genFuncs();
            
            CodeSplitter<FVariable> cs2 = new CodeSplitter<FVariable>(p, str, next, false,
                    "jmi_set_start_values_1_") {
                @Override
                public void genDecl(FVariable element) {
                    p.printVarDecls(element, str, indent);
                }
                @Override
                public void gen(FVariable element) {
                    element.genStartValue_C(p, str, indent);
                }
            };
            for (FVariable fv : fclass.initialParameters()) {
                if (!fv.hasParameterEquation()) {
                    cs2.add(fv);
                }
            }
            cs2.add(fclass.variables());
            cs2.add(fclass.discretePreVariables());
            cs2.genFuncs();
            
            str.print("int jmi_set_start_values_base(jmi_t* jmi) {\n");
            cs1.printStatusDecl();
            cs1.genCalls();
            str.print(next);
            str.print("model_init_eval_parameters(jmi);\n");
            cs2.genCalls();
            cs1.printStatusReturn();
            str.print("}\n");
        }
	}

    public  class DAETag_C_runtime_option_map extends DAETag {
		
		public DAETag_C_runtime_option_map(AbstractGenerator myGenerator, FClass fclass) {
			super("C_runtime_option_map", myGenerator, fclass);
		}

		public void generate(CodeStream genPrinter) {
			genPrinter.print("const char *fmi_runtime_options_map_names[] = {\n");
			for (FVariable fv : fclass.runtimeOptionParameters())
				genPrinter.println("    \"" + fv.name() + "\",");
			genPrinter.print("    NULL\n};\n\n");

			genPrinter.print("const int fmi_runtime_options_map_vrefs[] = {\n    ");
			int i = 0;
			for (FVariable fv : fclass.runtimeOptionParameters()) 
				genPrinter.print(fv.valueReference() + ((++i % 10) == 0 ? ",\n    " : ", "));
			genPrinter.print("0\n};\n\n");

			genPrinter.print("const int fmi_runtime_options_map_length = " + fclass.numRuntimeOptionParameters() + ";");
		}
	}

    public  class DAETag_C_destruct_external_object extends DAETag {
		
		public DAETag_C_destruct_external_object(AbstractGenerator myGenerator, FClass fclass) {
			super("C_destruct_external_object", myGenerator, fclass);
		}

		public void generate(CodeStream str) {
            CodePrinter p = ASTNode.printer_C;
            for (FExternalObjectVariable eo : fclass.externalObjectVariables()) {
                eo.genDestructorCall_C(p, str, p.indent(""));
            }
		}
	}

    public  class DAETag_n_delays extends DAETag {
        
        public DAETag_n_delays(AbstractGenerator myGenerator, FClass fclass) {
            super("n_delays", myGenerator, fclass);
        }
        
        public void generate(CodeStream genPrinter) {
            genPrinter.print(fclass.myDelayExps().size());
        }
    }

    public  class DAETag_numDelaySwitchingFunctions extends DAETag {
        
        public DAETag_numDelaySwitchingFunctions(AbstractGenerator myGenerator, FClass fclass) {
            super("n_delay_switches", myGenerator, fclass);
        }
    
        public void generate(CodeStream genPrinter) {
            genPrinter.print(fclass.numDelaySwitches());
        }
    
    }

    public  class DAETag_n_spatialdists extends DAETag {
        
        public DAETag_n_spatialdists(AbstractGenerator myGenerator, FClass fclass) {
            super("n_spatialdists", myGenerator, fclass);
        }
        
        public void generate(CodeStream genPrinter) {
            genPrinter.print(fclass.mySpatialDistExps().size());
        }
    }

    public  class DAETag_numSpatialDistSwitchingFunctions extends DAETag {
        
        public DAETag_numSpatialDistSwitchingFunctions(AbstractGenerator myGenerator, FClass fclass) {
            super("n_spatialdist_switches", myGenerator, fclass);
        }
    
        public void generate(CodeStream genPrinter) {
            genPrinter.print(fclass.numSpatialDistSwitches());
        }
    
    }

    public  class DAETag_C_delay_init extends DAETag {
        
        public DAETag_C_delay_init(AbstractGenerator myGenerator, FClass fclass) {
            super("C_delay_init", myGenerator, fclass);
        }
        
        public void generate(CodeStream genPrinter) {
            CodePrinter p = ASTNode.printer_C;
            String indent = p.indent("");
            for (FDelayExp d : fclass.myDelayExps()) {
                d.genInitVarDecls_C(p, genPrinter, indent);
            }
            for (FSpatialDistExp d : fclass.mySpatialDistExps()) {
                d.genInitVarDecls_C(p, genPrinter, indent);
            }
            for (FDelayExp d : fclass.myDelayExps()) {
                d.genInit_C(p, genPrinter, indent);
            }
            for (FSpatialDistExp d : fclass.mySpatialDistExps()) {
                d.genInit_C(p, genPrinter, indent);
            }
        }
    }

    public  class DAETag_C_delay_sample extends DAETag {
        
        public DAETag_C_delay_sample(AbstractGenerator myGenerator, FClass fclass) {
            super("C_delay_sample", myGenerator, fclass);
        }
        
        public void generate(CodeStream genPrinter) {
            CodePrinter p = ASTNode.printer_C;
            String indent = p.indent("");
            for (FDelayExp d : fclass.myDelayExps()) {
                d.genSampleVarDecls_C(p, genPrinter, indent);
            }
            for (FSpatialDistExp d : fclass.mySpatialDistExps()) {
                d.genSampleVarDecls_C(p, genPrinter, indent);
            }
            for (FDelayExp d : fclass.myDelayExps()) {
                d.genSample_C(p, genPrinter, indent);
            }
            for (FSpatialDistExp d : fclass.mySpatialDistExps()) {
                d.genSample_C(p, genPrinter, indent);
            }
        }
    }

    public  CGenerator(Printer expPrinter, char escapeCharacter,
			FClass fclass) {
		super(expPrinter, escapeCharacter, fclass);
	}

    public  class ECETag_external_includes extends ExternalCEvalTag {
        public ECETag_external_includes(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_external_includes", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            String inc = ext.include();
            if (inc != null)
                str.println(inc);
        }
    }

    public  class ECETag_record_definitions extends ExternalCEvalTag {
        public ECETag_record_definitions(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_record_definitions", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            for (FType rec : ext.usedTypes().values()) {
                if (rec.isRecord()) {
                    p.print(rec, str, indent);
                }
            }
        }
    }

    public  class ECETag_decl extends ExternalCEvalTag {
        public ECETag_decl(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_decl", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            TypePrinter_C tp = new DeclPrinter_ECE(p, str, tempMap, cgc);
            for (CommonVariableDecl cvd : ext.externalObjectsToSerialize()) {
                 tp.reset(cvd.name_C(), null, cvd.size().isUnknown(), false, indent);
                 tp.print(cvd.type());
            }
        }
    }

    public  class ECETag_free extends ExternalCEvalTag {
        public ECETag_free(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_free", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            TypePrinter_C tp = new FreePrinter_ECE(p, str, tempMap, cgc);
            for (CommonVariableDecl cvd : ext.externalObjectsToSerialize()) {
                 tp.reset(cvd.name_C(), null, cvd.size().isUnknown(), false, indent);
                 tp.printExt((FExternalObjectType)cvd.type());
            }
        }
    }

    public  class ECETag_setup_decl extends ExternalCEvalTag {
        public ECETag_setup_decl(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_setup_decl", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            TypePrinter_C tp = new DeclPrinter_ECE(p, str, tempMap, cgc);
            ext.genSerializeComps_C(tp, indent, cgc, tempMap, ext.externalObjectsToSerialize());
        }
    }

    public  class ECETag_setup_init extends ExternalCEvalTag {
        public ECETag_setup_init(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_setup_init", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            TypePrinter_C tp = new InitPrinter_ECE(p, str, tempMap, cgc);
            ext.genSerialize_C(tp, indent, cgc, tempMap, ext.externalObjectsToSerialize());
        }
    }

    public  class ECETag_setup_free extends ExternalCEvalTag {
        public ECETag_setup_free(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_setup_free", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            TypePrinter_C tp = new FreePrinter_ECE(p, str, tempMap, cgc);
            ext.genSerializeComps_C(tp, indent, cgc, tempMap, ext.externalObjectsToSerialize());
        }
    }

    public  class ECETag_calc_decl extends ExternalCEvalTag {
        public ECETag_calc_decl(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_calc_decl", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            TypePrinter_C tp = new DeclPrinter_ECE(p, str, tempMap, cgc);
            ext.genSerialize_C(tp, indent, cgc, tempMap, ext.functionArgsToSerialize());
            ext.genVarDecls_C(p, str, indent);
        }
    }

    public  class ECETag_calc_init extends ExternalCEvalTag {
        public ECETag_calc_init(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_calc_init", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            TypePrinter_C tp = new InitPrinter_ECE(p, str, tempMap, cgc);
            ext.genSerialize_C(tp, indent, cgc, tempMap, ext.functionArgsToSerialize());
        }
    }

    public  class ECETag_calc extends ExternalCEvalTag {
        public ECETag_calc(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_calc", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            indent = p.indent(indent);
            ext.genSerializeCalc_C(p, str, indent, cgc, tempMap);
        }
    }

    public  class ECETag_calc_free extends ExternalCEvalTag {
        public ECETag_calc_free(AbstractGenerator myGenerator, FClass fClass) {
            super("ECE_calc_free", myGenerator, fClass);
        }
        
        @Override
        public void generate(CodePrinter p, CodeStream str, String indent, FExternalStmt ext,
                CodeGenContext cgc, Map<String,String> tempMap) {
            indent = p.indent(indent);
            TypePrinter_C tp = new FreePrinter_ECE(p, str, tempMap, cgc);
            ext.genSerialize_C(tp, indent, cgc, tempMap, ext.functionArgsToSerialize());
        }
    }

    public  class DAETag_C_dynamic_state_add_call extends DAETag {

        public DAETag_C_dynamic_state_add_call(AbstractGenerator myGenerator, FClass fclass) {
            super("C_dynamic_state_add_call", myGenerator, fclass);
            addOptions("generate_ode");
        }

        public void generate(CodeStream genPrinter) {
            CodePrinter p = ASTNode.printer_C;
            fclass.getDynamicStateManager().genDynamicStateAddCall_C(p, genPrinter, p.indent(""));
        }
    }

    public  class DAETag_C_dynamic_state_coefficients extends DAETag {
        
        public DAETag_C_dynamic_state_coefficients(AbstractGenerator myGenerator, FClass fclass) {
            super("C_dynamic_state_coefficients", myGenerator, fclass);
            addOptions("generate_ode");
        }
        
        public void generate(CodeStream genPrinter) {
            CodePrinter p = ASTNode.printer_C;
            fclass.getDynamicStateManager().genDynamicStateCoefficients_C(p, genPrinter, "");
        }
    }


}
