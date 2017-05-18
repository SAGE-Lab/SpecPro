/**
 * Created by Simone Vuotto on 16/10/15.
 */
package snl2fl.fl.elements;

import snl2fl.fl.visitor.FormulaVisitor;

public class Atom extends Formula {
    final String name;

    public Atom(String name){
        this.name = name;
    }

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
