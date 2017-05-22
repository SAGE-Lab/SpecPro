package snl2fl.fl.parser;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.BinaryOperator;
import snl2fl.fl.elements.Formula;
import snl2fl.fl.elements.UnaryOperator;

/**
 * The Class FLBuilder.
 *
 * @author Simone Vuotto
 */
public class FLBuilder extends FLGrammarBaseListener {
    
    /**  maps nodes to Expressions with Map<ParseTree,Expression>. */
    ParseTreeProperty<Formula> values = new ParseTreeProperty<>();
    
    /** The root. */
    Formula root = null;

    /**
     * Gets the formula.
     *
     * @return the formula
     */
    public Formula getFormula(){
        return root;
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.parser.FLGrammarBaseListener#exitFl(snl2fl.fl.parser.FLGrammarParser.FlContext)
     */
    @Override
    public void exitFl(FLGrammarParser.FlContext ctx) {
        root = values.get(ctx.formula());
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.parser.FLGrammarBaseListener#exitBracketFormula(snl2fl.fl.parser.FLGrammarParser.BracketFormulaContext)
     */
    @Override
    public void exitBracketFormula(FLGrammarParser.BracketFormulaContext ctx) {
        values.put(ctx, values.get(ctx.formula()));
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.parser.FLGrammarBaseListener#exitUnaryOp(snl2fl.fl.parser.FLGrammarParser.UnaryOpContext)
     */
    @Override
    public void exitUnaryOp(FLGrammarParser.UnaryOpContext ctx) {
        Formula f = values.get(ctx.formula());
        UnaryOperator.Operator op = UnaryOperator.Operator.getOp(ctx.getChild(0).getText());
        values.put(ctx, new UnaryOperator(f, op));
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.parser.FLGrammarBaseListener#exitBinaryLogicOp(snl2fl.fl.parser.FLGrammarParser.BinaryLogicOpContext)
     */
    @Override
    public void exitBinaryLogicOp(FLGrammarParser.BinaryLogicOpContext ctx) {
        Formula leftFormula = values.get(ctx.formula(0));
        Formula rightFormula = values.get(ctx.formula(1));
        BinaryOperator.Operator op = BinaryOperator.Operator.getOp(ctx.getChild(1).toString());
        values.put(ctx, new BinaryOperator(leftFormula, rightFormula, op));
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.parser.FLGrammarBaseListener#exitBinaryOp(snl2fl.fl.parser.FLGrammarParser.BinaryOpContext)
     */
    @Override
    public void exitBinaryOp(FLGrammarParser.BinaryOpContext ctx) {
        Formula leftFormula = values.get(ctx.formula(0));
        Formula rightFormula = values.get(ctx.formula(1));
        BinaryOperator.Operator op = BinaryOperator.Operator.getOp(ctx.getChild(1).toString());
        values.put(ctx, new BinaryOperator(leftFormula, rightFormula, op));
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.parser.FLGrammarBaseListener#enterAtom(snl2fl.fl.parser.FLGrammarParser.AtomContext)
     */
    @Override
    public void enterAtom(FLGrammarParser.AtomContext ctx) {
        values.put(ctx, new Atom(ctx.ATOM().getText()));
    }
}
