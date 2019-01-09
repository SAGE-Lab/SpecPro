package it.sagelab.specpro.reasoners.translators;

import java.io.PrintStream;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.sagelab.specpro.models.translators.PSP2LTL;


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

    @Override
    public FormulaPrinter getFormulaPrinter(PrintStream stream) {
        FormulaPrinter formulaPrinter = new FormulaPrinter(stream);
        formulaPrinter.setNotOperator("~");
        formulaPrinter.setGloballyOperator("G");
        formulaPrinter.setEquivalenceOperator("F");
        formulaPrinter.setNextOperator("X");
        formulaPrinter.setAndOperator("&");
        formulaPrinter.setOrOperator("|");
        formulaPrinter.setUntilOperator("U");
        formulaPrinter.setDecomposeXor(true);
        formulaPrinter.setDecomposeImplication(true);
        return formulaPrinter;
    }

}
