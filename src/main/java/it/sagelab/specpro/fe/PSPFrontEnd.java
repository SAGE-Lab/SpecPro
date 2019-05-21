package it.sagelab.specpro.fe;

import it.sagelab.specpro.fe.psp.parser.RequirementsBuilder;
import it.sagelab.specpro.fe.psp.parser.RequirementsGrammarLexer;
import it.sagelab.specpro.fe.psp.parser.RequirementsGrammarParser;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.qualitative.QualitativeRequirement;
import it.sagelab.specpro.models.translators.PSP2LTL;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.ArrayList;

public class PSPFrontEnd extends AbstractLTLFrontEnd {

    private RequirementsBuilder builder = null;

    @Override
    public Lexer getLexer(CharStream inStream) {
        return new RequirementsGrammarLexer(inStream);
    }

    @Override
    public Parser getParser(TokenStream tokens) {
        return new RequirementsGrammarParser(tokens);
    }

    @Override
    public ParseTree getParseTree(Parser parser) {
        return ((RequirementsGrammarParser) parser).file();
    }

    @Override
    public ParseTreeListener getParseTreeListener() {
        builder = new RequirementsBuilder();
        return builder;
    }

    @Override
    public LTLSpec getLTLSpec() {

        ArrayList<QualitativeRequirement> requirements = new ArrayList<>();

        for(Requirement r : builder.getContext().getRequirementList()) {
            if (r instanceof QualitativeRequirement)
                requirements.add((QualitativeRequirement)r);
            else
                System.err.println("Requirement " + requirements.indexOf(r) + " is not a qualitative requirement, it is skipped.");
        }

        return new PSP2LTL().translate(requirements);
    }
}
