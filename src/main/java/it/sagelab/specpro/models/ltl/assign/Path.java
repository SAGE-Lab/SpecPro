package it.sagelab.specpro.models.ltl.assign;

import java.util.ArrayList;
import java.util.List;

public class Path implements Cloneable {

    private final ArrayList<Assignment> assignments;

    public Path() {
        assignments = new ArrayList<>();
    }

    public void append(Assignment a) {
        assignments.add(a);
    }

    public List<Assignment> get() {
        return assignments;
    }

    @Override
    public Path clone() {
        Path p = new Path();
        for(Assignment a : assignments) {
            p.append(a);
        }
        return p;
    }

}
