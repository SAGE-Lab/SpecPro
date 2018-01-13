package snl2fl;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import snl2fl.ltl.LTLToolTranslator;
import snl2fl.req.parser.RequirementsBuilder;
import snl2fl.req.parser.RequirementsGrammarLexer;
import snl2fl.req.parser.RequirementsGrammarParser;
import snl2fl.req.parser.ThrowingErrorListener;

import java.io.IOException;
import java.io.OutputStream;

public class Snl2FlParser {

    private CharStream inStream;
    private OutputStream outStream;

    private RequirementsBuilder builder;

    public Snl2FlParser(CharStream inStream, OutputStream outStream) {
        this.inStream = inStream;
        this.outStream = outStream;
    }

    public Snl2FlParser parse() throws Snl2FlException {
        try {
            RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(inStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            RequirementsGrammarParser parser = new RequirementsGrammarParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);

            ParseTreeWalker walker = new ParseTreeWalker();
            RequirementsBuilder builder = new RequirementsBuilder();
            walker.walk(builder, parser.file());

            return this;
        } catch (ParseCancellationException e) {
            throw new Snl2FlException(e.getMessage());
        }
    }

    public Snl2FlParser translate(Snl2FlTranslator translator) throws Snl2FlException {
        if(builder == null) {
            throw  new Snl2FlException("Before translation the input must be parsed");

        }
        try {
            translator.translate(builder, outStream);
            return this;
        } catch (IOException e) {
            throw new Snl2FlException(e.getMessage());
        }

    }


}
