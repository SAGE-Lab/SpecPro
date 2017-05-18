package snl2fl.req.visitor;

import snl2fl.req.requirements.Scope;

/**
 * @author Simone Vuotto
 * creation date 02/09/15.
 */
public interface ScopeVisitor {

    public void visitGloballyScope(Scope scope);

    public void visitBeforeScope(Scope scope);

    public void visitBetweenScope(Scope scope);

    public void visitAfterScope(Scope scope);

    public void visitAfterUntilScope(Scope scope);
}
