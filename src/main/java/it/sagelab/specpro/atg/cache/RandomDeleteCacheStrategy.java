package it.sagelab.specpro.atg.cache;

import it.sagelab.specpro.collections.ListUtils;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomDeleteCacheStrategy extends CacheStrategy {

    private final long maxEntries;

    public RandomDeleteCacheStrategy(long maxEntries) {
        this.maxEntries = maxEntries;
    }

    @Override
    protected void addToCache(Set<Edge> edgeSet, Set<Assignment> assignmentSet) {
        super.addToCache(edgeSet, assignmentSet);

        if(cache.size() >= maxEntries) {
            List<Set<Edge>> keys = new ArrayList<>(cache.keySet());
            List<Set<Edge>>[] split = ListUtils.randomSplit(keys, 2);

            for(Set<Edge> k: split[0]) {
                cache.remove(k);
            }
        }

    }

    @Override
    public List<Set<Assignment>> getCachedAssignments(List<Edge> edges) {
        List<Set<Assignment>> assignaments = new ArrayList<>();
        assignaments.add(getAssignments(edges));
        return assignaments;
    }


}
