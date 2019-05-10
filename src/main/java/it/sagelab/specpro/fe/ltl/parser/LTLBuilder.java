package it.sagelab.specpro.fe.ltl.parser;

import it.sagelab.specpro.models.InputRequirement;
import it.sagelab.specpro.models.ltl.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/**
 * The Class LTLBuilder.
 *
 * @author Simone Vuotto
 */
public class LTLBuilder extends LTLGrammarBaseListener {
    
    /**  maps nodes to Expressions with Map<ParseTree,Expression>. */
    private ParseTreeProperty<Formula> values = new ParseTreeProperty<>();

    private LTLSpec spec = null;

    /**
     * Gets the ltl specification.
     *
     * @return The specification object containing the parsed ltl formulae.
     */
    public LTLSpec getSpec() {
        return spec;
    }

    @Override
    public void enterFile(LTLGrammarParser.FileContext ctx) {
        spec = new LTLSpec();
    }

    @Override
    public void exitFile(LTLGrammarParser.FileContext ctx) {
        for(LTLGrammarParser.FormulaContext fc :ctx.formula()) {
            Formula f = values.get(fc);
            InputRequirement r = new InputRequirement();
            String text = fc.start.getInputStream().getText(new Interval(fc.start.getStartIndex(), fc.stop.getStopIndex()));
            r.setText(text);
            spec.addRequirement(f, r);
        }
    }

    @Override
    public void exitBracketFormula(LTLGrammarParser.BracketFormulaContext ctx) {
        values.put(ctx, values.get(ctx.formula()));
    }

    @Override
    public void exitUnaryOp(LTLGrammarParser.UnaryOpContext ctx) {
        Formula f = values.get(ctx.formula());
        UnaryOperator.Operator op = UnaryOperator.Operator.getOp(ctx.getChild(0).getText());
        values.put(ctx, new UnaryOperator(f, op));
    }

    @Override
    public void exitBinaryOp(LTLGrammarParser.BinaryOpContext ctx) {
        Formula leftFormula = values.get(ctx.formula(0));
        Formula rightFormula = values.get(ctx.formula(1));
        BinaryOperator.Operator op = BinaryOperator.Operator.getOp(ctx.getChild(1).toString());
        values.put(ctx, new BinaryOperator(leftFormula, rightFormula, op));
    }

    @Override
    public void enterAtom(LTLGrammarParser.AtomContext ctx) {
        Atom a = spec.getOrCreateAtom(ctx.ATOM().getText());
        values.put(ctx, a);
    }
}
