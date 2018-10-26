package it.sagelab.specpro.models.psp.expressions;

import it.sagelab.specpro.fe.snl2fl.visitor.ExpressionVisitor;

/**
 * The Class VariableExpression.
 *
 * @author = Simone Vuotto
 * creation date  =  03/09/15.
 */
public class VariableExpression extends Expression{

    public enum Type {
        BOOLEAN,
        FLOAT,
        UNDEFINED;
    }
    
    /** The name of the variable. */
    private final String name;

    /**
     * The label assigned to the variable,
     * it can be different from the name if the name is not allowed by a
     * given model checker.
     * */
    private String label;

    /**
     * The type of the variable
     */
    private final Type type;

    /**
     * It indicates if the variable is flagged as input variable.
     */
    private boolean input;

    /**
     * It indicates if the variable is flagged as output variable.
     */
    private boolean output;


    /**
     * Instantiates a new variable expression.
     *
     * @param name the name
     */
    public VariableExpression(String name, Type type){
        this.name = name;
        this.label = name;
        this.type = type;
        this.input = false;
        this.output = false;
    }

    public VariableExpression(VariableExpression exp, Type newType) {
        this.name = exp.name;
        this.label = exp.label;
        this.input = exp.input;
        this.output = exp.output;
        this.type = newType;
    }

    public Type getType() {
        return type;
    }

    public boolean isInput() {
        return input;
    }

    public void setInput(boolean input) {
        this.input = input;
    }

    public boolean isOutput() {
        return output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }

    public String getLabel(){ return label; }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitVariableExpression(this);
    }

    @Override
    public String toString(){
        return name;
    }
}
