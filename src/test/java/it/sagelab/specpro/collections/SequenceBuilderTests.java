package it.sagelab.specpro.collections;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequenceBuilderTests {

    @Test
    public void testPathIteratorWithMultipleConditions() {
        ArrayList<Edge> path = new ArrayList<>();
        path.add(new Edge(null, null, "a | b", null, 0));
        path.add(new Edge(null, null, "c | d", null, 0));

        HashSet<Atom> atoms = new HashSet<>();
        int count = 0;
        for(List<Assignment> assignmentList: new SequenceBuilder<Assignment>(path)) {
            ++count;
            assertEquals(2, assignmentList.size());
            for(Assignment a : assignmentList)
                atoms.addAll(a.getAssignments().keySet());
        }

        assertEquals(4, count);
        assertEquals(4, atoms.size());
    }
}
