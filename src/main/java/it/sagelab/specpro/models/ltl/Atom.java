/*
 * 
 */
package it.sagelab.specpro.models.ltl;

import it.sagelab.specpro.fe.ltl.visitor.FormulaVisitor;

/**
 * The Class Atom.
 *
 * @author Simone Vuotto
 */
public class Atom extends Formula {
    
    /** The name. */
    private final String name;

    /**
     * Instantiates a new atom.
     *
     * @param name the name
     */
    public Atom(String name){
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() { return name; }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitAtom(this);
    }
}
