package it.sagelab.specpro.models.psp;

import java.util.List;

import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class Scope.
 *
 * @author Simone Vuotto
 */
public class Scope {

    /** The type. */
    private final Type type;
    
    /** The expressions. */
    private final List<Expression> expressions;

    /**
     * The Enum Type.
     */
    public enum Type {
        
        /** The globally. */
        GLOBALLY, 
        /** The before. */
        BEFORE, 
        /** The after. */
        AFTER, 
        /** The after until. */
        AFTER_UNTIL, 
        /** The between. */
        BETWEEN
    }

    /**
     * Instantiates a new scope.
     *
     * @param type the type
     * @param expressions the expressions
     */
    public Scope(Type type, List<Expression> expressions){
        this.type = type;
        this.expressions = expressions;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public Type getType() { return type; }

    /**
     * Gets the expressions.
     *
     * @return the expressions
     */
    public List<Expression> getExpressions() { return expressions; }

    /**
     * Accept.
     *
     * @param visitor the visitor
     */
    public void accept(ScopeVisitor visitor){
        switch (type) {
            case GLOBALLY:
                visitor.visitGloballyScope(this);
                break;
            case BEFORE:
                visitor.visitBeforeScope(this);
                break;
            case AFTER:
                visitor.visitAfterScope(this);
                break;
            case AFTER_UNTIL:
                visitor.visitAfterUntilScope(this);
                break;
            case BETWEEN:
                visitor.visitBetweenScope(this);
                break;
        }
    }

}
