package snl2fl;

import snl2fl.req.parser.RequirementsBuilder;

import java.io.IOException;
import java.io.OutputStream;

public interface Snl2FlTranslator {

    void translate(RequirementsBuilder builder, OutputStream stream) throws IOException;
}
