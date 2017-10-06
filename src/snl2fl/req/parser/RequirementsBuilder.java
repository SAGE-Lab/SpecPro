package snl2fl.req.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import snl2fl.req.expressions.*;
import snl2fl.req.requirements.Requirement;
import snl2fl.req.requirements.Scope;
import snl2fl.req.requirements.Time;
import snl2fl.req.requirements.qualitative.*;
import snl2fl.req.requirements.realtime.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Simone Vuotto
 */
public class RequirementsBuilder extends RequirementsGrammarBaseListener {
    
    /**  maps nodes to Expressions with Map<ParseTree,Expression>. */
    private ParseTreeProperty<Object> values = new ParseTreeProperty<Object>();
    
    /**  Symbol Table  *. */
    private Map<String, VariableExpression> symbolTable = new HashMap<>();
    
    /** The requirement list. */
    private ArrayList<Requirement> requirementList = new ArrayList<>();
    
    /** The scope. */
    private Scope scope = null;

    /**
     *  Getter Methods *.
     *
     * @return the requirement list
     */
    public List<Requirement> getRequirementList() {
        return requirementList;
    }

    /**
     * Gets the symbol table.
     *
     * @return the symbol table
     */
    public Map<String, VariableExpression> getSymbolTable() {
        return symbolTable;
    }


    /**
     *  Time and Scope Listeners *.
     *
     * @param ctx the ctx
     */

