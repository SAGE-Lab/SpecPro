package it.sagelab.specpro.models.ltl.assign;

import it.sagelab.specpro.models.ltl.Atom;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Assignment {

    private final HashMap<Atom, Boolean> assignmentsMap;

    private boolean startBeta = false;

    public Assignment() {
        assignmentsMap = new HashMap<>();
    }

    public Assignment(Assignment ass) {
        this((HashMap) ass.assignmentsMap.clone());
        this.startBeta = ass.startBeta;
    }

    public Assignment(HashMap<Atom, Boolean> assignment) {
        this.assignmentsMap = assignment;
    }

    public boolean isStartBeta() {
        return startBeta;
    }

    public void setStartBeta(boolean startBeta) {
        this.startBeta = startBeta;
    }

    public void add(Atom a, boolean value) {
        if(assignmentsMap.containsKey(a)) {
            if(assignmentsMap.get(a) != value)
                throw new IllegalArgumentException("Atom " + a.getName() + " has already a different value");
        } else {
            assignmentsMap.put(a, value);
        }
    }

    public void add(Assignment assignment) {
        for(Map.Entry<Atom, Boolean> entry : assignment.getAssignments().entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    public Map<Atom, Boolean> getAssignments() {
        return assignmentsMap;
    }

    /**
     * Create a new filtered assignment that contains only atoms given in the input set
     * @param vars the set of allowed atoms
     * @return a new filtered assignment
     */
    public Assignment filter(Set<Atom> vars) {
        Assignment a = new Assignment();
        a.setStartBeta(this.isStartBeta());
        for(Map.Entry<Atom, Boolean> entry: assignmentsMap.entrySet()) {
            if(vars.contains(entry.getKey())) {
                a.add(entry.getKey(), entry.getValue());
            }
        }
        return a;
    }

    public Assignment combine(Assignment assignment) {
        try {
            Assignment newAss = new Assignment(this);
            newAss.add(assignment);
            newAss.setStartBeta(this.isStartBeta() || assignment.isStartBeta());
            return newAss;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Atom getAtom(String varName) {
        for(Atom a:  assignmentsMap.keySet()) {
            if(a.getName().equals(varName)) {
                return a;
            }
        }
        return null;
    }

    public boolean contains(Atom a) { return assignmentsMap.containsKey(a); }

    public boolean getValue(Atom a) {
        return assignmentsMap.get(a);
    }

    public boolean contains(Assignment assignment) {
        for(Map.Entry<Atom, Boolean> entry : assignment.getAssignments().entrySet()) {
            Boolean v = this.assignmentsMap.get(entry.getKey());
            if(!entry.getValue().equals(v))
                return false;
        }
        return true;
    }

    /**
     * Checks if the input assignment is compatible with the current instance
     * @param assignment the assignment to check
     * @return true if the assignment is compatible with the current instance
     */
    public boolean isCompatible(Assignment assignment) {
        for(Map.Entry<Atom, Boolean> entry : assignment.getAssignments().entrySet()) {
            if(this.assignmentsMap.containsKey(entry.getKey())) {
                if(!this.assignmentsMap.get(entry.getKey()).equals(entry.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Assignment negate() {
        Assignment negated = new Assignment();
        for(Map.Entry<Atom, Boolean> entry: assignmentsMap.entrySet()) {
            negated.add(entry.getKey(), !entry.getValue());
        }
        return negated;
    }

    @Override
    public int hashCode() {
        return assignmentsMap.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Assignment))
            return false;
        if(obj == this)
            return true;
        return this.assignmentsMap.equals(((Assignment) obj).getAssignments()) && this.isStartBeta() == ((Assignment) obj).isStartBeta();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(startBeta)
            builder.append("*");
        builder.append("{");
        int counter = 0;
        for(Map.Entry<Atom, Boolean> entry: assignmentsMap.entrySet()) {
            if(entry.getValue() == false)
                builder.append("!");
            builder.append(entry.getKey().getName());
            if(++counter < assignmentsMap.size())
                builder.append(", ");
        }
        builder.append("}");
        return builder.toString();
    }

}
