package it.sagelab.specpro.models.ltl.assign;

import it.sagelab.specpro.models.ltl.Atom;

import java.util.HashMap;
import java.util.Map;

public class NumericAssignment extends Assignment {

    private final HashMap<Atom, Float> numericAssignmentsMap;

    public NumericAssignment() {
        numericAssignmentsMap = new HashMap<>();
    }

    public void add(Atom a, Float value) {
        if(numericAssignmentsMap.containsKey(a)) {
            if(numericAssignmentsMap.get(a) != value) {
                throw new IllegalArgumentException("Atom " + a.getName() + " has already a different value");
            }
        } else {
            numericAssignmentsMap.put(a, value);
        }
    }

    public Map<Atom, Float> getFloatAssignments() {
        return numericAssignmentsMap;
    }

}
