package it.sagelab.specpro.atg;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.jgrapht.GraphPath;

import java.util.ArrayList;
import java.util.List;

public class TestSequence {

    private final List<Edge> path;
    private final List<Assignment> assignmentList;


    public TestSequence(List<Edge> path, List<Assignment> assignmentList) {
        this.path = path;
        this.assignmentList = assignmentList;
    }

    public TestSequence() {
        this.path = new ArrayList<>();
        this.assignmentList = new ArrayList<>();
    }

    public List<Edge> getPath() {
        return path;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void add(Edge e, Assignment a) {
        this.path.add(e);
        this.assignmentList.add(a);
    }

    public void add(Edge e) {
        path.add(e);
        assignmentList.add(e.getRandAssignment());
    }

    public void add(GraphPath<Vertex, Edge> graphPath) {
        if(graphPath != null) {
            for (Edge e : graphPath.getEdgeList()) {
                add(e);
            }
        }
    }

    @Override
    public String toString() {
        return assignmentList.toString();
    }

}
