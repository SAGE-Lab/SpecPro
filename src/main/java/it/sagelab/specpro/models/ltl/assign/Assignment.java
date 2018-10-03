package it.sagelab.specpro.models.ltl.assign;

import it.sagelab.specpro.models.ltl.Atom;

import java.util.HashMap;
import java.util.Map;

public class Assignment {

    final private HashMap<Atom, Boolean> assignment;

    public Assignment() {
        assignment = new HashMap<>();
    }

    public void add(Atom a, boolean value) {
        if(assignment.containsKey(a)) {
            if(assignment.get(a) != value)
                throw new IllegalArgumentException("Atom " + a.getName() + " has already a different value");
        } else {
            assignment.put(a, value);
        }
    }

    public Map<Atom, Boolean> getAssignment() {
        return assignment;
    }

    @Override
    public int hashCode() {
        return assignment.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Assignment))
            return false;
        if(obj == this)
            return true;
        return this.assignment.equals(((Assignment) obj).getAssignment());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int counter = 0;
        for(Map.Entry<Atom, Boolean> entry: assignment.entrySet()) {
            if(entry.getValue() == false)
                builder.append("!");
            builder.append(entry.getKey().getName());
            if(++counter < assignment.size())
                builder.append(", ");
        }
        builder.append("}");
        return builder.toString();
    }

}
