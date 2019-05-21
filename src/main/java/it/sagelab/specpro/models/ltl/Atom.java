/*
 * 
 */
package it.sagelab.specpro.models.ltl;

import java.util.HashMap;

/**
 * The Class Atom.
 *
 * @author Simone Vuotto
 */
public class Atom extends Formula {

    public final static String PROPERTY_LABEL = "label";
    public final static String PROPERTY_NUMERIC = "numeric";
    public final static String PROPERTY_NUMERIC_VAR = "nvar";
    public final static String PROPERTY_NUMERIC_EQ = "eq";
    
    /** The name. */
    private final String name;

    private HashMap<String, Object> properties;

    /**
     * Instantiates a new atom.
     *
     * @param name the name
     */
    public Atom(String name){
        this.name = name;
        this.properties = null;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() { return name; }

    public void setLabel(String label) {
        this.setProperty(PROPERTY_LABEL, label);
    }

    public String getLabel() {
        Object label = getProperty(PROPERTY_LABEL);
        if(label == null)
            return name;
        return (String) label;
    }

    public void setProperty(String key, Object value) {
        if(properties == null) {
            properties = new HashMap<>();
        }
        properties.put(key, value);
    }

    public Object getProperty(String key) {
        if(properties == null)
            return null;
        return properties.get(key);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Atom && ((Atom)obj).getName().equals(name);
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitAtom(this);
    }
}
