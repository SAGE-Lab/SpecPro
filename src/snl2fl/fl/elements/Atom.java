/*
 * 
 */
package snl2fl.fl.elements;

import snl2fl.fl.visitor.FormulaVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class Atom.
 *
 * @author Simone Vuotto
 */
public class Atom extends Formula {
    
    /** The name. */
    final String name;

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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.elements.Formula#accept(snl2fl.fl.visitor.FormulaVisitor)
     */
    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitAtom(this);
    }
}
