package it.sagelab.reasoners.translators.panda;

import java.io.PrintStream;
import java.util.List;
import java.util.TreeMap;

import it.sagelab.models.ltl.elements.Atom;
import it.sagelab.models.ltl.elements.Formula;
import it.sagelab.models.ltl.LTLContext;
import it.sagelab.reasoners.translators.LTLToolTranslator;
import it.sagelab.models.ltl.LTLTranslator;
import it.sagelab.models.psp.expressions.BooleanVariableExpression;
import it.sagelab.models.psp.expressions.VariableExpression;

/**
 * The Class PANDATranslator.
 *
 * @author Massimo Narizzano
 */
public class PANDATranslator extends LTLToolTranslator {

    /**
     * Instantiates a new nu SMV translator.
     *
     * @param translator the translator
     */
    public PANDATranslator(LTLTranslator translator) { super(translator); }

    /**
     * Instantiates a new nu SMV translator.
     *
     */
    public PANDATranslator() {  }

    /**
     * Translate a set of psp with signals, into a LTL formula using the PANDA input syntax.
     * The final formula is composed by two formulas :
     *  - \phi_A : derived by the aritmetic constraint
     *  - \phi_R : i.e. the translation of the psp into LTL
     *  Finally the formula cheched is the negated one:
     *  \phi_A -> \not\phi_R.
     * 
     * @param stream The stream on which the LTL formula is written
     */
    public void translate(PrintStream stream) {
    	LTLPANDAVisitor visitor = new LTLPANDAVisitor(stream);
        
        List<Formula> ltlFormulae = translator.translate();
        List<Formula> invariants = translator.getInvariants();
        // Since PANDA tool negate the input formula we add the negation
        // at the begin of the formula.
        stream.print("~(");
        
        // Print the arithmetics constraints (\phi_A)
        stream.print("~G(");
        for(int i=0; i < invariants.size(); i++) {
        	Formula inv = invariants.get(i);
        	stream.print("(");
            inv.accept(visitor);
            stream.print(")");
            if(i < invariants.size() - 1)
                stream.print(" & ");
        }
        stream.print(")");
        // Print the psp constraints (\phi_R)
        stream.print(" | ~(");
        for(int i=0; i < ltlFormulae.size(); ++i) {
            Formula formula = ltlFormulae.get(i);
            stream.print("(");
            formula.accept(visitor);
            stream.print(")");
            if(i < ltlFormulae.size() - 1)
                stream.print(" & ");
        }
        stream.print(")");

        // END of the formula
        stream.print(")");
    }

    /**
     * Prints the variables.
     *
     * @param stream the stream
     */
    public void printVariables(PrintStream stream) {
        stream.println("VAR\n");
        LTLContext context = translator.getContext();
        // Print boolean variables
        for(VariableExpression ve : context.getSymbolTable().values())
            if(ve instanceof BooleanVariableExpression)
                stream.println("\t"+ve.getName()+" : boolean;");
        // Print range variables encoded with atoms
        for(TreeMap<Float, Atom[]> t : context.getRangeMap().values())
            for(Atom[] a : t.values()) {
                stream.println("\t"+a[0].getName()+" : boolean;");
                stream.println("\t"+a[1].getName()+" : boolean;");

            }
    }


}
