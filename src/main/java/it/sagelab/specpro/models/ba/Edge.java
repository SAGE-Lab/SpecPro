package it.sagelab.specpro.models.ba;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;

import java.util.*;

public class Edge extends DefaultEdge implements Iterable<Assignment> {

    Vertex source, target;
    String label;
    Map<String, Attribute> attributes;
    Set<Assignment> assignmentSet = null;

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

    public Set<Assignment> getAssigments() {
        if(assignmentSet == null) {
            assignmentSet = parseLabel();
        }
        return assignmentSet;
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + target + ")";
    }


    /***************************************************
     *
     * Private Methods
     *
     ***************************************************/

    private Set<Assignment> parseLabel() {
        if(this.label == null)
            return Collections.emptySet();
        Set<Assignment> assignments = new HashSet<>();
        Assignment ass = new Assignment();
        assignments.add(ass);
        int index = 0;
        while(index < label.length()) {
            switch (label.charAt(index)) {
                case ' ':
                case '(':
                case ')':
                case '&':
                    ++index;
                    break;
                case '!':
                    index = parseAtom(label, index + 1, ass, false);
                    break;
                case '|':
                    ass = new Assignment();
                    assignments.add(ass);
                    ++index;
                    break;
                default:
                    if(label.charAt(index) == '_' || Character.isLetterOrDigit(label.charAt(index))) {
                        index = parseAtom(label, index, ass, true);
                    } else {
                        throw new RuntimeException("Formula \"" + label + "\" non recognized at index " + index);
                    }
            }
        }

        return assignments;
    }

    private int parseAtom(String formula, int index, Assignment a, boolean value) {
        StringBuilder builder = new StringBuilder();
        while(index < formula.length() &&
                (formula.charAt(index) == '_' ||
                        Character.isLetterOrDigit(formula.charAt(index)))) {
            builder.append(formula.charAt(index++));
        }
        String name = builder.toString();
        if(!name.equals("1")) {
            Atom atom = new Atom(name);
            a.add(atom, value);
        }
        return index;
    }

    @Override
    public Iterator<Assignment> iterator() {
        return getAssigments().iterator();
    }
}
