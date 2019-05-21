package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.ltl.UnaryOperator;

import java.io.PrintStream;
import java.util.List;

public class PltlMupTranslator extends AALTATranslator {

    public PltlMupTranslator() {
        super();
        varPrefix = "X";
    }

    public void translate(PrintStream stream, LTLSpec spec) {
        FormulaPrinter formulaPrinter = getFormulaPrinter(stream);
        List<Formula> ltlFormulae = spec.getRequirements();
        List<Formula> invariants = spec.getInvariants();
        if(invariants.size() > 0) {
            Formula invariantFormula = new UnaryOperator(BinaryOperator.conjunctiveFormula(invariants), UnaryOperator.Operator.GLOBALLY);
            formulaPrinter.print(invariantFormula);
            stream.println();
        }
        for(Formula f: ltlFormulae) {
            formulaPrinter.print(f);
            stream.println();
        }

    }
}
