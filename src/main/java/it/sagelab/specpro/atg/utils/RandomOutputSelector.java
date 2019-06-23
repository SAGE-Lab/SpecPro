package it.sagelab.specpro.atg.utils;

import it.sagelab.specpro.atg.TestSequence;
import it.sagelab.specpro.models.ba.BAExplorer;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.ac.EndsWithAcceptanceStateCondition;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class RandomOutputSelector implements OutputSelector {

    private final BAExplorer explorer;

    public RandomOutputSelector() {
        explorer = new BAExplorer();
        explorer.addAcceptanceCondition(new EndsWithAcceptanceStateCondition());
    }

    @Override
    public TestSequence selectOutput(BuchiAutomaton automaton, List<Assignment> inputs) {

        explorer.setLength(inputs.size());

        Set<List<Edge>> paths = explorer.findInducedPaths(automaton, inputs).toSet();

        List<Edge> output = select(paths);

        TestSequence oututSequence = new TestSequence();
        for(int i = 0; i < output.size(); ++i) {
            Edge edge = output.get(i);
            Assignment assignment = edge.getRandAssignment().combine(inputs.get(i));
            oututSequence.add(edge, assignment);
        }


        return oututSequence;
    }

    protected List<Edge> select(Set<List<Edge>> paths) {
        Random random = new Random();
        int n = random.nextInt(paths.size());
        Iterator<List<Edge>> itr = paths.iterator();
        int i = 0;
        while (i++ < n)
            itr.next();
        return itr.next();
    }
}
