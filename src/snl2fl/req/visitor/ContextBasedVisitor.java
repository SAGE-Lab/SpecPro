package snl2fl.req.visitor;

/**
 * @author Simone Vuotto
 * creation date  30/09/15.
 */
public abstract class ContextBasedVisitor<T> {

    private T context;

    public ContextBasedVisitor(T c) {
        this.context = c;
    }

    public T getContext() { return context; }

    public void setContext(T context) { this.context = context; }

}