    @Override
    public void exitTime(RequirementsGrammarParser.TimeContext ctx) {
        Time t = new Time(Float.parseFloat(ctx.NUMBER().getText()), ctx.TIME_UNIT().getText());
        setValue(ctx, t);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitScope(snl2fl.req.parser.RequirementsGrammarParser.ScopeContext)
     */
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

    /**
     *  Qualitative Requirement Listeners *.
     *
     * @param ctx the ctx
     */

    @Override
    public void exitAbsence(RequirementsGrammarParser.AbsenceContext ctx) {
        Requirement r = new AbsenceRequirement(this.scope, getExpression(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitUniversality(snl2fl.req.parser.RequirementsGrammarParser.UniversalityContext)
     */
    @Override
    public void exitUniversality(RequirementsGrammarParser.UniversalityContext ctx) {
        Requirement r = new UniversalityRequirement(this.scope, getExpression(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitExistence(snl2fl.req.parser.RequirementsGrammarParser.ExistenceContext)
     */
    @Override
    public void exitExistence(RequirementsGrammarParser.ExistenceContext ctx) {
        Requirement r = new ExistenceRequirement(this.scope, getExpression(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitBoundedExistence(snl2fl.req.parser.RequirementsGrammarParser.BoundedExistenceContext)
     */
    @Override
    public void exitBoundedExistence(RequirementsGrammarParser.BoundedExistenceContext ctx) {
        Requirement r = new BoundedExistenceRequirement(this.scope, getExpression(ctx.expr()), Integer.parseInt(ctx.POSITIVE_INT().getText()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitPrecedence(snl2fl.req.parser.RequirementsGrammarParser.PrecedenceContext)
     */
    @Override
    public void exitPrecedence(RequirementsGrammarParser.PrecedenceContext ctx){
        Requirement r = new PrecedenceRequirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitPrecedenceChain12(snl2fl.req.parser.RequirementsGrammarParser.PrecedenceChain12Context)
     */
    @Override
    public void exitPrecedenceChain12(RequirementsGrammarParser.PrecedenceChain12Context ctx) {
        Requirement r = new PrecedenceChain12Requirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitPrecedenceChain21(snl2fl.req.parser.RequirementsGrammarParser.PrecedenceChain21Context)
     */
    @Override
    public void exitPrecedenceChain21(RequirementsGrammarParser.PrecedenceChain21Context ctx) {
        Requirement r = new PrecedenceChain21Requirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitResponse(snl2fl.req.parser.RequirementsGrammarParser.ResponseContext)
     */
    @Override
    public void exitResponse(RequirementsGrammarParser.ResponseContext ctx) {
        Requirement r = new ResponseRequirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitResponseChain12(snl2fl.req.parser.RequirementsGrammarParser.ResponseChain12Context)
     */
    @Override
    public void exitResponseChain12(RequirementsGrammarParser.ResponseChain12Context ctx) {
        Requirement r = new ResponseChain12Requirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitResponseChain21(snl2fl.req.parser.RequirementsGrammarParser.ResponseChain21Context)
     */
    @Override
    public void exitResponseChain21(RequirementsGrammarParser.ResponseChain21Context ctx) {
        Requirement r = new ResponseChain21Requirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitConstrainedChain12(snl2fl.req.parser.RequirementsGrammarParser.ConstrainedChain12Context)
     */
    @Override
    public void exitConstrainedChain12(RequirementsGrammarParser.ConstrainedChain12Context ctx) {
        Requirement r = new ConstrainedChain12Requriement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /**
     *  Real Time Requirement Listeners *.
     *
     * @param ctx the ctx
     */

    @Override
    public void exitMinDuration(RequirementsGrammarParser.MinDurationContext ctx) {
        Requirement r = new MinDurationRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitMaxDuration(snl2fl.req.parser.RequirementsGrammarParser.MaxDurationContext)
     */
    @Override
    public void exitMaxDuration(RequirementsGrammarParser.MaxDurationContext ctx) {
        Requirement r = new MaxDurationRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitBoundedRecurrence(snl2fl.req.parser.RequirementsGrammarParser.BoundedRecurrenceContext)
     */
    @Override
    public void exitBoundedRecurrence(RequirementsGrammarParser.BoundedRecurrenceContext ctx) {
        Requirement r = new BoundedRecurrenceRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitBoundedResponse(snl2fl.req.parser.RequirementsGrammarParser.BoundedResponseContext)
     */
    @Override
    public void exitBoundedResponse(RequirementsGrammarParser.BoundedResponseContext ctx) {
        Requirement r = new BoundedResponseRequirement(scope, getExpressionList(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitBoundedInvariance(snl2fl.req.parser.RequirementsGrammarParser.BoundedInvarianceContext)
     */
    @Override
    public void exitBoundedInvariance(RequirementsGrammarParser.BoundedInvarianceContext ctx) {
        Requirement r = new BoundedInvarianceRequirement(scope, getExpressionList(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    /**
     *  Invariant Requirement Listener *.
     *
     * @param ctx the ctx
     */

    @Override
    public void exitInvariant(RequirementsGrammarParser.InvariantContext ctx) {
        Requirement r = new InvariantRequirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /**
     *  Expression Listeners *.
     *
     * @param ctx the ctx
     */

    @Override
    public void exitBracketExpression(RequirementsGrammarParser.BracketExpressionContext ctx){
        setValue(ctx, getExpression(ctx.expr()));
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitCompareExpression(snl2fl.req.parser.RequirementsGrammarParser.CompareExpressionContext)
     */
    @Override
    public void exitCompareExpression(RequirementsGrammarParser.CompareExpressionContext ctx) {
        CompareExpression.Operator operator = CompareExpression.getOperator(ctx.getChild(1).getText());
        Expression [] operands = new Expression[2];
        int index = 0;
        for(TerminalNode id : ctx.ID())
            operands[index++] = getFloatVariable(id.getText());
        for(TerminalNode numb : ctx.NUMBER())
            operands[index++] = new NumberExpression(Float.parseFloat(numb.getText()));
        CompareExpression expression = new CompareExpression(operands[0], operands[1], operator);
        setValue(ctx, expression);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitBooleanExpression(snl2fl.req.parser.RequirementsGrammarParser.BooleanExpressionContext)
     */
    @Override
    public void exitBooleanExpression(RequirementsGrammarParser.BooleanExpressionContext ctx) {
        BooleanExpression.Operator operator = BooleanExpression.getOperator(ctx.getChild(1).getText());
        Expression leftExp = getExpression(ctx.expr(0));
        Expression rightExp = getExpression(ctx.expr(1));
        BooleanExpression expression = new BooleanExpression(leftExp, rightExp, operator);
        setValue(ctx, expression);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitUnaryExpression(snl2fl.req.parser.RequirementsGrammarParser.UnaryExpressionContext)
     */
    @Override
    public void exitUnaryExpression(RequirementsGrammarParser.UnaryExpressionContext ctx) {
        UnaryExpression.Operator operator = UnaryExpression.getOperator(ctx.getChild(0).getText());
        Expression exp = getExpression(ctx.expr());
        UnaryExpression expression = new UnaryExpression(exp, operator);
        setValue(ctx, expression);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.parser.RequirementsGrammarBaseListener#exitIDExpression(snl2fl.req.parser.RequirementsGrammarParser.IDExpressionContext)
     */
    @Override
    public void exitIDExpression(RequirementsGrammarParser.IDExpressionContext ctx) {
        setValue(ctx, getBooleanVariable(ctx.ID().getText()));
    }

    /**
     *  Util methods *.
     *
     * @param node the node
     * @param value the value
     */

    public void setValue(ParseTree node, Object value) { values.put(node, value); }

    /**
     * Gets the expression.
     *
     * @param node the node
     * @return the expression
     */
    public Expression getExpression(RequirementsGrammarParser.ExprContext node) { return (Expression) values.get(node); }

    /**
     * Gets the expression list.
     *
     * @param exprContexts the expr contexts
     * @return the expression list
     */
    public List<Expression> getExpressionList(List<RequirementsGrammarParser.ExprContext> exprContexts) {
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
    public Time getTime(RequirementsGrammarParser.TimeContext node) { return (Time) values.get(node); }

    /**
     * Gets the requirement.
     *
     * @param node the node
     * @return the requirement
     */
    public Requirement getRequirement(ParseTree node) { return (Requirement) values.get(node); }

    /**
     * Gets the boolean variable.
     *
     * @param id the id
     * @return the boolean variable
     */
    public BooleanVariableExpression getBooleanVariable(String id){
        VariableExpression var = symbolTable.get(id);
        if(var == null) {
            var = new BooleanVariableExpression(id);
            symbolTable.put(id, var);
        } else if(!(var instanceof BooleanVariableExpression)) {
            throw new IllegalArgumentException("Type conflict for variable " + var.getName());
        }
        return (BooleanVariableExpression) var;
    }

    /**
     * Gets the float variable.
     *
     * @param id the id
     * @return the float variable
     */
    public FloatVariableExpression getFloatVariable(String id){
        VariableExpression var = symbolTable.get(id);
        if(var == null) {
            var = new FloatVariableExpression(id);
            symbolTable.put(id, var);
        } else if(!(var instanceof FloatVariableExpression)) {
            throw new IllegalArgumentException("Type conflict for variable " + var.getName());
        }
        return (FloatVariableExpression) var;
    }

}
