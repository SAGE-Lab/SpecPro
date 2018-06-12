package snl2fl.similarity;

import snl2fl.fl.patterns.Pattern;
import snl2fl.req.requirements.Scope;

import java.io.IOException;
import java.util.*;

public class PatternSimilarity {

    enum TokenType {
        VARIABLE,
        NUMBER,
        BINARY_OP,
        UNARY_OP,
        OPEN_BRACKET,
        CLOSE_BRACKET,
        NONE
    }

    private Set<String> variables;
    private ArrayList<String> expressions;
    private int index;

    private CosineSimilarity cosineSimilarity;

    public PatternSimilarity(Set<String> variables) {
        this.variables = variables;
        cosineSimilarity = new CosineSimilarity();
    }

    public String findMatch(String sentence) throws IOException {
        Text text = new Text(sentence);
        findExpressions(text);
        System.out.println(expressions);
        loadPatterns();
        List<Text> rankedDocuments = cosineSimilarity.computeRanking(text);

        for(Text t: rankedDocuments)
            System.out.println(t.getScore() + " : " + t);

        return rankedDocuments.get(0).toString();
    }

    private void loadPatterns() throws IOException {
        ArrayList<String> templates = new ArrayList<>();
        Map<String, Pattern> patternsMap = Pattern.loadPatterns(Pattern.PATTERNS_FILE);
        for(Pattern p : patternsMap.values()) {
            templates.add(p.getRequirement().getText().replaceAll("[QRPSTZ]", "%s"));
            if(p.getRequirement().getScope().getType() == Scope.Type.GLOBALLY)
                templates.add(p.getRequirement().getText().replaceAll("[QRPSTZ]", "%s").replace("Globally, it", "It"));
        }

        String[] args = new String[6];
        for(int i = 0; i < args.length; ++i)
            args[i] = expressions.get(i % expressions.size());

        for(String pattern: templates) {
            String t = String.format(pattern, args[0], args[1], args[2], args[3], args[4], args[5], args[3], args[4]);
            cosineSimilarity.addDocument(new Text(t));
        }
    }

    private TokenType getTokenType(int index, List<String> tokens) {
        final Set<String> binaryOps = new HashSet<>(Arrays.asList("and", "or", "xor", ">", ">=", "<", "<="));
        final Set<String> unaryOps = new HashSet<>(Arrays.asList("not"));

        if(index >= tokens.size() || index < 0)
            return TokenType.NONE;
        String t = tokens.get(index);
        if(t.equals("-LRB-"))
            return TokenType.OPEN_BRACKET;
        if(t.equals("-RRB-"))
            return TokenType.CLOSE_BRACKET;
        if(unaryOps.contains(t))
            return TokenType.UNARY_OP;
        if(variables.contains(t))
            return TokenType.VARIABLE;
        if(isNumber(t))
            return TokenType.NUMBER;
        if(binaryOps.contains(t))
            return TokenType.BINARY_OP;
        return TokenType.NONE;
    }

    private boolean isNumber(String n) {
        try  {
            float f = Float.parseFloat(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void findExpressions(Text text) {
        expressions = new ArrayList<>();
        while(index < text.getTokens().size()) {
            String expr = getExpression(text.getTokens());
            if(expr != null)
                expressions.add(expr);
        }
    }

    private String getExpression(List<String> tokens) {
        TokenType type = getTokenType(index++, tokens);
        if(type == TokenType.NONE)
            return null;
        String token = tokens.get(index - 1);
        String expr;
        switch (type) {
            case OPEN_BRACKET:
                expr = getExpression(tokens);
                if(expr == null)
                    return null;
                if(getTokenType(index++, tokens) == TokenType.CLOSE_BRACKET) {
                    if(getTokenType(index, tokens) == TokenType.BINARY_OP) {
                        String op = tokens.get(index++);
                        String expr2 = getExpression(tokens);
                        if(expr2 != null)
                            return "(" + expr + ") " + op + " " + expr2;
                    }
                    else
                        return "(" + expr + ")";
                } else
                    return expr;
            case UNARY_OP:
                expr = getExpression(tokens);
                return expr == null ? null : token + " " + expr;
            case VARIABLE:
            case NUMBER:
                if(getTokenType(index, tokens) == TokenType.BINARY_OP) {
                    String op = tokens.get(index++);
                    expr = getExpression(tokens);
                    if(expr != null)
                        return token + " " + op + " " + expr;
                }
                return token;
            default:
                return null;
        }
    }

}
