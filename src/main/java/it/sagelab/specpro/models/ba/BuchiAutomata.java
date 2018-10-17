package it.sagelab.specpro.models.ba;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.DirectedPseudograph;

public class BuchiAutomata extends DirectedPseudograph<Vertex, Edge> {

    public BuchiAutomata(EdgeFactory<Vertex, Edge> ef) {
        super(ef);
    }

    public Vertex[] getInitStates() {
        return vertexSet().stream().filter(v -> v.getId().equals("I")).toArray(n-> new Vertex[n]);
    }
}
