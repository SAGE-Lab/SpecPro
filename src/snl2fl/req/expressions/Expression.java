package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;

/**
 * @author = Simone Vuotto
 * creation date  =  03/09/15.
 */
abstract public class Expression {

    abstract boolean value();

    public abstract void accept(ExpressionVisitor visitor);
}
