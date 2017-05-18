package it.unige.pat2fl.req.expressions;

/**
 * Created by Simone Vuotto on 02/09/15.
 */
abstract public class VariableExpression extends Expression{
    protected final String name;

    public VariableExpression(String name){
        this.name = name;
    }

    public String getName(){ return name; }
    @Override
    public String toString(){
        return name;
    }
}
