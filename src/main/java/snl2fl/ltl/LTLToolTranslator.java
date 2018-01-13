package snl2fl.ltl;

import snl2fl.Snl2FlTranslator;
import snl2fl.req.parser.RequirementsBuilder;

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
