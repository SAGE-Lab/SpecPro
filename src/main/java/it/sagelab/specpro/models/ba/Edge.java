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
    final int id;

    public Edge(Vertex source, Vertex target, Set<Assignment> assignments, int id) {
        this.source = source;
        this.target = target;
        this.label = null;
        this.attributes = Collections.emptyMap();
        this.assignmentSet = assignments;
        this.id = id;
    }

    public Edge(Vertex source, Vertex target, String label, Map<String, Attribute> attributes, int id) {
        this.source = source;
        this.target = target;
        this.label = label;
        this.attributes = attributes;
        this.id = id;
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

    public int getId() {
        return id;
    }

    void setAssignmentSet(Set<Assignment> assignmentSet) {
        this.assignmentSet = assignmentSet;
    }

    public Set<Assignment> getAssigments() {
        if(assignmentSet == null) {
            assignmentSet = parseLabel();
        }
        return assignmentSet;
    }

    /**
     * Returns a random assignment among the ones associated with the edge
     *
     * @return an assignment
     */
    public Assignment getRandAssignment() {
        if(getAssigments().size() > 0) {
            return getAssigments().iterator().next();
        } else {
            return new Assignment();
        }
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + target + ")";
    }

    @Override
    public Iterator<Assignment> iterator() {
        return getAssigments().iterator();
    }

    @Override
    public int hashCode(){
        return id;
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

}
