package it.sagelab.specpro.fe.snl2fl;

import it.sagelab.specpro.fe.snl2fl.parser.RequirementsBuilder;

import java.io.IOException;
import java.io.PrintStream;

public interface Snl2FlTranslator {

    void translate(RequirementsBuilder builder, PrintStream stream) throws IOException;
}
