package it.sagelab.specpro.atg;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.List;

public class TestSequence {

    private final List<Edge> path;
    private final List<Assignment> assignmentList;


    public TestSequence(List<Edge> path, List<Assignment> assignmentList) {
        this.path = path;
        this.assignmentList = assignmentList;
    }

    public List<Edge> getPath() {
        return path;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    @Override
    public String toString() {
        return assignmentList.toString();
    }
}
