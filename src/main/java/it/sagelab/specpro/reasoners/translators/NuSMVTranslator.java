package it.sagelab.specpro.reasoners.translators;

import java.io.PrintStream;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import it.sagelab.specpro.models.translators.PSP2LTL;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.Formula;


public class NuSMVTranslator extends LTLToolTranslator {

    private static final Set<String> forbiddenVarNames = Stream.of("MODULE", "DEFINE", "MDEFINE", "CONSTANTS", "VAR", "IVAR", "FROZENVAR",
            "INIT", "TRANS", "INVAR", "SPEC", "CTLSPEC", "LTLSPEC", "PSLSPEC", "COMPUTE", "NAME", "INVARSPEC", "FAIRNESS",
            "JUSTICE", "COMPASSION", "ISA", "ASSIGN", "CONSTRAINT", "SIMPWFF", "CTLWFF", "LTLWFF", "PSLWFF", "COMPWFF",
            "IN", "MIN", "MAX", "MIRROR", "PRED", "PREDICATES", "process", "array", "of", "boolean", "integer", "real",
            "word", "word1", "bool", "signed", "unsigned", "extend", "resize", "sizeof", "uwconst", "swconst", "EX", "AX",
            "EF", "AF", "EG", "AG", "E", "F", "O", "G", "H", "X", "Y", "Z", "A", "U", "S", "V", "T", "BU", "EBF", "ABF",
            "EBG", "ABG", "case", "esac", "mod", "next", "init", "union", "in", "xor", "xnor", "self", "TRUE", "FALSE",
            "count").collect(Collectors.toSet());

    /** Property to indicate if the translation should not use INVAR statements*/
    private boolean noinvar;

    public NuSMVTranslator(PSP2LTL translator) { super(translator, forbiddenVarNames); }

    public NuSMVTranslator() { super(forbiddenVarNames); }


    public NuSMVTranslator setNoinvar(boolean noinvar) {
        this.noinvar = noinvar;
        return this;
    }

    public boolean isNoinvar() {
        return noinvar;
    }

    /**
     * Translate the formula in input into a model checking problem with universal automata.
     * Two type of translation are possible: 
     * (1) Adding the arithmetic constraints as invar in the model, i.e. pruning the amount of possible 
     *     behaviors of the universal automata ("invar" value of the option)
     * (2) Adding the arithmetic constraints in the LTLSPEC specification (noinvar option)
     *
     * @param stream the stream
     */
    public void translate(PrintStream stream) {
        FormulaPrinter formulaPrinter = getFormulaPrinter(stream);
        List<Formula> invariants = psp2ltl.getInvariants();
        List<Formula> ltlFormulae = psp2ltl.translate();

        stream.println("MODULE main");
        this.printVariables(stream);
        stream.println();

        Formula conjFormula = BinaryOperator.conjunctiveFormula(ltlFormulae);

        if (!noinvar) {
        	// Writing the translation constraining the Universal model
        	for(Formula inv : invariants) {
        		stream.print("INVAR ");
        		formulaPrinter.print(inv);
        		stream.print(";\n");
        	}

        	// Print the negation of the formula representing the psp
        	stream.println();
        	stream.println("-- Negated Formula");
        	stream.print("LTLSPEC !(");
        	formulaPrinter.print(conjFormula);
        	stream.println(");");
        	      
        } else {
        	// Writing the translation without the INVAR 
        	stream.println("-- Negated Formula");
        	stream.print("LTLSPEC !(");

        	if(invariants.size() > 0) {
                Formula invariantFormula = new UnaryOperator(BinaryOperator.conjunctiveFormula(invariants), UnaryOperator.Operator.GLOBALLY);
                conjFormula = new BinaryOperator(invariantFormula, conjFormula, BinaryOperator.Operator.AND);
            }
        	formulaPrinter.print(conjFormula);
        	stream.println(");");
        }
        
    }

    @Override
    public FormulaPrinter getFormulaPrinter(PrintStream stream) {
        FormulaPrinter formulaPrinter = new FormulaPrinter(stream);
        formulaPrinter.setNotOperator("!");
        formulaPrinter.setGloballyOperator("G");
        formulaPrinter.setEventuallyOperator("F");
        formulaPrinter.setNextOperator("X");
        formulaPrinter.setAndOperator("&");
        formulaPrinter.setOrOperator("|");
        formulaPrinter.setUntilOperator("U");
        formulaPrinter.setXorOperator("xor");
        formulaPrinter.setImplicationOperator("->");
        formulaPrinter.setEquivalenceOperator("<->");
        return formulaPrinter;
    }

    /**
     * Prints the variables.
     *
     * @param stream the stream
     */
    private void printVariables(PrintStream stream) {
        stream.println("VAR\n");
        // Print boolean variables
        for(String varName : psp2ltl.getBooleanAtoms().keySet())
            stream.println("\t" + varName + " : boolean;");
        // Print range variables encoded with atoms
        for(String name: psp2ltl.getRangeMap().keySet()) {
        	TreeMap<Float, Atom[]> t = psp2ltl.getRangeMap().get(name);
        	for(Float a_key : t.keySet()) {
        		Atom[] a = t.get(a_key);
        		stream.println("\t"+a[0].getName()+" : boolean; -- " + name + " < " + a_key);
                stream.println("\t"+a[1].getName()+" : boolean; -- " + name + " = " + a_key);
        	}
        }
    }


}
