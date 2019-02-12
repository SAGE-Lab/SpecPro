package it.sagelab.specpro.fe.snl2fl.parser;

import java.util.*;
import java.util.stream.Collectors;

import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.expressions.VariableExpression;

import static java.util.stream.Collectors.toSet;


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
     * Gets the set of input variables
     * @return the set of input variables
     */
    public Set<VariableExpression> getInputVariables() {
        return symbolTable.values().stream().filter(v -> v.isInput()).collect(toSet());
    }

    /**
     * Gets the set of output variables
     * @return the set of output variables
     */
    public Set<VariableExpression> getOutputVariables() {
        return symbolTable.values().stream().filter(v -> v.isOutput()).collect(toSet());
    }

    /**
     * Add the boolean variable with the given id to the symbol table if it's not present yet, and return the instance
     * of that variable.
     *
     * @param id the id
     * @return the boolean variable
     * @throws IllegalArgumentException if a variable with the given id but different type is already present in the symbol table
     */
    public VariableExpression addBooleanVariable(String id){
        VariableExpression var = symbolTable.get(id);
        if(var == null) {
            var = new VariableExpression(id, VariableExpression.Type.BOOLEAN);
            symbolTable.put(id, var);
        } else if(var.getType() == VariableExpression.Type.UNDEFINED) {
            var = new VariableExpression(var, VariableExpression.Type.BOOLEAN);
            symbolTable.put(id, var);
        } else if(var.getType() != VariableExpression.Type.BOOLEAN) {
            throw new IllegalArgumentException("Type conflict for variable " + var.getLabel());
        }
        return var;
    }

    /**
     * Add the float variable with the given id to the symbol table if it's not present yet, and return the instance
     * of that variable.
     *
     * @param id the id
     * @return the float variable
     * @throws IllegalArgumentException if a variable with the given id but different type is already present in the symbol table
     */
    public VariableExpression addFloatVariable(String id){
        VariableExpression var = symbolTable.get(id);
        if(var == null) {
            var = new VariableExpression(id, VariableExpression.Type.FLOAT);
            symbolTable.put(id, var);
        } else if(var.getType() == VariableExpression.Type.UNDEFINED) {
            var = new VariableExpression(var, VariableExpression.Type.FLOAT);
            symbolTable.put(id, var);
        } else if(var.getType() != VariableExpression.Type.FLOAT) {
            throw new IllegalArgumentException("Type conflict for variable " + var.getLabel());
        }
        return var;
    }


}
