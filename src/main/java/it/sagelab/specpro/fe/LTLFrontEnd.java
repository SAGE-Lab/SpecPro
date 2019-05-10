package it.sagelab.specpro.fe;

import it.sagelab.specpro.fe.ltl.parser.LTLBuilder;
import it.sagelab.specpro.fe.ltl.parser.LTLGrammarLexer;
import it.sagelab.specpro.fe.ltl.parser.LTLGrammarParser;
import it.sagelab.specpro.models.ltl.LTLSpec;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public class LTLFrontEnd extends AbstractLTLFrontEnd {

    LTLBuilder builder = new LTLBuilder();

    @Override
    public Lexer getLexer(CharStream inStream) {
        return new LTLGrammarLexer(inStream);
    }

    @Override
    public Parser getParser(TokenStream tokens) {
        return new LTLGrammarParser(tokens);
    }

    @Override
    public ParseTree getParseTree(Parser parser) {
        return ((LTLGrammarParser) parser).file();
    }

    @Override
    public ParseTreeListener getParseTreeListener() {
        return builder;
    }

    @Override
    public LTLSpec getLTLSpec() {
        return builder.getSpec();
    }
}
