package snl2fl.ltl.nusmv;

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
 * The Class NuSMVTranslator.
 *
 * @author Simone Vuotto
 */
public class NuSMVTranslator {
    
    /** The translator. */
    private final LTLTranslator translator;

    /**
     * Instantiates a new nu SMV translator.
     *
     * @param translator the translator
     */
    public NuSMVTranslator(LTLTranslator translator) {
        this.translator = translator;
    }

    /**
     * Translate the formula in input into a model checking problem with universal automata.
     * Two type of translation are possible: 
     * (1) Adding the arithmetic constraints as invar in the model, i.e. pruning the amount of possible 
     *     behaviors of the universal automata ("invar" value of the option)
     * (2) Adding the arithmetic constraints in the LTLSPEC specification (noinvar option)
     * 
     * @param option indicate the type of output: invar as (1) or noinvar as (2) 
     * @param stream the stream
     */
    public void translate(PrintStream stream, String option) {
        stream.println("MODULE main");
        this.printVariables(stream);
        stream.println();
        LTLNuSMVVisitor visitor = new LTLNuSMVVisitor(stream);
        List<Formula> invariants = translator.getInvariants();

        if (option.equals("invar")) { 
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
        	for(int i=0; i < ltlFormulae.size(); ++i) {
        		Formula formula = ltlFormulae.get(i);
        		stream.print("(");
        		formula.accept(visitor);
        		stream.print(")");
        		if(i < ltlFormulae.size() - 1)
        			stream.print(" & ");
        	}
        	stream.print(");\n");
        	stream.println();
        	
        	// Print the formula representing the requirements directly         
        	stream.println("-- Directed Formula");
        	stream.print("LTLSPEC (");
        	for(int i=0; i < ltlFormulae.size(); ++i) {
        		Formula formula = ltlFormulae.get(i);
        		stream.print("(");
        		formula.accept(visitor);
        		stream.print(")");
        		if(i < ltlFormulae.size() - 1)
        			stream.print(" & ");
            }
        	stream.print(");\n");      
        }else if (option.equals("noinvar")) { 
        	// Writing the translation without the INVAR 
        	stream.println("-- Negated Formula");
        	stream.print("LTLSPEC ");
        	stream.print("!G(");
        	for(int i=0; i < invariants.size(); i++) {
            	Formula inv = invariants.get(i);
            	stream.print("(");
                inv.accept(visitor);
                stream.print(")");
                if(i < invariants.size() - 1)
                    stream.print(" & ");
            }
        	stream.print(")");
        	
        	List<Formula> ltlFormulae = translator.translate();
        	// Print the requirements constraints (\phi_R)	
        	stream.print(" | !(");
        	for(int i=0; i < ltlFormulae.size(); ++i) {
        		Formula formula = ltlFormulae.get(i);
        		stream.print("(");
        		formula.accept(visitor);
        		stream.print(")");
        		if(i < ltlFormulae.size() - 1)
        			stream.print(" & ");
        	}
        	stream.print(");\n");      
        	
        	stream.println("-- Direct Formula");
        	stream.print("LTLSPEC ");
        	stream.print("!G(");
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
        	stream.print(" | (");
        	for(int i=0; i < ltlFormulae.size(); ++i) {
        		Formula formula = ltlFormulae.get(i);
        		stream.print("(");
        		formula.accept(visitor);
        		stream.print(")");
        		if(i < ltlFormulae.size() - 1)
        			stream.print(" & ");
        	}
        	stream.print(");\n");  	
        } else {
        	// Thrown an exception
        }
        
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
