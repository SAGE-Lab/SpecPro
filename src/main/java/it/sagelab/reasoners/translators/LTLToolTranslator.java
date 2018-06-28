package it.sagelab.reasoners.translators;

import it.sagelab.fe.snl2fl.Snl2FlTranslator;
import it.sagelab.models.ltl.LTLTranslator;
import it.sagelab.fe.snl2fl.parser.RequirementsBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public abstract class LTLToolTranslator implements Snl2FlTranslator {

    /** The intermediate ltl translator. */
    final protected LTLTranslator translator;

    public LTLToolTranslator() {
        translator = new LTLTranslator();
    }

    public LTLToolTranslator(LTLTranslator translator) {
        this.translator = translator;
    }

    public LTLTranslator getLTLTranslator() {
        return translator;
    }

    public abstract void translate(PrintStream stream);

    public void translate(RequirementsBuilder builder, OutputStream stream) throws IOException {
        translator.setContext(builder);
        translate(new PrintStream(stream));
    }
}
