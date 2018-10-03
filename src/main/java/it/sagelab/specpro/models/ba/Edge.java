package it.sagelab.specpro.models.ba;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;

import java.util.Map;

public class Edge extends DefaultEdge {

    Vertex source, target;
    String label;
    Map<String, Attribute> attributes;

    public Edge(Vertex source, Vertex target, String label, Map<String, Attribute> attributes) {
        this.source = source;
        this.target = target;
        this.label = label;
        this.attributes = attributes;
    }

    @Override
    public Vertex getSource() {
        return source;
    }

    @Override
    public Vertex getTarget() {
        return target;
    }

    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + target + ")";
    }
}
