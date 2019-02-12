package it.sagelab.specpro.reasoners.translators;

import java.io.PrintStream;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.sagelab.specpro.models.translators.PSP2LTL;


public class PANDATranslator extends AALTATranslator {

    public PANDATranslator() { super(); }

    @Override
    public FormulaPrinter getFormulaPrinter(PrintStream stream) {
        FormulaPrinter formulaPrinter = new FormulaPrinter(stream);
        formulaPrinter.setNotOperator("~");
        formulaPrinter.setGloballyOperator("G");
        formulaPrinter.setEventuallyOperator("F");
        formulaPrinter.setNextOperator("X");
        formulaPrinter.setAndOperator("&");
        formulaPrinter.setOrOperator("|");
        formulaPrinter.setUntilOperator("U");
        formulaPrinter.setDecomposeXor(true);
        formulaPrinter.setDecomposeImplication(true);
        return formulaPrinter;
    }

}
