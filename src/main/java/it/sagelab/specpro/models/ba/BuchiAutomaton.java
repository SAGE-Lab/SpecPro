package it.sagelab.specpro.models.ba;

import it.sagelab.specpro.models.ba.ac.LassoShapedAcceptanceCondition;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.DirectedPseudograph;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BuchiAutomaton extends DirectedPseudograph<Vertex, Edge> {

    public BuchiAutomaton(EdgeFactory<Vertex, Edge> ef) {
        super(ef);
    }

    public Vertex[] getInitStates() {
        return vertexSet().stream().filter(v -> v.getId().equals("I")).toArray(n-> new Vertex[n]);
    }

    public Set<Vertex> initStates() {
        return vertexSet().stream().filter(v -> v.getId().equals("I")).collect(Collectors.toSet());

    }

    public Iterator<List<Edge>> iterator(int length) {
        return new BuchiAutomatonIterator(this, new LassoShapedAcceptanceCondition(), length);
    }
}
