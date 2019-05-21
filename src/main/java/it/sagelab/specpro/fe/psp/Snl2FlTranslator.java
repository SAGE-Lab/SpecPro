package it.sagelab.specpro.fe.psp;

import it.sagelab.specpro.fe.psp.parser.RequirementsBuilder;

import java.io.PrintStream;

public interface Snl2FlTranslator {

    void translate(RequirementsBuilder builder, PrintStream stream);
}
