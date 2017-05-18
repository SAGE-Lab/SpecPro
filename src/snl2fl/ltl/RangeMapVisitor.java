package it.unige.pat2fl.ltl;

import java.util.HashMap;
import java.util.TreeMap;

import it.unige.pat2fl.fl.elements.Atom;
import it.unige.pat2fl.req.expressions.*;
import it.unige.pat2fl.req.visitor.ExpressionVisitor;

/**
 * Created by Simone Vuotto on 15/10/15.
 */
public class RangeMapVisitor implements ExpressionVisitor {

    HashMap<String, TreeMap<Float, Atom[]>> rangeMap = new HashMap<>();

    public HashMap<String, TreeMap<Float, Atom[]>> getRangeMap(){
        return rangeMap;
    }

    @Override
    public void visitBooleanExpression(BooleanExpression exp) {

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

    private void add(FloatVariableExpression var, NumberExpression exp) {

        TreeMap<Float, Atom[]> treeMap = rangeMap.get(var.getName());
        if(treeMap == null) {
            treeMap = new TreeMap<>();
            rangeMap.put(var.getName(), treeMap);
        }
        if(!treeMap.containsValue(exp.floatValue())) {
            Atom [] atoms = new Atom[2];
            atoms[0] = new Atom("_lower_"+var.getName()+treeMap.size());
            atoms[1] = new Atom("_equal_"+var.getName()+treeMap.size());
            treeMap.put(exp.floatValue(), atoms);
        }

    }

}
