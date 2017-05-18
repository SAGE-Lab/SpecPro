package it.unige.pat2fl.req.requirements;

import java.util.List;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.visitor.ScopeVisitor;

/**
 * Created by Simone Vuotto on 03/09/15.
 */
public class Scope {

    private final Type type;
    private final List<Expression> expressions;

    public enum Type {
        GLOBALLY, BEFORE, AFTER, AFTER_UNTIL, BETWEEN;

    }

    public Scope(Type type, List<Expression> expressions){
        this.type = type;
        this.expressions = expressions;
    }

    public Type getType() { return type; }

    public List<Expression> getExpressions() { return expressions; }

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
