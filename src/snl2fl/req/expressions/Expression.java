package it.unige.pat2fl.req.expressions;


import it.unige.pat2fl.req.visitor.ExpressionVisitor;

/**
 * Created by Simone Vuotto on 02/09/15.
 */
abstract public class Expression {

    abstract boolean value();

    public abstract void accept(ExpressionVisitor visitor);
}
