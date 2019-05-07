package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.ltl.*;

import java.io.PrintStream;
import java.util.Stack;

public class FlatFormulaPrinter extends FormulaPrinter {


    private class StrStack extends Formula {

        String str;

        StrStack(String str) {
            this.str = str;
        }

        @Override
        public void accept(FormulaVisitor visitor) {
        }
    }


    /**
     * Instantiates a new formula printer
     *
     * @param c the PrintStream to write in
     */
    public FlatFormulaPrinter(PrintStream c) {
        super(c);
    }

    public void print(Formula f) {
        Stack<Formula> s = new Stack<Formula>();

        Formula curr = f;
        s.push(f);
        while (!s.empty()) {
            curr = s.pop();


            if(curr instanceof BinaryOperator) {

                if(curr != f) {
                    print("(");
                    s.push(new StrStack(")"));
                }
                BinaryOperator op = decompose((BinaryOperator) curr);

                s.push(op.getRightFormula());
                s.push(new StrStack(" " + opToStr(op) + " "));
                s.push(op.getLeftFormula());

            } else if(curr instanceof UnaryOperator) {
                print(opToStr((UnaryOperator)curr) + " ");
                s.push(((UnaryOperator)curr).getChild());
            } else if(curr instanceof Atom){
                print(((Atom)curr).getName());
            } else {
                print(((StrStack)curr).str);
            }

        }



    }

}
