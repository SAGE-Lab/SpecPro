package it.sagelab.specpro.atg.cache;

import it.sagelab.specpro.collections.ListUtils;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MaxLengthCacheStrategy extends CacheStrategy {

    private int maxLength;

    public MaxLengthCacheStrategy(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public List<Set<Assignment>> getCachedAssignments(List<Edge> edges) {
        List<Set<Assignment>> assignments = new ArrayList<>();

        int nSplits = edges.size() / maxLength;
        if(edges.size() % maxLength != 0)
            ++nSplits;
        List<Edge>[] edgesSplitted = ListUtils.split(edges, nSplits);

        for(List<Edge> split: edgesSplitted) {
            assignments.add(getAssignments(split));
        }
        return assignments;
    }

}
