package it.sagelab.specpro.models;

/**
 * The Class ContextBasedVisitor.
 *
 * @author Simone Vuotto
 * creation date  30/09/15.
 * @param <T> the generic type
 */
public abstract class ContextBasedVisitor<T> {

    /** The context. */
    private T context;

    /**
     * Instantiates a new context based visitor.
     *
     * @param c the c
     */
    public ContextBasedVisitor(T c) {
        this.context = c;
    }

    /**
     * Gets the context.
     *
     * @return the context
     */
    public T getContext() { return context; }

    /**
     * Sets the context.
     *
     * @param context the new context
     */
    public void setContext(T context) { this.context = context; }

}
