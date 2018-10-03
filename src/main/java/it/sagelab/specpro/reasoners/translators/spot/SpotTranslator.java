package it.sagelab.specpro.reasoners.translators.spot;

import it.sagelab.specpro.fe.ltl.visitor.FormulaVisitor;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import it.sagelab.specpro.models.translators.PSP2LTL;
import it.sagelab.specpro.reasoners.translators.LTLToolTranslator;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpotTranslator extends LTLToolTranslator implements FormulaVisitor {

    private PrintStream stream;

    private static final Set<String> forbiddenVarNames = Stream.of("X", "W", "U", "xor").collect(Collectors.toSet());

    public SpotTranslator(PSP2LTL translator) { super(translator, forbiddenVarNames); }


    public SpotTranslator() {
        super(forbiddenVarNames);
    }

    @Override
    public void translate(PrintStream printStream) throws IOException {
        this.stream = printStream;

        List<Formula> invariants = psp2ltl.getInvariants();
        List<Formula> ltlFormulae = psp2ltl.translate();

        if(invariants.size() > 0) {
            stream.print("G(");
            this.printFormulaeInConjunction(stream, this, invariants);
            stream.print(")");
            stream.print(" & ");
        }
        this.printFormulaeInConjunction(stream, this, ltlFormulae);

//        for(Formula f: ltlFormulae) {
//            f.accept(this);
//        }

    }

    @Override
    public void visitUnaryOperator(UnaryOperator unaryOperator) {
        switch (unaryOperator.getOperator()) {
            case NOT:
                stream.print("!");
                break;
            case NEXT:
                stream.print("X");
                break;
            case GLOBALLY:
                stream.print("[]");
                break;
            case EVENTUALLY:
                stream.print("<>");
                break;
        }
        visitFormula(unaryOperator.getChild());

    }

    @Override
    public void visitBinaryOperator(BinaryOperator binaryOperator) {
        visitFormula(binaryOperator.getLeftFormula());

        switch (binaryOperator.getOperator()) {
            case AND:
                stream.print(" & ");
                break;
            case OR:
                stream.print(" | ");
                break;
            case XOR:
                stream.print(" xor ");
                break;
            case IMPLICATION:
                stream.print(" -> ");
                break;
            case EQUIVALENCE:
                stream.print(" <-> ");
                break;
            case UNTIL:
                stream.print(" U ");
                break;
            case WEAK_UNTIL:
                stream.print(" W ");
                break;
        }

        visitFormula(binaryOperator.getRightFormula());
    }

    @Override
    public void visitAtom(Atom atom) {
        stream.print(atom.getName());
    }


    private void visitFormula(Formula f) {
        if(f instanceof Atom) {
            f.accept(this);
        } else {
            stream.print("(");
            f.accept(this);
            stream.print(")");
        }
    }
}
