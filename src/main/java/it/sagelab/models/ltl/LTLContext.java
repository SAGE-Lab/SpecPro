package it.sagelab.models.ltl;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NavigableMap;

import it.sagelab.models.ltl.elements.Atom;
import it.sagelab.models.ltl.elements.BinaryOperator;
import it.sagelab.models.ltl.elements.Formula;
import it.sagelab.models.ltl.elements.UnaryOperator;
import it.sagelab.fe.ltl.patterns.Pattern;
import it.sagelab.models.psp.expressions.CompareExpression;
import it.sagelab.models.psp.expressions.CompareExpression.Operator;
import it.sagelab.models.psp.expressions.VariableExpression;


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
    	 Formula f = null;
    	 if (operator == Operator.EQUAL) {
    		 f = getEqualAtom(varName, threshold);
    	 } else {
    		 Atom[] variables = getAtoms(varName,threshold,operator);
             for (Atom a : variables) {
                 if (f == null) {
                     if ((operator == Operator.GREATER) ||
                             (operator == Operator.GREATER_EQUAL)) {
                         f = new UnaryOperator(a, UnaryOperator.Operator.NOT);
                     } else {
                         f = a;
                     }
                 } else {
                     if ((operator == Operator.GREATER) ||
                             (operator == Operator.GREATER_EQUAL)) {
                         f = new BinaryOperator(f, new UnaryOperator(a, UnaryOperator.Operator.NOT),
                                 BinaryOperator.Operator.AND);
                     } else {
                         // TODO: thrown exception if operator is not LOWER or LOWER_EQUAL
                         f = new BinaryOperator(f, a, BinaryOperator.Operator.OR);
                     }
                 }
             }
    	 }
    	 return f;
    }	

    /**
     * Gets the set of the Atoms of a given compare expression.
     * 
     * @param varName the var name
     * @param threshold the threshold
     * @param operator the operator
     * @return the set of atoms in the compare expression formula.
     */
    public Atom[] getAtoms(String varName, Float threshold, CompareExpression.Operator operator) {
    	TreeMap<Float, Atom[]> treeMap = rangeMap.get(varName);
    	if(treeMap == null) {
            throw new RuntimeException("Variable " + varName + " not found");
        }

        NavigableMap<Float,Atom[]> submap = treeMap.headMap(threshold, true);
    	Collection<Atom[]> c = submap.values();
    	//obtain an Iterator for Collection
    	Iterator<Atom[]> itr = c.iterator();
    	//iterate through TreeMap values iterator
    	ArrayList<Atom> vars = new ArrayList<>();
    	while(itr.hasNext()) {
    		Atom[] a = itr.next();
    		vars.add(a[0]);
	    	if (itr.hasNext() ||
    			(operator == Operator.GREATER) || 
    			(operator == Operator.LOWER_EQUAL)) {
	    		vars.add(a[1]);	
	    	}
    	}
    	return vars.toArray(new Atom[0]);
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
