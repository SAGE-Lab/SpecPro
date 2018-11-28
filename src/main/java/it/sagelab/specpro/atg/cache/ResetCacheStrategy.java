package it.sagelab.specpro.atg.cache;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResetCacheStrategy extends CacheStrategy {

    @Override
    public void start() {
        super.start();
        cache.clear();
    }

    @Override
    public List<Set<Assignment>> getCachedAssignments(List<Edge> edges) {
        List<Set<Assignment>> list = new ArrayList<>();
        list.add(getAssignments(edges));
        return list;
    }
}
