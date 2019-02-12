package it.sagelab.specpro.fe.snl2fl.parser;

import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.Time;
import it.sagelab.specpro.models.psp.expressions.*;
import it.sagelab.specpro.models.psp.qualitative.*;
import it.sagelab.specpro.models.psp.realtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Simone Vuotto
 */
public class RequirementsBuilder extends RequirementsGrammarBaseListener {

    /** The context */
    private Context context = new Context();

    /*********************************************
     *  Private attributes
     *********************************************/
    
    /**  maps nodes to Expressions with Map<ParseTree,Expression>. */
    private ParseTreeProperty<Object> values = new ParseTreeProperty<>();
    
    /** The scope. */
    private Scope scope = null;

    private String reqId = null;

    private Requirement req = null;

    /*********************************************
     *  Getters
     *********************************************/

    public Context getContext() {
        return context;
    }

    /*********************************************
     *  Requirement Listeners
     *********************************************/

    @Override
    public void enterRequirement(RequirementsGrammarParser.RequirementContext ctx) {
        scope = null;
        reqId = null;
        req = null;
    }

    @Override
    public void exitRequirement(RequirementsGrammarParser.RequirementContext ctx) {
        if (reqId == null) {
            reqId = "REQ" + String.valueOf(context.getRequirementList().size() + 1);
        }
        req.setReqId(reqId);
        String text = ctx.start.getInputStream().getText(new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex()));
        req.setText(text);
        context.addRequirement(req);

    }

    @Override
    public void exitRId(RequirementsGrammarParser.RIdContext ctx) {
        reqId = ctx.getText();
    }

    @Override
    public void exitTime(RequirementsGrammarParser.TimeContext ctx) {
        Time t = new Time(Float.parseFloat(ctx.number().getText()), ctx.TIME_UNIT().getText());
        setValue(ctx, t);
    }

    @Override
    public void exitScope(RequirementsGrammarParser.ScopeContext ctx) {
        List<Expression> expressions = getExpressionList(ctx.expr());
        Scope.Type type = null;
        switch (ctx.getChild(0).getText()){
            case "Globally":
                type = Scope.Type.GLOBALLY;
                break;
            case "Before":
                type = Scope.Type.BEFORE;
                break;
            case "Between":
                type = Scope.Type.BETWEEN;
                break;
            case "After":
                if(expressions.size() == 2)
                    type = Scope.Type.AFTER_UNTIL;
                else
                    type = Scope.Type.AFTER;
                break;
        }

        this.scope = new Scope(type, expressions);
    }

    /*********************************************
     *  Variable Definition
     *********************************************/

    @Override
    public void exitVarDeclaration(RequirementsGrammarParser.VarDeclarationContext ctx) {
        String varName = ctx.ID().getText();
        String type = ctx.varType().getText();
        VariableExpression variableExpression = context.getSymbolTable().getOrDefault(varName,
                                                            new VariableExpression(varName, VariableExpression.Type.UNDEFINED));
        if("input".equals(type))
            variableExpression.setInput(true);
        if("output".equals(type))
            variableExpression.setOutput(true);
        context.getSymbolTable().put(varName, variableExpression);
    }

    /*********************************************
     *  Qualitative Requirement Listeners
     *********************************************/

    @Override
    public void exitAbsence(RequirementsGrammarParser.AbsenceContext ctx) {
        req = new AbsenceRequirement(this.scope, getExpression(ctx.expr()));
    }

    @Override
    public void exitUniversality(RequirementsGrammarParser.UniversalityContext ctx) {
        req = new UniversalityRequirement(this.scope, getExpression(ctx.expr()));
    }

    @Override
    public void exitExistence(RequirementsGrammarParser.ExistenceContext ctx) {
        req = new ExistenceRequirement(this.scope, getExpression(ctx.expr()));
    }

    @Override
    public void exitBoundedExistence(RequirementsGrammarParser.BoundedExistenceContext ctx) {
        req = new BoundedExistenceRequirement(this.scope, getExpression(ctx.expr()), Integer.parseInt(ctx.positiveInt().getText()));
    }

    @Override
    public void exitPrecedence(RequirementsGrammarParser.PrecedenceContext ctx){
        req = new PrecedenceRequirement(scope, getExpressionList(ctx.expr()));
    }

    @Override
    public void exitPrecedenceChain12(RequirementsGrammarParser.PrecedenceChain12Context ctx) {
        req = new PrecedenceChain12Requirement(scope, getExpressionList(ctx.expr()));
    }

    @Override
    public void exitPrecedenceChain21(RequirementsGrammarParser.PrecedenceChain21Context ctx) {
        req = new PrecedenceChain21Requirement(scope, getExpressionList(ctx.expr()));
    }

    @Override
    public void exitResponse(RequirementsGrammarParser.ResponseContext ctx) {
        req = new ResponseRequirement(scope, getExpressionList(ctx.expr()));
    }

    @Override
    public void exitResponseChain12(RequirementsGrammarParser.ResponseChain12Context ctx) {
        req = new ResponseChain12Requirement(scope, getExpressionList(ctx.expr()));
    }

    @Override
    public void exitResponseChain21(RequirementsGrammarParser.ResponseChain21Context ctx) {
        req = new ResponseChain21Requirement(scope, getExpressionList(ctx.expr()));
    }

    @Override
    public void exitConstrainedChain12(RequirementsGrammarParser.ConstrainedChain12Context ctx) {
        req = new ConstrainedChain12Requirement(scope, getExpressionList(ctx.expr()));
    }

    @Override
    public void exitInvariant(RequirementsGrammarParser.InvariantContext ctx) {
        req = new InvariantRequirement(scope, getExpressionList(ctx.expr()));
    }

    /*********************************************
     *  Real Time Requirement Listeners
     *********************************************/

    @Override
    public void exitMinDuration(RequirementsGrammarParser.MinDurationContext ctx) {
        req = new MinDurationRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
    }

    @Override
    public void exitMaxDuration(RequirementsGrammarParser.MaxDurationContext ctx) {
        req = new MaxDurationRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
    }

    @Override
    public void exitBoundedRecurrence(RequirementsGrammarParser.BoundedRecurrenceContext ctx) {
        req = new BoundedRecurrenceRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
    }

    @Override
    public void exitBoundedResponse(RequirementsGrammarParser.BoundedResponseContext ctx) {
        req = new BoundedResponseRequirement(scope, getExpressionList(ctx.expr()), getTime(ctx.time()));
    }

    @Override
    public void exitBoundedInvariance(RequirementsGrammarParser.BoundedInvarianceContext ctx) {
        req = new BoundedInvarianceRequirement(scope, getExpressionList(ctx.expr()), getTime(ctx.time()));
    }

    /*********************************************
     *  Expressions Listeners
     *********************************************/

    @Override
    public void exitBracketExpression(RequirementsGrammarParser.BracketExpressionContext ctx){
        setValue(ctx, getExpression(ctx.expr()));
    }

    @Override
    public void exitCompareExpression(RequirementsGrammarParser.CompareExpressionContext ctx) {
        CompareExpression.Operator operator = CompareExpression.getOperator(ctx.getChild(1).getText());
        Expression [] operands = new Expression[2];
        int index = 0;
        for(TerminalNode id : ctx.ID())
            operands[index++] = context.addFloatVariable(id.getText());
        for(RequirementsGrammarParser.NumberContext numb : ctx.number())
            operands[index++] = new NumberExpression(Float.parseFloat(numb.getText()));
        CompareExpression expression = new CompareExpression(operands[0], operands[1], operator);
        setValue(ctx, expression);
    }

    @Override
    public void exitBooleanExpression(RequirementsGrammarParser.BooleanExpressionContext ctx) {
        BooleanExpression.Operator operator = BooleanExpression.getOperator(ctx.getChild(1).getText());
        Expression leftExp = getExpression(ctx.expr(0));
        Expression rightExp = getExpression(ctx.expr(1));
        BooleanExpression expression = new BooleanExpression(leftExp, rightExp, operator);
        setValue(ctx, expression);
    }

    @Override
    public void exitUnaryExpression(RequirementsGrammarParser.UnaryExpressionContext ctx) {
        UnaryExpression.Operator operator = UnaryExpression.getOperator(ctx.getChild(0).getText());
        Expression exp = getExpression(ctx.expr());
        UnaryExpression expression = new UnaryExpression(exp, operator);
        setValue(ctx, expression);
    }

    @Override
    public void exitIDExpression(RequirementsGrammarParser.IDExpressionContext ctx) {
        setValue(ctx, context.addBooleanVariable(ctx.ID().getText()));
    }

    /*********************************************
     *  Util Methods
     *********************************************/

    private void setValue(ParseTree node, Object value) { values.put(node, value); }

    /**
     * Gets the expression.
     *
     * @param node the node
     * @return the expression
     */
    private Expression getExpression(RequirementsGrammarParser.ExprContext node) { return (Expression) values.get(node); }

    /**
     * Gets the expression list.
     *
     * @param exprContexts the expr contexts
     * @return the expression list
     */
    private List<Expression> getExpressionList(List<RequirementsGrammarParser.ExprContext> exprContexts) {
        List<Expression> exprs = new ArrayList<>();
        for(RequirementsGrammarParser.ExprContext ctx : exprContexts)
            exprs.add(getExpression(ctx));
        return exprs;
    }

    /**
     * Gets the time.
     *
     * @param node the node
     * @return the time
     */
    private Time getTime(RequirementsGrammarParser.TimeContext node) { return (Time) values.get(node); }

    /**
     * Gets the requirement.
     *
     * @param node the node
     * @return the requirement
     */
    private Requirement getRequirement(ParseTree node) { return (Requirement) values.get(node); }



}
