package it.sagelab.specpro.models.ba;

import org.jgrapht.io.Attribute;
import org.jgrapht.io.ComponentUpdater;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.VertexProvider;

import java.util.Map;

public class DotBuilder implements VertexProvider<Vertex>, EdgeProvider<Vertex, Edge>, ComponentUpdater<Vertex> {

    int edgeId = 0;

    @Override
    public Vertex buildVertex(String id, Map<String, Attribute> attributes) {
        return new Vertex(id, attributes);
    }

    @Override
    public Edge buildEdge(Vertex from, Vertex to, String label, Map<String, Attribute> attributes) {
        return new Edge(from, to, label, attributes, ++edgeId);
    }

    @Override
    public void update(Vertex vertex, Map<String, Attribute> map) {
        vertex.getAttributes().putAll(map);
    }
}
