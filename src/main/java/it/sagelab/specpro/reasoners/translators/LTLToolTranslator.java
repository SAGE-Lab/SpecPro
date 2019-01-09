package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.FormulaVisitor;
import it.sagelab.specpro.fe.snl2fl.Snl2FlTranslator;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import it.sagelab.specpro.models.psp.expressions.VariableExpression;
import it.sagelab.specpro.models.translators.PSP2LTL;
import it.sagelab.specpro.fe.snl2fl.parser.RequirementsBuilder;
import it.sagelab.specpro.models.ltl.Formula;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class LTLToolTranslator implements Snl2FlTranslator {

    private final Set<String> forbiddenVarNames;

    /** The intermediate ltl psp2ltl. */
    final protected PSP2LTL psp2ltl;

    public LTLToolTranslator(Set<String> forbiddenVarNames) {
        psp2ltl = new PSP2LTL();
        this.forbiddenVarNames = forbiddenVarNames;
    }

    public LTLToolTranslator(PSP2LTL psp2ltl, Set<String> forbiddenVarNames) {
        this.psp2ltl = psp2ltl;
        this.forbiddenVarNames = forbiddenVarNames;
    }

    public PSP2LTL getLTLTranslator() {
        return psp2ltl;
    }

    public void translate(PrintStream stream) {
        List<Formula> ltlFormulae = psp2ltl.translate();
        List<Formula> invariants = psp2ltl.getInvariants();
        Formula consistencyFormula = BinaryOperator.conjunctiveFormula(ltlFormulae);
        if(invariants.size() > 0) {
            Formula invariantFormula = new UnaryOperator(BinaryOperator.conjunctiveFormula(invariants), UnaryOperator.Operator.GLOBALLY);
            consistencyFormula = new BinaryOperator(invariantFormula, consistencyFormula, BinaryOperator.Operator.AND);
        }

        consistencyFormula.accept(getFormulaPrinter(stream));
    }

    public void translate(RequirementsBuilder builder, PrintStream stream) {

        Map<String, VariableExpression> symbolTable = builder.getContext().getSymbolTable();
        for(String varName: forbiddenVarNames) {
            if(symbolTable.containsKey(varName) && symbolTable.get(varName).getType() == VariableExpression.Type.BOOLEAN) {
                VariableExpression exp = symbolTable.remove(varName);
                exp.setLabel("_" + exp.getLabel());
                symbolTable.put(exp.getLabel(), exp);
            }
        }

        psp2ltl.setContext(builder);

        translate(stream);
    }

    public abstract FormulaPrinter getFormulaPrinter(PrintStream stream);


}
