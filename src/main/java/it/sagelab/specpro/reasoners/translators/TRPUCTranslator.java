package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.translators.PSP2LTL;

import java.io.PrintStream;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TRPUCTranslator extends LTLToolTranslator {

    private static final Set<String> forbiddenVarNames = Stream.of("not", "always", "sometime", "next", "until", "true", "false", "TRUE", "FALSE").collect(Collectors.toSet());


    public TRPUCTranslator() {
        super(forbiddenVarNames);
        varPrefix = "X";
    }


    @Override
    public FormulaPrinter getFormulaPrinter(PrintStream stream) {
        FormulaPrinter formulaPrinter = new FlatFormulaPrinter(stream);
        formulaPrinter.setNotOperator("not");
        formulaPrinter.setGloballyOperator("always");
        formulaPrinter.setEventuallyOperator("sometime");
        formulaPrinter.setNextOperator("next");
        formulaPrinter.setAndOperator("&");
        formulaPrinter.setOrOperator("|");
        formulaPrinter.setUntilOperator("until");
        formulaPrinter.setDecomposeXor(true);
        formulaPrinter.setDecomposeImplication(true);
        return formulaPrinter;
    }

}
