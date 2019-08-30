package it.sagelab.specpro.atg.utils;

import it.sagelab.specpro.atg.TestCase;
import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.models.ba.BAExplorer;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OutputGenerator {

    private final BAExplorer explorer;

    public OutputGenerator() {
        explorer = new BAExplorer();
    }

    public BAExplorer getExplorer() {
        return explorer;
    }

    public TestCase generate(BuchiAutomaton automaton, LTLSpec spec, List<Assignment> inputs) {
        TestCase tc = new TestCase(inputs);

        Set<List<Edge>> paths =  explorer.findInducedPaths(automaton, inputs).toSet();

        int kMax = automaton.vertexSet().size();
        int k = 0;
        while((paths == null || paths.size() == 0) && k < kMax) {
            inputs.add(new Assignment());
            paths =  explorer.findInducedPaths(automaton, inputs).toSet();
            ++k;
        }


        if(paths != null) {
            for (List<Edge> p : paths) {

                for(int i = 0; i < p.size(); ++i) {
                    Edge e = p.get(i);
                    HashSet<Assignment> assignments = new HashSet<>();
                    for(Assignment a: e.getAssigments()) {
                        if(a.isCompatible(inputs.get(i))) {
                            assignments.add(a.filter(spec.getOutputVariables()));
                        }
                    }

                    p.set(i, new Edge(e.getSource(), e.getTarget(), assignments));
                }

                SequenceBuilder<Assignment> sequenceBuilder = new SequenceBuilder<>(p);
                for (List<Assignment> output : sequenceBuilder) {
                    tc.addOutput(output);
                    //tc.addOutput(filter(spec, assignments));
                }
            }
        }

        return tc;
    }

    public List<Assignment> filter(LTLSpec spec, List<Assignment> assignments) {

        for(int i = 0; i < assignments.size(); ++i) {
            assignments.set(i, assignments.get(i).filter(spec.getOutputVariables()));
        }

        //BAFilter.trim(assignments);

        return assignments;
    }

}
