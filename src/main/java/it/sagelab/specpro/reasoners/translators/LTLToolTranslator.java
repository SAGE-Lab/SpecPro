package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.fe.ltl.visitor.FormulaVisitor;
import it.sagelab.specpro.fe.snl2fl.Snl2FlTranslator;
import it.sagelab.specpro.models.psp.expressions.BooleanVariableExpression;
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

    public abstract void translate(PrintStream stream) throws IOException;

    public void translate(RequirementsBuilder builder, OutputStream stream) throws IOException {

        Map<String, VariableExpression> symbolTable = builder.getContext().getSymbolTable();
        for(String varName: forbiddenVarNames) {
            if(symbolTable.containsKey(varName) && symbolTable.get(varName) instanceof BooleanVariableExpression) {
                VariableExpression exp = symbolTable.remove(varName);
                exp.setName("_" + exp.getName());
                symbolTable.put(exp.getName(), exp);
            }
        }

        psp2ltl.setContext(builder);

        translate(new PrintStream(stream));
    }

    /**
     * Prints the list of formulae in conjunction form.
     * @param stream the stream
     * @param visitor the visitor to print the formulae
     * @param ltlFormulae the list of formulae
     */
    protected void printFormulaeInConjunction(PrintStream stream, FormulaVisitor visitor, List<Formula> ltlFormulae) {
        printFormulaeInConjunction(stream, visitor, ltlFormulae, " & ");
    }

    /**
     * Prints the list of formulae in conjunction form.
     * @param stream the stream
     * @param visitor the visitor to print the formulae
     * @param ltlFormulae the list of formulae
     * @param andSimbol the conjunction symbol
     */
    protected void printFormulaeInConjunction(PrintStream stream, FormulaVisitor visitor, List<Formula> ltlFormulae, String andSimbol) {
        for(int i=0; i < ltlFormulae.size(); ++i) {
            Formula formula = ltlFormulae.get(i);
            stream.print("(");
            formula.accept(visitor);
            stream.print(")");
            if(i < ltlFormulae.size() - 1)
                stream.print(andSimbol);
        }
    }
}
