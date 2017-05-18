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
 * Created by Simone Vuotto on 03/09/15.
 */
public class RequirementsBuilder extends RequirementsGrammarBaseListener {
    /** maps nodes to Expressions with Map<ParseTree,Expression> */
    ParseTreeProperty<Object> values = new ParseTreeProperty<Object>();
    /** Symbol Table  **/
    Map<String, VariableExpression> symbolTable = new HashMap<>();
    ArrayList<Requirement> requirementList = new ArrayList<>();
    Scope scope = null;

    /** Getter Methods **/
    public List<Requirement> getRequirementList() {
        return requirementList;
    }

    public Map<String, VariableExpression> getSymbolTable() {
        return symbolTable;
    }


    /** Time and Scope Listeners **/

    @Override
    public void exitTime(RequirementsGrammarParser.TimeContext ctx) {
        Time t = new Time(Float.parseFloat(ctx.NUMBER().getText()), ctx.TIME_UNIT().getText());
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

    /** Qualitative Requirement Listeners **/

    @Override
    public void exitAbsence(RequirementsGrammarParser.AbsenceContext ctx) {
        Requirement r = new AbsenceRequirement(this.scope, getExpression(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitUniversality(RequirementsGrammarParser.UniversalityContext ctx) {
        Requirement r = new UniversalityRequirement(this.scope, getExpression(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitExistence(RequirementsGrammarParser.ExistenceContext ctx) {
        Requirement r = new ExistenceRequirement(this.scope, getExpression(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitBoundedExistence(RequirementsGrammarParser.BoundedExistenceContext ctx) {
        Requirement r = new BoundedExistenceRequirement(this.scope, getExpression(ctx.expr()), Integer.parseInt(ctx.POSITIVE_INT().getText()));
        requirementList.add(r);
    }

    @Override
    public void exitPrecedence(RequirementsGrammarParser.PrecedenceContext ctx){
        Requirement r = new PrecedenceRequirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitPrecedenceChain12(RequirementsGrammarParser.PrecedenceChain12Context ctx) {
        Requirement r = new PrecedenceChain12Requirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitPrecedenceChain21(RequirementsGrammarParser.PrecedenceChain21Context ctx) {
        Requirement r = new PrecedenceChain21Requirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitResponse(RequirementsGrammarParser.ResponseContext ctx) {
        Requirement r = new ResponseRequirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitResponseChain12(RequirementsGrammarParser.ResponseChain12Context ctx) {
        Requirement r = new ResponseChain12Requirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitResponseChain21(RequirementsGrammarParser.ResponseChain21Context ctx) {
        Requirement r = new ResponseChain21Requirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    @Override
    public void exitConstrainedChain12(RequirementsGrammarParser.ConstrainedChain12Context ctx) {
        Requirement r = new ConstrainedChain12Requriement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /** Real Time Requirement Listeners **/

    @Override
    public void exitMinDuration(RequirementsGrammarParser.MinDurationContext ctx) {
        Requirement r = new MinDurationRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    @Override
    public void exitMaxDuration(RequirementsGrammarParser.MaxDurationContext ctx) {
        Requirement r = new MaxDurationRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    @Override
    public void exitBoundedRecurrence(RequirementsGrammarParser.BoundedRecurrenceContext ctx) {
        Requirement r = new BoundedRecurrenceRequirement(scope, getExpression(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    @Override
    public void exitBoundedResponse(RequirementsGrammarParser.BoundedResponseContext ctx) {
        Requirement r = new BoundedResponseRequirement(scope, getExpressionList(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    @Override
    public void exitBoundedInvariance(RequirementsGrammarParser.BoundedInvarianceContext ctx) {
        Requirement r = new BoundedInvarianceRequirement(scope, getExpressionList(ctx.expr()), getTime(ctx.time()));
        requirementList.add(r);
    }

    /** Invariant Requirement Listener **/

    @Override
    public void exitInvariant(RequirementsGrammarParser.InvariantContext ctx) {
        Requirement r = new InvariantRequirement(scope, getExpressionList(ctx.expr()));
        requirementList.add(r);
    }

    /** Expression Listeners **/

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
            operands[index++] = getFloatVariable(id.getText());
        for(TerminalNode numb : ctx.NUMBER())
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
    public void exitIDExpression(RequirementsGrammarParser.IDExpressionContext ctx) {
        setValue(ctx, getBooleanVariable(ctx.ID().getText()));
    }

    /** Util methods **/

    public void setValue(ParseTree node, Object value) { values.put(node, value); }

    public Expression getExpression(RequirementsGrammarParser.ExprContext node) { return (Expression) values.get(node); }

    public List<Expression> getExpressionList(List<RequirementsGrammarParser.ExprContext> exprContexts) {
        List<Expression> exprs = new ArrayList<>();
        for(RequirementsGrammarParser.ExprContext ctx : exprContexts)
            exprs.add(getExpression(ctx));
        return exprs;
    }

    public Time getTime(RequirementsGrammarParser.TimeContext node) { return (Time) values.get(node); }

    public Requirement getRequirement(ParseTree node) { return (Requirement) values.get(node); }

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
