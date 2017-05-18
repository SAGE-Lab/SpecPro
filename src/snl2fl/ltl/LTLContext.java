package it.unige.pat2fl.ltl;

import java.util.Map;
import java.util.TreeMap;

import it.unige.pat2fl.fl.elements.Atom;
import it.unige.pat2fl.fl.elements.BinaryOperator;
import it.unige.pat2fl.fl.elements.Formula;
import it.unige.pat2fl.fl.elements.UnaryOperator;
import it.unige.pat2fl.fl.patterns.Pattern;
import it.unige.pat2fl.req.expressions.CompareExpression;
import it.unige.pat2fl.req.expressions.VariableExpression;

/**
 * Created by Simone Vuotto on 03/11/15.
 */
public class LTLContext {
    private Map<String, VariableExpression> symbolTable;
    private Map<String, TreeMap<Float, Atom[]>> rangeMap;
    private Map<String, Pattern> patternMap;

    public LTLContext(Map<String, VariableExpression> symbolTable, Map<String, TreeMap<Float, Atom[]>> rangeMap, Map<String, Pattern> patternMap) {
        this.symbolTable = symbolTable;
        this.rangeMap = rangeMap;
        this.patternMap = patternMap;
    }

    public Map<String, VariableExpression> getSymbolTable() {
        return symbolTable;
    }

    public Map<String, TreeMap<Float, Atom[]>> getRangeMap() {
        return rangeMap;
    }

    public Map<String, Pattern> getPatternMap() {
        return patternMap;
    }

    public Pattern getPattern(String key) {
        return patternMap.get(key);
    }

    public Formula getFormula(String varName, Float threshold, CompareExpression.Operator operator) {
        Atom equalAtom = getEqualAtom(varName, threshold);
        Atom lowerAtom = getLowerAtom(varName, threshold);
        Formula f = null;
        switch (operator) {
            case EQUAL:
                f = equalAtom;
                break;
            case GREATER:
                f = new BinaryOperator(
                        new UnaryOperator(lowerAtom, UnaryOperator.Operator.NOT),
                        new UnaryOperator(equalAtom, UnaryOperator.Operator.NOT),
                        BinaryOperator.Operator.AND);
                break;
            case GREATER_EQUAL:
                f = new UnaryOperator(lowerAtom, UnaryOperator.Operator.NOT);
                break;
            case LOWER:
                f = lowerAtom;
                break;
            case LOWER_EQUAL:
                f = new BinaryOperator(lowerAtom, equalAtom, BinaryOperator.Operator.OR);
                break;
            case NOT_EQUAL:
                f = new UnaryOperator(equalAtom, UnaryOperator.Operator.NOT);
                break;
        }
        return f;
    }

    public Atom getEqualAtom(String varName, Float threshold) {
        return rangeMap.get(varName).get(threshold)[1];
    }

    public Atom getLowerAtom(String varName, Float threshold) {
        return rangeMap.get(varName).get(threshold)[0];
    }
}
