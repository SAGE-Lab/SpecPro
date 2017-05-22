package snl2fl.req.requirements;

import java.util.List;

import snl2fl.req.expressions.Expression;

/**
 * The Class Requirement.
 *
 * @author Simone Vuotto
 */
public abstract class Requirement {
    
    /** The scope. */
    private final Scope scope;
    
    /** The expressions. */
    private final List<Expression> expressions;

    /**
     * Instantiates a new requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public Requirement(Scope scope, List<Expression> expressions){
        this.scope = scope;
        this.expressions = expressions;
    }

    /**
     * Gets the scope.
     *
     * @return the scope
     */
    public Scope getScope() { return scope; }

    /**
     * Gets the expressions.
     *
     * @return the expressions
     */
    public List<Expression> getExpressions() { return expressions; }

    /**
     * Key.
     *
     * @return the string
     */
    public String key() {
        return this.getClass().toString() + "_" + scope.getType().toString();
    }
}

