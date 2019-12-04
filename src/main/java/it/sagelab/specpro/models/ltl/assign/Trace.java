package it.sagelab.specpro.models.ltl.assign;

import java.util.ArrayList;

public class Trace extends ArrayList<Assignment> {

    private int startCycle;

    public Trace() {
        startCycle = -1;
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

    public int getStartCycle() {
        return startCycle;
    }

    public void setStartCycle(int startCycle) {
        this.startCycle = startCycle;
    }

    public Trace getPrefix(int length) {
        Trace t = new Trace();
        if(length < size()) {
            t.addAll(subList(0, length));
        } else {
            if(startCycle < 0) {
                throw new IllegalArgumentException("This trace does not have a cycle and length passed is greater then trace length");
            }

            t.addAll(this);
            int n = length - t.size();
            for(int i = 0; i < n; ++i) {
                t.add(this.get(startCycle + i % (size() - startCycle)));
            }

        }
        return t;
    }

}
