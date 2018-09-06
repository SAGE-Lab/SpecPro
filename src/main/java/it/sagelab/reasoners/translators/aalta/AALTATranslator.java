package it.sagelab.reasoners.translators.aalta;

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
 * The Class AALTATranslator.
 *
 * @author Massimo Narizzano
 */
public class AALTATranslator extends LTLToolTranslator {

    private static final Set<String> forbiddenVarNames = Stream.of("X", "N", "U", "R", "V", "G", "F", "true", "false", "TRUE", "FALSE").collect(Collectors.toSet());

    /**
     * Instantiates a new nu SMV psp2ltl.
     *
     * @param translator the psp2ltl
     */
    public AALTATranslator(PSP2LTL translator) {
        super(translator, forbiddenVarNames);
    }

    /**
     * Instantiates a new nu SMV psp2ltl.
     *
     */
    public AALTATranslator() { super(forbiddenVarNames); }

    /**
     * Translate a set of psp with signals, into a LTL formula using the AALTA input syntax.
     * The final formula is composed by two formulas :
     *  - \phi_A : derived by the arithmetic constraint
     *  - \phi_R : i.e. the translation of the psp into LTL
     *  Finally the formula checked is the  \phi_A \and \not\phi_R.
     * 
     * @param stream The stream on which the LTL formula is written
     */
    public void translate(PrintStream stream) throws IOException {
    	LTLAALTAVisitor visitor = new LTLAALTAVisitor(stream);
        
        List<Formula> ltlFormulae = psp2ltl.translate();
        List<Formula> invariants = psp2ltl.getInvariants();
        // Print the arithmetic constraints (\phi_A)
        stream.print("G(");
        this.printFormulaeInConjunction(stream, visitor, invariants);
        stream.print(")");
        // Print the psp constraints (\phi_R)
        stream.print(" & (");
        this.printFormulaeInConjunction(stream, visitor, ltlFormulae);
        stream.print(")");
    }


}
