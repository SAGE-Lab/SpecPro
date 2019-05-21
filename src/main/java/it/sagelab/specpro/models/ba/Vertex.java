package it.sagelab.specpro.models.ba;

import org.jgrapht.io.Attribute;

import java.util.Collections;
import java.util.Map;

public class Vertex {

    final String id;
    final Map<String, Attribute> attributes;

    public Vertex(String id) {
        this.id = id;
        attributes = Collections.emptyMap();
    }

    public Vertex(String id, Map<String, Attribute> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    public boolean isAcceptingState() {
        return attributes.containsKey("peripheries");
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Vertex && ((Vertex)o).getId().equals(id))
            return true;
        return false;
    }
}
