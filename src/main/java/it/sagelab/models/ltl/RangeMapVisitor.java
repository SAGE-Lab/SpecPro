package it.sagelab.models.ltl;

import java.util.HashMap;
import java.util.TreeMap;

import it.sagelab.models.ltl.elements.Atom;
import it.sagelab.models.psp.expressions.*;
import it.sagelab.fe.snl2fl.visitor.ExpressionVisitor;

/**
 * The Class RangeMapVisitor.
 *
 * @author Simone Vuotto
 */
public class RangeMapVisitor implements ExpressionVisitor {

    /** The range map. */
    private HashMap<String, TreeMap<Float, Atom[]>> rangeMap = new HashMap<>();

    /**
     * Gets the range map.
     *
     * @return the range map
     */
    public HashMap<String, TreeMap<Float, Atom[]>> getRangeMap(){
        return rangeMap;
    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitBooleanExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.BooleanExpression)
     */
    @Override
    public void visitBooleanExpression(BooleanExpression exp) {
        exp.getLeftExp().accept(this);
        exp.getRightExp().accept(this);
    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitUnaryExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.UnaryExpression)
     */
    @Override
    public void visitUnaryExpression(UnaryExpression exp) {
        exp.getExp().accept(this);
    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitCompareExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.CompareExpression)
     */
    @Override
    public void visitCompareExpression(CompareExpression exp) {
        if(exp.getLeftExp() instanceof FloatVariableExpression && exp.getRightExp() instanceof FloatVariableExpression)
            return; //Special case to be evaluated
        if(exp.getLeftExp() instanceof FloatVariableExpression)
            add((FloatVariableExpression) exp.getLeftExp(),  (NumberExpression) exp.getRightExp());
        else
            add((FloatVariableExpression) exp.getRightExp(), (NumberExpression) exp.getLeftExp());

    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitBooleanVariableExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.BooleanVariableExpression)
     */
    @Override
    public void visitBooleanVariableExpression(BooleanVariableExpression exp) {

    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitNumberExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.NumberExpression)
     */
    @Override
    public void visitNumberExpression(NumberExpression exp) {

    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitFloatVariableExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.FloatVariableExpression)
     */
    @Override
    public void visitFloatVariableExpression(FloatVariableExpression exp) {

    }

    /**
     * Adds the.
     *
     * @param var the var
     * @param exp the exp
     */
    private void add(FloatVariableExpression var, NumberExpression exp) {

        TreeMap<Float, Atom[]> treeMap = rangeMap.get(var.getName());
        if(treeMap == null) {
            treeMap = new TreeMap<>();
            rangeMap.put(var.getName(), treeMap);
        }
        if(!treeMap.containsKey(exp.floatValue())) {
            Atom [] atoms = new Atom[2];
            atoms[0] = new Atom("_lower_"+var.getName()+treeMap.size());
            atoms[1] = new Atom("_equal_"+var.getName()+treeMap.size());
            treeMap.put(exp.floatValue(), atoms);
        }

    }

}
