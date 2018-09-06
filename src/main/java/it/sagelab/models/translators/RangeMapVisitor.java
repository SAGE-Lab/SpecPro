package it.sagelab.models.translators;

import java.util.HashMap;
import java.util.TreeMap;

import it.sagelab.models.ltl.Atom;
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

    @Override
    public void visitBooleanExpression(BooleanExpression exp) {
        exp.getLeftExp().accept(this);
        exp.getRightExp().accept(this);
    }

    @Override
    public void visitUnaryExpression(UnaryExpression exp) {
        exp.getExp().accept(this);
    }

    @Override
    public void visitCompareExpression(CompareExpression exp) {
        if(exp.getLeftExp() instanceof FloatVariableExpression && exp.getRightExp() instanceof FloatVariableExpression)
            return; //Special case to be evaluated
        if(exp.getLeftExp() instanceof FloatVariableExpression)
            add((FloatVariableExpression) exp.getLeftExp(),  (NumberExpression) exp.getRightExp());
        else
            add((FloatVariableExpression) exp.getRightExp(), (NumberExpression) exp.getLeftExp());

    }

    @Override
    public void visitBooleanVariableExpression(BooleanVariableExpression exp) {

    }

    @Override
    public void visitNumberExpression(NumberExpression exp) {

    }

    @Override
    public void visitFloatVariableExpression(FloatVariableExpression exp) {

    }

    /**
     * Adds the var and threshold to the rangeMap.
     *
     * @param var the var
     * @param exp the exp
     */
    private void add(FloatVariableExpression var, NumberExpression exp) {

        TreeMap<Float, Atom[]> treeMap = rangeMap.computeIfAbsent(var.getName(), k -> new TreeMap<>());
        if(!treeMap.containsKey(exp.floatValue())) {
            Atom [] atoms = new Atom[2];
            atoms[0] = new Atom("_lower_"+var.getName()+treeMap.size());
            atoms[1] = new Atom("_equal_"+var.getName()+treeMap.size());
            treeMap.put(exp.floatValue(), atoms);
        }

    }

}
