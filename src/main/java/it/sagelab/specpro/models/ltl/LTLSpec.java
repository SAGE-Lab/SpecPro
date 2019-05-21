package it.sagelab.specpro.models.ltl;

import it.sagelab.specpro.models.InputRequirement;

import java.util.*;

public class LTLSpec {

    /** List of requirements to analyse **/
    private final List<Formula> requirements;

    /** List of invariants to maintain **/
    private final List<Formula> invariants;

    /** Boolean Vars */
    private final Map<String, Atom> symbolTable;

    private final Map<Formula, InputRequirement> spec2req;

    public LTLSpec() {
        requirements = new ArrayList<>();
        invariants = new ArrayList<>();
        symbolTable = new HashMap<>();
        spec2req = new HashMap<>();
    }

    /**
     * Add the formula in the requirements list.
     * @param f The formula to add.
     */
    public void addRequirement(Formula f) {
        this.requirements.add(f);
    }

    /**
     * Add the formula in the requirements list and map it to a InputRequirement object that can be retrieved later.
     * @param f The formula to add.
     * @param req The input requirement associated with the formula.
     */
    public void addRequirement(Formula f, InputRequirement req) {
        addRequirement(f);
        spec2req.put(f, req);
    }

    public InputRequirement getInputRequirement(Formula f) {
        return spec2req.get(f);
    }

    public InputRequirement getInputRequirement(int index) { return spec2req.get(getRequirement(index)); }

    /**
     * Add the formula in the invariance list.
     * @param f The formula to add.
     */
    public void addInvariant(Formula f) {
        this.invariants.add(f);
    }

    /**
     * Search and return the atom with the given name, or create a new ones.
     * @param name The name to search.
     * @return The atom object with the given name.
     */
    public Atom getOrCreateAtom(String name) {
        return symbolTable.computeIfAbsent(name, (k) -> new Atom(name));
    }

    public Collection<Atom> getAtoms() {
        return symbolTable.values();
    }

    public List<Formula> getRequirements() {
        return requirements;
    }

    public Formula getRequirement(int index) { return requirements.get(index); }

    public List<Formula> getInvariants() {
        return invariants;
    }

    public Map<String, Atom> getSymbolTable() {
        return symbolTable;
    }
}
