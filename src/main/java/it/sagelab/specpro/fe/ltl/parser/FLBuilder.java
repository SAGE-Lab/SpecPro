package it.sagelab.specpro.fe.ltl.parser;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/**
 * The Class FLBuilder.
 *
 * @author Simone Vuotto
 */
public class FLBuilder extends FLGrammarBaseListener {
    
    /**  maps nodes to Expressions with Map<ParseTree,Expression>. */
    private ParseTreeProperty<Formula> values = new ParseTreeProperty<>();
    
    /** The root. */
    private Formula root = null;

    /**
     * Gets the formula.
     *
     * @return the formula
     */
    public Formula getFormula(){
        return root;
    }

    /* (non-Javadoc)
     * @see FLGrammarBaseListener#exitFl(FLGrammarParser.FlContext)
     */
    @Override
    public void exitFl(FLGrammarParser.FlContext ctx) {
        root = values.get(ctx.formula());
    }

    /* (non-Javadoc)
     * @see FLGrammarBaseListener#exitBracketFormula(FLGrammarParser.BracketFormulaContext)
     */
    @Override
    public void exitBracketFormula(FLGrammarParser.BracketFormulaContext ctx) {
        values.put(ctx, values.get(ctx.formula()));
    }

    /* (non-Javadoc)
     * @see FLGrammarBaseListener#exitUnaryOp(FLGrammarParser.UnaryOpContext)
     */
    @Override
    public void exitUnaryOp(FLGrammarParser.UnaryOpContext ctx) {
        Formula f = values.get(ctx.formula());
        UnaryOperator.Operator op = UnaryOperator.Operator.getOp(ctx.getChild(0).getText());
        values.put(ctx, new UnaryOperator(f, op));
    }

    /* (non-Javadoc)
     * @see FLGrammarBaseListener#exitBinaryLogicOp(FLGrammarParser.BinaryLogicOpContext)
     */
    @Override
    public void exitBinaryLogicOp(FLGrammarParser.BinaryLogicOpContext ctx) {
        Formula leftFormula = values.get(ctx.formula(0));
        Formula rightFormula = values.get(ctx.formula(1));
        BinaryOperator.Operator op = BinaryOperator.Operator.getOp(ctx.getChild(1).toString());
        values.put(ctx, new BinaryOperator(leftFormula, rightFormula, op));
    }

    /* (non-Javadoc)
     * @see FLGrammarBaseListener#exitBinaryOp(FLGrammarParser.BinaryOpContext)
     */
    @Override
    public void exitBinaryOp(FLGrammarParser.BinaryOpContext ctx) {
        Formula leftFormula = values.get(ctx.formula(0));
        Formula rightFormula = values.get(ctx.formula(1));
        BinaryOperator.Operator op = BinaryOperator.Operator.getOp(ctx.getChild(1).toString());
        values.put(ctx, new BinaryOperator(leftFormula, rightFormula, op));
    }

    /* (non-Javadoc)
     * @see FLGrammarBaseListener#enterAtom(FLGrammarParser.AtomContext)
     */
    @Override
    public void enterAtom(FLGrammarParser.AtomContext ctx) {
        values.put(ctx, new Atom(ctx.ATOM().getText()));
    }
}
