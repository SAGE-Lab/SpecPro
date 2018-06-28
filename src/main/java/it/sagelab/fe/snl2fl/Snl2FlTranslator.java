package it.sagelab.fe.snl2fl;

import it.sagelab.fe.snl2fl.parser.RequirementsBuilder;

import java.io.IOException;
import java.io.OutputStream;

public interface Snl2FlTranslator {

    void translate(RequirementsBuilder builder, OutputStream stream) throws IOException;
}
