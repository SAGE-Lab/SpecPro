package it.unige.pat2fl.fl.parser;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import it.unige.pat2fl.fl.elements.Atom;
import it.unige.pat2fl.fl.elements.BinaryOperator;
import it.unige.pat2fl.fl.elements.Formula;
import it.unige.pat2fl.fl.elements.UnaryOperator;

/**
 * Created by Simone Vuotto on 16/11/15.
 */
public class FLBuilder extends FLGrammarBaseListener {
    /** maps nodes to Expressions with Map<ParseTree,Expression> */
    ParseTreeProperty<Formula> values = new ParseTreeProperty<>();
    Formula root = null;

    public Formula getFormula(){
        return root;
    }

    @Override
    public void exitFl(FLGrammarParser.FlContext ctx) {
        root = values.get(ctx.formula());
    }

    @Override
    public void exitBracketFormula(FLGrammarParser.BracketFormulaContext ctx) {
        values.put(ctx, values.get(ctx.formula()));
    }

    @Override
    public void exitUnaryOp(FLGrammarParser.UnaryOpContext ctx) {
        Formula f = values.get(ctx.formula());
        UnaryOperator.Operator op = UnaryOperator.Operator.getOp(ctx.getChild(0).getText());
        values.put(ctx, new UnaryOperator(f, op));
    }

    @Override
    public void exitBinaryLogicOp(FLGrammarParser.BinaryLogicOpContext ctx) {
        Formula leftFormula = values.get(ctx.formula(0));
        Formula rightFormula = values.get(ctx.formula(1));
        BinaryOperator.Operator op = BinaryOperator.Operator.getOp(ctx.getChild(1).toString());
        values.put(ctx, new BinaryOperator(leftFormula, rightFormula, op));
    }

    @Override
    public void exitBinaryOp(FLGrammarParser.BinaryOpContext ctx) {
        Formula leftFormula = values.get(ctx.formula(0));
        Formula rightFormula = values.get(ctx.formula(1));
        BinaryOperator.Operator op = BinaryOperator.Operator.getOp(ctx.getChild(1).toString());
        values.put(ctx, new BinaryOperator(leftFormula, rightFormula, op));
    }

    @Override
    public void enterAtom(FLGrammarParser.AtomContext ctx) {
        values.put(ctx, new Atom(ctx.ATOM().getText()));
    }
}
