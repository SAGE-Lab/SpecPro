package it.sagelab.specpro.models.ltl;

import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.ltl.assign.NumericAssignment;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.expressions.Expression;
import it.sagelab.specpro.models.translators.RangeMapVisitor;

import java.util.*;

public class LTLDcSpec extends LTLSpec {

    /** The range map. */
    private Map<String, TreeMap<Float, Atom[]>> rangeMap;

    /** Set of Input variables **/
    private final Set<String> numericInputVariables;

    /** Set of Output variables */
    private final Set<String> numericOutputVariables;

    public LTLDcSpec(List<? extends Requirement> requirements) {
        computeRangeMap(requirements);
        numericInputVariables = new HashSet<>();
        numericOutputVariables = new HashSet<>();
    }

    public Map<String, TreeMap<Float, Atom[]>> getRangeMap() {
        return rangeMap;
    }

    /**
     * Gets the lists of thresholds in ascending order defined for the given variable.
     * @param varName the name of the variable.
     * @return a sorted list of thresholds.
     */
    public List<Float> getThresholds(String varName) {
        ArrayList<Float> thresholds = new ArrayList<>();
        NavigableSet<Float> keys = rangeMap.get(varName).navigableKeySet();
        for(Float f: keys) {
            thresholds.add(f);
        }
        return thresholds;
    }

    /**
     * Gets the set of numeric variable names used in the specification
     * @return the set of numeric variable names
     */
    public Set<String> getNumericVariableNames() {
        return rangeMap.keySet();
    }

    /**
     * Returns the right assignments of LTL(Dc) boolean variables given the numerical assignment of a variable.
     * @param var the numeric variable name
     * @param value the value assigned to the variable
     * @return the corresponding boolean assignment (with the extendend LTL(Dc) boolean variables)
     */
    public Assignment getDcAssignment(String var, Float value) {
        Assignment assignment = new Assignment();
        Float prev = Float.NEGATIVE_INFINITY;
        NavigableSet<Float> keys = rangeMap.get(var).navigableKeySet();
        for(Float f: keys) {
            Atom[] atoms = rangeMap.get(var).get(f);
            assignment.add(atoms[0], value.compareTo(prev) > 0 && value.compareTo(f) < 0);
            assignment.add(atoms[1], value.compareTo(f) == 0);
            prev = f;
        }

        return assignment;
    }

    public Float[] getRangeFromDcAssignment(String var, Assignment assignment) {
        Float prev = Float.NEGATIVE_INFINITY;
        NavigableSet<Float> keys = rangeMap.get(var).navigableKeySet();
        for(Float f: keys) {
            Atom[] atoms = rangeMap.get(var).get(f);
            if(assignment.contains(atoms[0]) || assignment.contains(atoms[1])) {
                if(assignment.contains(atoms[0]) && assignment.getValue(atoms[0])) {
                    return new Float[] {prev, f};
                }

                if(assignment.contains(atoms[1]) && assignment.getValue(atoms[1])) {
                    return new Float[] {f};
                }

                prev = f;
            }
        }

        return new Float[]{prev, Float.POSITIVE_INFINITY};

    }

    public NumericAssignment fromBool2Numeric(Assignment assignment) {
        NumericAssignment na = new NumericAssignment();
        HashMap<String, Assignment> numericAssignments = new HashMap<>();
        for(Atom a: assignment.getAssignments().keySet()) {
            if(a.getProperty(Atom.PROPERTY_NUMERIC) == null) {
                na.add(a, assignment.getValue(a));
            } else {
                numericAssignments.putIfAbsent((String) a.getProperty(Atom.PROPERTY_NUMERIC_VAR), new Assignment());
                numericAssignments.get(a.getProperty(Atom.PROPERTY_NUMERIC_VAR)).add(a, assignment.getValue(a));
            }
        }

        for(Map.Entry<String, Assignment> entry: numericAssignments.entrySet()) {
            Float[] range = getRangeFromDcAssignment(entry.getKey(), entry.getValue());
            if(range.length < 2) {
                na.add(new Atom(entry.getKey()), range[0]);
            } else  {
                na.add(new Atom(entry.getKey()), (range[0] + range[1]) / 2);
            }
        }

        return na;
    }

    public Assignment fromNumeric2Bool(NumericAssignment numericAssignment) {
        Assignment assignment = new Assignment(numericAssignment);

        for(Map.Entry<Atom, Float> entry: numericAssignment.getFloatAssignments().entrySet()) {
            assignment.add(getDcAssignment(entry.getKey().getName(), entry.getValue()));
        }

        return assignment;
    }

    /**
     * Gets the equal atom.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @return the equal atom
     */
    public Atom getEqualAtom(String varName, Float threshold) {
        return rangeMap.get(varName).get(threshold)[1];
    }

    /**
     * Gets the lower atom.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @return the lower atom
     */
    public Atom getLowerAtom(String varName, Float threshold) {
        return rangeMap.get(varName).get(threshold)[0];
    }

    /**
     * Compute range map.
     *
     * @param requirements the psp
     */
    private void computeRangeMap(List<? extends Requirement> requirements){
        RangeMapVisitor rangeMapVisitor = new RangeMapVisitor();
        for(Requirement r : requirements) {
            for (Expression e : r.getScope().getExpressions())
                e.accept(rangeMapVisitor);
            for (Expression e : r.getExpressions())
                e.accept(rangeMapVisitor);
        }
        rangeMap = rangeMapVisitor.getRangeMap();
    }

    public Set<String> getNumericInputVariables() {
        return numericInputVariables;
    }

    public Set<String> getNumericOutputVariables() {
        return numericOutputVariables;
    }

    public void addNumericInputVariable(String var) {
        this.numericInputVariables.add(var);
    }

    public void addNumericOutputVariable(String var) {
        this.numericOutputVariables.add(var);
    }
}
