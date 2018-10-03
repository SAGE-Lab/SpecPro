package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.jgrapht.Graph;

import java.util.ArrayList;

public abstract class PathsFinder {

    private PathsBuilder pathsBuilder;
    protected Graph<Vertex, Edge> graph;
    protected Vertex[] initStates;
    protected ArrayList<Edge> path;


    public PathsFinder(Graph<Vertex, Edge> graph) {
        this.graph = graph;
        this.pathsBuilder = new PathsBuilder();
        initStates = graph.vertexSet().stream().filter(v -> v.getId().equals("I")).toArray(n-> new Vertex[n]);
        path = new ArrayList<>();
    }

    public abstract void find();

    public Trie<Assignment> getPaths() {
        return pathsBuilder.getTrie();
    }

    protected void addPath() {
        for(Edge e : path)
            pathsBuilder.addEdge(e);
        pathsBuilder.generateAllConditionsPaths();
    }
}
