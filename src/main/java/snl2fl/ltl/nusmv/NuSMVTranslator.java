package snl2fl.ltl.nusmv;

import java.io.PrintStream;
import java.util.List;
import java.util.TreeMap;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.Formula;
import snl2fl.ltl.LTLContext;
import snl2fl.ltl.LTLToolTranslator;
import snl2fl.ltl.LTLTranslator;
import snl2fl.req.expressions.BooleanVariableExpression;
import snl2fl.req.expressions.VariableExpression;

/**
 * The Class NuSMVTranslator.
 *
 * @author Simone Vuotto
 */
public class NuSMVTranslator extends LTLToolTranslator {

    /** Property to indicate if the translation should not use INVAR statements*/
    private boolean noinvar;

    /**
     * Instantiates a new nu SMV translator.
     *
     * @param translator the translator
     */
    public NuSMVTranslator(LTLTranslator translator) { super(translator); }

    /**
     * Instantiates a new nu SMV translator.
     *
     */
    public NuSMVTranslator() { }


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
        stream.println("MODULE main");
        this.printVariables(stream);
        stream.println();
        LTLNuSMVVisitor visitor = new LTLNuSMVVisitor(stream);
        List<Formula> invariants = translator.getInvariants();

        if (!noinvar) {
        	// Writing the translation constraining the Universal model
        	for(Formula inv : invariants) {
        		stream.print("INVAR ");
        		inv.accept(visitor);
        		stream.print(";\n");
        	}

        	// Print the negation of the formula representing the requirements 
        	stream.println();
        	List<Formula> ltlFormulae = translator.translate();
        	stream.println("-- Negated Formula");
        	stream.print("LTLSPEC !(");
        	this.printFormulae(stream, visitor, ltlFormulae);
        	stream.println(");");
        	      
        } else {
        	// Writing the translation without the INVAR 
        	stream.println("-- Negated Formula");
        	stream.print("LTLSPEC ");
        	stream.print("!G(");
            this.printFormulae(stream, visitor, invariants);
        	stream.print(")");
        	
        	List<Formula> ltlFormulae = translator.translate();
        	// Print the requirements constraints (\phi_R)	
        	stream.print(" | !(");
            this.printFormulae(stream, visitor, ltlFormulae);
        	stream.println(");");
        }
        
    }

    /**
     * Prints the list of formulae.
     * @param stream the stream
     * @param visitor the visitor to print the formulae
     * @param ltlFormulae the list of formulae
     */
    private void printFormulae(PrintStream stream, LTLNuSMVVisitor visitor, List<Formula> ltlFormulae) {

        for(int i=0; i < ltlFormulae.size(); ++i) {
            Formula formula = ltlFormulae.get(i);
            stream.print("(");
            formula.accept(visitor);
            stream.print(")");
            if(i < ltlFormulae.size() - 1)
                stream.print(" & ");
        }
    }

    /**
     * Prints the variables.
     *
     * @param stream the stream
     */
    private void printVariables(PrintStream stream) {
        stream.println("VAR\n");
        LTLContext context = translator.getContext();
        // Print boolean variables
        for(VariableExpression ve : context.getSymbolTable().values())
            if(ve instanceof BooleanVariableExpression)
                stream.println("\t"+ve.getName()+" : boolean;");
        // Print range variables encoded with atoms
        for(String name: context.getRangeMap().keySet()) {
        	TreeMap<Float, Atom[]> t = context.getRangeMap().get(name);
        	for(Float a_key : t.keySet()) {
        		Atom[] a = t.get(a_key);
        		stream.println("\t"+a[0].getName()+" : boolean; -- " + name + " < " + a_key);
                stream.println("\t"+a[1].getName()+" : boolean; -- " + name + " = " + a_key);
        	}
        }
    }


}
