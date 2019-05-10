package it.sagelab.specpro.models.translators;

import java.util.HashMap;
import java.util.TreeMap;

import it.sagelab.specpro.fe.psp.Snl2FlException;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.psp.expressions.ExpressionVisitor;
import it.sagelab.specpro.models.psp.expressions.*;

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
        if(exp.getLeftExp() instanceof VariableExpression && exp.getRightExp() instanceof VariableExpression)
            throw new Snl2FlException("Comparison between two variables is not supported.");
        if(exp.getLeftExp() instanceof VariableExpression)
            add((VariableExpression) exp.getLeftExp(),  (NumberExpression) exp.getRightExp());
        else
            add((VariableExpression) exp.getRightExp(), (NumberExpression) exp.getLeftExp());

    }

    @Override
    public void visitNumberExpression(NumberExpression exp) {

    }

    @Override
    public void visitVariableExpression(VariableExpression exp) {

    }

    /**
     * Adds the var and threshold to the rangeMap.
     *
     * @param var the var
     * @param exp the exp
     */
    private void add(VariableExpression var, NumberExpression exp) {

        TreeMap<Float, Atom[]> treeMap = rangeMap.computeIfAbsent(var.getLabel(), k -> new TreeMap<>());
        if(!treeMap.containsKey(exp.floatValue())) {
            Atom [] atoms = new Atom[2];
            atoms[0] = new Atom("_lower_"+var.getLabel()+treeMap.size());
            atoms[1] = new Atom("_equal_"+var.getLabel()+treeMap.size());
            treeMap.put(exp.floatValue(), atoms);
        }

    }

}
