package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.ltl.*;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class LTLToolTranslator {

    /**
     * Set of forbidden names for the given LTL tool.
     * If an atom has a forbidden name, it is relabeled with @relabelAtomsWithForbiddenNames
     */
    private final Set<String> forbiddenVarNames;

    /**
     * Prefix string used to relabel atoms with a forbidden name
     */
    protected String varPrefix = "_";

    /**
     * Standard Constructor.
     *
     * @param forbiddenAtomsNames Set of forbidden atoms names.
     */
    public LTLToolTranslator(Set<String> forbiddenAtomsNames) {
        this.forbiddenVarNames = forbiddenAtomsNames;
    }

    /**
     * Translate the given ltl specification and write it in the given stream.
     *
     * @param stream The stream where to write the ltl specification.
     * @param spec The specification to translate.
     */
    public void translate(PrintStream stream, LTLSpec spec) {

        relabelAtomsWithForbiddenNames(spec);

        List<Formula> ltlFormulae = spec.getRequirements();
        List<Formula> invariants = spec.getInvariants();
        Formula consistencyFormula = BinaryOperator.conjunctiveFormula(ltlFormulae);
        if(invariants.size() > 0) {
            Formula invariantFormula = new UnaryOperator(BinaryOperator.conjunctiveFormula(invariants), UnaryOperator.Operator.GLOBALLY);
            consistencyFormula = new BinaryOperator(invariantFormula, consistencyFormula, BinaryOperator.Operator.AND);
        }

        getFormulaPrinter(stream).print(consistencyFormula);
    }

    public abstract FormulaPrinter getFormulaPrinter(PrintStream stream);

    /**
     * Relabel atoms that are named with one of the forbidden keywords
     *
     * @param spec The spec to relabel.
     */
    protected void relabelAtomsWithForbiddenNames(LTLSpec spec) {
        Set<Atom> relabeledAtoms = new HashSet<>();
        for(Atom a: spec.getAtoms()) {
            if(forbiddenVarNames.contains(a.getName())) {
                a.setLabel(varPrefix + a.getLabel());
                relabeledAtoms.add(a);
            }
        }

        for(Atom a: relabeledAtoms) {
            spec.getSymbolTable().remove(a.getName());
            spec.getSymbolTable().put(a.getLabel(), a);
        }
    }


}
