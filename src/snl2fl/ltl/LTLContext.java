package snl2fl.ltl;

import java.util.Map;
import java.util.TreeMap;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.BinaryOperator;
import snl2fl.fl.elements.Formula;
import snl2fl.fl.elements.UnaryOperator;
import snl2fl.fl.patterns.Pattern;
import snl2fl.req.expressions.CompareExpression;
import snl2fl.req.expressions.VariableExpression;


/**
 * The Class LTLContext.
 *
 * @author Simone Vuotto
 */
public class LTLContext {
    
    /** The symbol table. */
    private Map<String, VariableExpression> symbolTable;
    
    /** The range map. */
    private Map<String, TreeMap<Float, Atom[]>> rangeMap;
    
    /** The pattern map. */
    private Map<String, Pattern> patternMap;

    /**
     * Instantiates a new LTL context.
     *
     * @param symbolTable the symbol table
     * @param rangeMap the range map
     * @param patternMap the pattern map
     */
    public LTLContext(Map<String, VariableExpression> symbolTable, Map<String, TreeMap<Float, Atom[]>> rangeMap, Map<String, Pattern> patternMap) {
        this.symbolTable = symbolTable;
        this.rangeMap = rangeMap;
        this.patternMap = patternMap;
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
     * Gets the range map.
     *
     * @return the range map
     */
    public Map<String, TreeMap<Float, Atom[]>> getRangeMap() {
        return rangeMap;
    }

    /**
     * Gets the pattern map.
     *
     * @return the pattern map
     */
    public Map<String, Pattern> getPatternMap() {
        return patternMap;
    }

    /**
     * Gets the pattern.
     *
     * @param key the key
     * @return the pattern
     */
    public Pattern getPattern(String key) {
        return patternMap.get(key);
    }

    /**
     * Gets the formula.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @param operator the operator
     * @return the formula
     */
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

    /**
     * Gets the equal atom.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @return the equal atom
     */
    public Atom getEqualAtom(String varName, Float threshold) {
        return rangeMap.get(varName).get(threshold)[1];
    }

    /**
     * Gets the lower atom.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @return the lower atom
     */
    public Atom getLowerAtom(String varName, Float threshold) {
        return rangeMap.get(varName).get(threshold)[0];
    }
}
