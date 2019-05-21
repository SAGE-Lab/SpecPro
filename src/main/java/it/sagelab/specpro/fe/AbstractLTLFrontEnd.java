package it.sagelab.specpro.fe;

import it.sagelab.specpro.fe.psp.Snl2FlException;
import it.sagelab.specpro.fe.psp.parser.ThrowingErrorListener;
import it.sagelab.specpro.models.ltl.LTLSpec;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractLTLFrontEnd {

    private Lexer lexer;
    private Parser parser;

    public AbstractLTLFrontEnd() {
    }

    /**
     * Parse the input file with the given path
     *
     * @param filePath The path of the file
     * @return an object containing the ltl specification
     * @throws Snl2FlException
     * @throws IOException
     */
    public LTLSpec parseFile(String filePath) throws Snl2FlException, IOException {
        return parse(CharStreams.fromFileName(filePath));
    }


    /**
     * Parse an input string
     *
     * @param text The string to parse
     * @return a reference to the parser object
     * @throws Snl2FlException
     */
    public LTLSpec parseString(String text) throws Snl2FlException {
        return parse(CharStreams.fromString(text));
    }

    /**
     * Parse the input stream
     *
     * @param stream The stream to parse
     * @return a reference to the parser object
     * @throws Snl2FlException
     * @throws IOException
     */
    public LTLSpec parseStream(InputStream stream) throws Snl2FlException, IOException {
        return parse(CharStreams.fromStream(stream));
    }

    public LTLSpec parse(CharStream inStream) {
        try {
            lexer = getLexer(inStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            parser = getParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(getParseTreeListener(), getParseTree(parser));

            return getLTLSpec();
        } catch (ParseCancellationException e) {
            throw new Snl2FlException(e.getMessage());
        }
    }

    /************************************************************************
     *  ABSTRACT METHODS
     ************************************************************************/

    public abstract Lexer getLexer(CharStream inStream);

    public abstract Parser getParser(TokenStream tokens);

    public abstract ParseTree getParseTree(Parser parser);

    public abstract ParseTreeListener getParseTreeListener();

    public abstract LTLSpec getLTLSpec();


}
