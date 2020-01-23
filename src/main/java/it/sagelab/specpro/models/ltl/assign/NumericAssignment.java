package it.sagelab.specpro.models.ltl.assign;

import it.sagelab.specpro.models.ltl.Atom;

import java.util.HashMap;
import java.util.Map;

public class NumericAssignment extends Assignment {

    private final HashMap<Atom, Float> numericAssignmentsMap;

    public NumericAssignment(NumericAssignment assignment) {
        super(assignment);
        this.numericAssignmentsMap = (HashMap) assignment.numericAssignmentsMap.clone();
    }


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

    @Override
    public NumericAssignment combine(Assignment assignment) {
        try {
            NumericAssignment newAss = new NumericAssignment(this);
            newAss.add(assignment);
            newAss.setStartBeta(this.isStartBeta() || assignment.isStartBeta());
            if(assignment instanceof NumericAssignment) {
                for(Map.Entry<Atom, Float> ass: ((NumericAssignment) assignment).getFloatAssignments().entrySet()) {
                    newAss.add(ass.getKey(), ass.getValue());
                }
            }

            return newAss;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


    public Map<Atom, Float> getFloatAssignments() {
        return numericAssignmentsMap;
    }

}
