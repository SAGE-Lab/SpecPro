package it.sagelab.specpro.models.ltl.assign;

import it.sagelab.specpro.models.ltl.Atom;

import java.util.HashMap;
import java.util.Map;

public class Assignment {

    final private HashMap<Atom, Boolean> assignmentsMap;

    public Assignment() {
        assignmentsMap = new HashMap<>();
    }

    public Assignment(HashMap<Atom, Boolean> assignment) {
        this.assignmentsMap = assignment;
    }

    public void add(Atom a, boolean value) {
        if(assignmentsMap.containsKey(a)) {
            if(assignmentsMap.get(a) != value)
                throw new IllegalArgumentException("Atom " + a.getName() + " has already a different value");
        } else {
            assignmentsMap.put(a, value);
        }
    }

    public Map<Atom, Boolean> getAssignments() {
        return assignmentsMap;
    }

    public Assignment combine(Assignment assignment) {
        Assignment newAss = new Assignment((HashMap) this.assignmentsMap.clone());

        try {
            for(Map.Entry<Atom, Boolean> entry : assignment.getAssignments().entrySet()) {
                newAss.add(entry.getKey(), entry.getValue());
            }
        } catch (IllegalArgumentException e) {
            return null;
        }

        return newAss;
    }

    public boolean contains(Assignment assignment) {
        for(Map.Entry<Atom, Boolean> entry : assignment.getAssignments().entrySet()) {
            Boolean v = this.assignmentsMap.get(entry.getKey());
            if(!entry.getValue().equals(v))
                return false;
        }
        return true;
    }

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

    @Override
    public int hashCode() {
        return assignmentsMap.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Assignment))
            return false;
        if(obj == this)
            return true;
        return this.assignmentsMap.equals(((Assignment) obj).getAssignments());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
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
