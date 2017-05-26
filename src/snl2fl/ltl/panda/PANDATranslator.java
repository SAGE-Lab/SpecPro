package snl2fl.ltl.panda;

import java.io.PrintStream;
import java.util.List;
import java.util.TreeMap;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.Formula;
import snl2fl.ltl.LTLContext;
import snl2fl.ltl.LTLTranslator;
import snl2fl.req.expressions.BooleanVariableExpression;
import snl2fl.req.expressions.VariableExpression;

/**
 * The Class PANDATranslator.
 *
 * @author Massimo Narizzano
 */
public class PANDATranslator {
    
    /** The translator. */
    private final LTLTranslator translator;

    /**
     * Instantiates a new nu SMV translator.
     *
     * @param translator the translator
     */
    public PANDATranslator(LTLTranslator translator) {
        this.translator = translator;
    }

    /**
     * Translate a set of requirements with signals, into a LTL formula using the PANDA input syntax.
     * The final formula is composed by two formulas :
     *  - \phi_A : derived by the aritmetic constraint
     *  - \phi_R : i.e. the translation of the requirements into LTL
     *  Finally the formula cheched is the negated one:
     *  \phi_A -> \phi_R.
     * 
     * @param stream The stream on which the LTL formula is written
     */
    public void translate(PrintStream stream) {
    	LTLPANDAVisitor visitor = new LTLPANDAVisitor(stream);
        
        List<Formula> ltlFormulae = translator.translate();
        List<Formula> invariants = translator.getInvariants();
        // Since PANDA tool negate the input formula we add the negation
        // at the begininng of the formula.
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
        // Print the requirements constraints (\phi_R)	
        stream.print(" | G(");
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
