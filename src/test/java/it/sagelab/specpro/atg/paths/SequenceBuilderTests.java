package it.sagelab.specpro.atg.paths;

import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SequenceBuilderTests {

    @Test
    public void testPathIteratorWithMultipleConditions() {
        ArrayList<Edge> path = new ArrayList<>();
        path.add(new Edge(null, null, "a | b", null));
        path.add(new Edge(null, null, "c | d", null));

        for(List<Assignment> assignmentList: new SequenceBuilder<Assignment>(path)) {
            System.out.println(assignmentList);
        }
    }
}
