package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.translators.PSP2LTL;

import java.io.PrintStream;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpotTranslator extends LTLToolTranslator {

    private PrintStream stream;

    private static final Set<String> forbiddenVarNames = Stream.of("X", "W", "U", "xor").collect(Collectors.toSet());

    public SpotTranslator(PSP2LTL translator) { super(translator, forbiddenVarNames); }


    public SpotTranslator() {
        super(forbiddenVarNames);
    }

    @Override
    public FormulaPrinter getFormulaPrinter(PrintStream stream) {
        FormulaPrinter formulaPrinter = new FormulaPrinter(stream);
        formulaPrinter.setNotOperator("!");
        formulaPrinter.setGloballyOperator("[]");
        formulaPrinter.setEquivalenceOperator("<>");
        formulaPrinter.setNextOperator("X");
        formulaPrinter.setAndOperator("&");
        formulaPrinter.setOrOperator("|");
        formulaPrinter.setUntilOperator("U");
        formulaPrinter.setXorOperator("xor");
        formulaPrinter.setImplicationOperator("->");
        formulaPrinter.setEquivalenceOperator("<->");
        return formulaPrinter;
    }

}