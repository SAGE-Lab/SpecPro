package snl2fl;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import snl2fl.req.parser.RequirementsBuilder;
import snl2fl.req.parser.RequirementsGrammarLexer;
import snl2fl.req.parser.RequirementsGrammarParser;
import snl2fl.req.parser.ThrowingErrorListener;
import snl2fl.req.requirements.Requirement;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Snl2FlParser {

    private RequirementsBuilder builder;
    private boolean parsed;



    public Snl2FlParser() {
        builder = new RequirementsBuilder();
        parsed = false;
    }


    /**
     * Reset the internal state of the parser
     *
     * @return this
     */
    public Snl2FlParser reset() {
        builder = new RequirementsBuilder();
        parsed = false;
        return this;
    }


    /**
     * Return the builder used for parsing and translation
     *
     * @return builder
     */
    public RequirementsBuilder getBuilder() {
        return builder;
    }


    /**
     * Returns the list of all parsed requirements
     * @return the list of requirements
     */
    public List<Requirement> getRequirements() { return builder.getRequirementList(); }


    /**
     * Returns the requirement object in position `index`
     * @param index The index of the requirement to retrieve
     * @return the requirement in
     */
    public Requirement getRequirement(int index) { return builder.getRequirementList().get(index); }


    /**
     * Parse the input file with the given path
     *
     * @param filePath The path of the file
     * @return a reference to the parser object
     * @throws Snl2FlException
     * @throws IOException
     */
    public Snl2FlParser parseFile(String filePath) throws Snl2FlException, IOException {
        return parse(CharStreams.fromFileName(filePath));
    }


    /**
     * Parse an input string
     *
     * @param text The string to parse
     * @return a reference to the parser object
     * @throws Snl2FlException
     */
    public Snl2FlParser parseString(String text) throws Snl2FlException {
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
    public Snl2FlParser parseStream(InputStream stream) throws Snl2FlException, IOException {
        return parse(CharStreams.fromStream(stream));
    }


    /**
     * Parse a generic char stream
     *
     * @param inStream The stream to parse
     * @return this
     * @throws Snl2FlException
     */
    public Snl2FlParser parse(CharStream inStream) throws Snl2FlException {
        try {
            RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(inStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            RequirementsGrammarParser parser = new RequirementsGrammarParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(builder, parser.file());

            parsed = true;

            return this;
        } catch (ParseCancellationException e) {
            throw new Snl2FlException(e.getMessage());
        }
    }

    /**
     * Translated the parsed content with the given translator and output the result in outStream
     *
     * @param translator The translator to use
     * @param outStream The output stream
     * @return this
     * @throws Snl2FlException
     */
    public Snl2FlParser translate(Snl2FlTranslator translator, OutputStream outStream) throws Snl2FlException {
        if(!parsed)
            throw  new Snl2FlException("No input parsed, nothing to translate!");

        try {
            translator.translate(builder, outStream);
            return this;
        } catch (IOException e) {
            throw new Snl2FlException(e.getMessage());
        }

    }


}
