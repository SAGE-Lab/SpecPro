package it.sagelab.specpro.atg.cache;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NoCacheStrategy extends CacheStrategy {
    @Override
    public List<Set<Assignment>> getCachedAssignments(List<Edge> edges) {
        ArrayList<Set<Assignment>> list = new ArrayList<>();
        for(Edge e: edges) {
            list.add(e.getAssigments());
        }
        return list;
    }
}
