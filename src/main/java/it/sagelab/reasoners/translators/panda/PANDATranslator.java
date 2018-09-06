package it.sagelab.reasoners.translators.panda;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.sagelab.models.translators.PSP2LTL;
import it.sagelab.models.ltl.Formula;
import it.sagelab.reasoners.translators.LTLToolTranslator;

/**
 * The Class PANDATranslator.
 *
 * @author Massimo Narizzano
 */
public class PANDATranslator extends LTLToolTranslator {

    private static final Set<String> forbiddenVarNames = Stream.of("X", "N", "U", "R", "V", "G", "F", "true", "false", "TRUE", "FALSE").collect(Collectors.toSet());


    /**
     * Instantiates a new nu SMV psp2ltl.
     *
     * @param translator the psp2ltl
     */
    public PANDATranslator(PSP2LTL translator) { super(translator, forbiddenVarNames); }

    /**
     * Instantiates a new nu SMV psp2ltl.
     *
     */
    public PANDATranslator() { super(forbiddenVarNames); }

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
    public void translate(PrintStream stream) throws IOException {
    	LTLPANDAVisitor visitor = new LTLPANDAVisitor(stream);
        
        List<Formula> ltlFormulae = psp2ltl.translate();
        List<Formula> invariants = psp2ltl.getInvariants();
        
        // Print the arithmetics constraints (\phi_A)
        stream.print("G(");
        this.printFormulaeInConjunction(stream, visitor, invariants);
        stream.print(")");
        // Print the psp constraints (\phi_R)
        stream.print(" & (");
        this.printFormulaeInConjunction(stream, visitor, ltlFormulae);
        stream.print(")");
    }

}
