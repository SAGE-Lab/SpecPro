package it.sagelab.specpro.models.ltl.assign;

import java.util.ArrayList;

public class Trace extends ArrayList<Assignment> {

    public Trace() {

    }

    public Trace(Assignment... assignments) {
        for(Assignment a: assignments){
            add(a);
        }
    }

    public void append(Trace trace) {
        this.addAll(trace);
    }

    public Trace merge(Trace t) {
        if(this.size() > t.size()) {
            return t.merge(this);
        }
        Trace merged = new Trace();

        for(int i = 0; i < t.size(); ++i) {
            Assignment a = t.get(i).combine(this.get(i));
            if(a == null) {
                System.out.println(this);
                System.out.println(t);
                return null;
            }
            merged.add(a);
        }

        for(int i = t.size(); i < this.size(); ++i) {
            merged.add(this.get(i));
        }

        return merged;
    }

    public Assignment last() {
        return get(size() - 1);
    }

}
