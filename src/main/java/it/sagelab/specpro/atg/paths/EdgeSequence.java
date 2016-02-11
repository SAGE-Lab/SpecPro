package it.sagelab.specpro.atg.paths;

import it.sagelab.specpro.collections.SequenceBuilderIterator;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.Iterator;
import java.util.List;

public class EdgeSequence implements Iterable<List<Assignment>> {

    private final List<Edge> path;

    public EdgeSequence(List<Edge> path) {
        this.path = path;
    }

    @Override
    public Iterator<List<Assignment>> iterator() {
        return new SequenceBuilderIterator<Assignment>(path);
    }
}
