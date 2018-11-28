package it.sagelab.specpro.atg.cache;

import it.sagelab.specpro.collections.ListUtils;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CacheStrategy {

    protected final HashMap<Set<Edge>, Set<Assignment>> cache;

    public CacheStrategy() {
        cache = new HashMap<>();
    }

    public void start() {
        System.out.println("### cache size: " + cache.size());
    }


    public abstract List<Set<Assignment>> getCachedAssignments(List<Edge> edges);

    protected void addToCache(Set<Edge> edgeSet, Set<Assignment> combinedSet) {
        cache.put(edgeSet, combinedSet);
    }

    protected Set<Assignment> getAssignments(List<Edge> edges) {
        if(edges == null || edges.size() == 0)
            return Collections.emptySet();
        if(edges.size() == 1)
            return edges.get(0).getAssigments();

        Set<Edge> edgeSet = ListUtils.toSet(edges);
        Set<Assignment> assignments = cache.get(edgeSet);
        if(assignments != null)
            return assignments;

        List<Edge>[] split = ListUtils.split(edges, 2);
        Set<Assignment> s1 = getAssignments(split[0]);
        Set<Assignment> s2 = getAssignments(split[1]);

        Set<Assignment> combinedSet = combine(s1, s2);
        addToCache(edgeSet, combinedSet);
        return combinedSet;
    }

    protected Set<Assignment> combine(Set<Assignment> s1, Set<Assignment> s2) {
        return s1.parallelStream().flatMap(a1 -> s2.stream().map(a2 -> a1.combine(a2))).filter(a -> a != null).collect(Collectors.toSet());
    }

}
