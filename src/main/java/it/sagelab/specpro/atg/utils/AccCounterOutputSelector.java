package it.sagelab.specpro.atg.utils;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccCounterOutputSelector extends RandomOutputSelector {

    HashMap<List<Edge>, Long> cache = new HashMap<>();
    final Set<Atom> oututs;

    public AccCounterOutputSelector(Set<Atom> oututs) {
        this.oututs = oututs;
    }

    private Long getNumbOfAcceptanceStates(List<Edge> list) {
        return cache.computeIfAbsent(list, l -> l.stream().map(Edge::getTarget).filter(Vertex::isAcceptingState).count());
    }

    private List<Edge> findBest(List<Edge> l1, List<Edge> l2) {
        long n1 = getNumbOfAcceptanceStates(l1);
        long n2 = getNumbOfAcceptanceStates(l2);
        if(n1 > n2)
            return l1;
        if(n2 > n1)
            return l2;

        int m1 = computeActiveOutputs(l1);
        int m2 = computeActiveOutputs(l2);

        if(m1 < m2)
            return l1;
        else
            return l2;
    }

    private int computeActiveOutputs(List<Edge> list) {
        int m = 0;

        for(Edge e: list) {
            for(Assignment a: e.getAssigments()) {
                for(Map.Entry<Atom, Boolean> entry: a.getAssignments().entrySet()) {
                    if(oututs.contains(entry.getKey()) && entry.getValue() == true)
                        ++m;
                }
            }
        }

        return m;
    }

    @Override
    protected List<Edge> select(Set<List<Edge>> paths) {
        return paths.stream().reduce(this::findBest).get();
    }
}
