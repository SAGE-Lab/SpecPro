package it.unige.pat2fl.req.visitor;

import it.unige.pat2fl.req.requirements.Scope;

/**
 * Created by Simone Vuotto on 02/10/15.
 */
public interface ScopeVisitor {

    public void visitGloballyScope(Scope scope);

    public void visitBeforeScope(Scope scope);

    public void visitBetweenScope(Scope scope);

    public void visitAfterScope(Scope scope);

    public void visitAfterUntilScope(Scope scope);
}
