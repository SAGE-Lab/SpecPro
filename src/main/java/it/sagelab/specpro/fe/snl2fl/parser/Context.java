package it.sagelab.specpro.fe.snl2fl.parser;

import java.util.*;

import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.expressions.BooleanVariableExpression;
import it.sagelab.specpro.models.psp.expressions.FloatVariableExpression;
import it.sagelab.specpro.models.psp.expressions.VariableExpression;


/**
 * The Class Context.
 *
 * @author Simone Vuotto
 */
public class Context {
    
    /** The symbol table. */
    private Map<String, VariableExpression> symbolTable = new HashMap<>();


    /** The requirement list. */
    private ArrayList<Requirement> requirementList = new ArrayList<>();

    public Context() {  }

    /**
     * Gets the symbol table.
     *
     * @return the symbol table
     */
    public Map<String, VariableExpression> getSymbolTable() {
        return symbolTable;
    }

    /**
     * Add requirement r in the requirements list
     * @param r the requirement to add
     */
    public void addRequirement(Requirement r) {
        requirementList.add(r);
    }

    /**
     * Gets the requirements list
     *
     * @return the requirements list
     */
    public ArrayList<Requirement> getRequirementList() {
        return requirementList;
    }

    /**
     * Add the boolean variable with the given id to the symbol table if it's not present yet, and return the instance
     * of that variable.
     *
     * @param id the id
     * @return the boolean variable
     * @throws IllegalArgumentException if a variable with the given id but different type is already present in the symbol table
     */
    public BooleanVariableExpression addBooleanVariable(String id){
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
     * Add the float variable with the given id to the symbol table if it's not present yet, and return the instance
     * of that variable.
     *
     * @param id the id
     * @return the float variable
     * @throws IllegalArgumentException if a variable with the given id but different type is already present in the symbol table
     */
    public FloatVariableExpression addFloatVariable(String id){
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
