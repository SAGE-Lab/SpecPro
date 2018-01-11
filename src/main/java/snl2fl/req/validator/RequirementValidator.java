package snl2fl.req.validator;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import snl2fl.req.parser.RequirementsBuilder;
import snl2fl.req.parser.RequirementsGrammarLexer;
import snl2fl.req.parser.RequirementsGrammarParser;

public class RequirementValidator {
    
    private String errorMessage;

    public boolean isValid(String req) {
        try {
            RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(new ANTLRInputStream(req));
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            RequirementsGrammarParser parser = new RequirementsGrammarParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);

            ParseTreeWalker walker = new ParseTreeWalker();
            RequirementsBuilder builder = new RequirementsBuilder();
            walker.walk(builder, parser.requirement());

            return true;
        } catch (ParseCancellationException e) {
            errorMessage = e.getMessage();
            return false;
        }

    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

}
