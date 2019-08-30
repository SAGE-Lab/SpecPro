package it.sagelab.specpro.atg.utils;

import it.sagelab.specpro.atg.TestSequence;
import it.sagelab.specpro.models.ba.BAExplorer;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ba.ac.LassoShapedAcceptanceCondition;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.*;

public class AccCounterOutputSelector extends RandomOutputSelector {

    private final BAExplorer explorer;


    HashMap<List<Edge>, Long> cache = new HashMap<>();
    final LTLSpec spec;

    public AccCounterOutputSelector(LTLSpec spec) {
        this.spec = spec;
        explorer = new BAExplorer();

        explorer.addAcceptanceCondition(new LassoShapedAcceptanceCondition());
    }

    private Long getNumbOfAcceptanceStates(List<Edge> list) {
        return cache.computeIfAbsent(list, l -> l.stream().map(Edge::getTarget).filter(Vertex::isAcceptingState).count());
    }

    @Override
    public TestSequence selectOutput(BuchiAutomaton automaton, List<Assignment> inputs) {

        explorer.setLength(inputs.size());

        Set<List<Edge>> paths =  explorer.findInducedPaths(automaton, inputs).toSet();
        int counter = 0;
        while(paths.isEmpty() && counter < 5) {
            inputs.add(new Assignment());
            explorer.setLength(inputs.size());
            paths = explorer.findInducedPaths(automaton, inputs).toSet();
        }

        if(paths.isEmpty())
            return null;


        List<Edge> output = select(paths);

        TestSequence oututSequence = new TestSequence();
        for(int i = 0; i < output.size(); ++i) {
            Edge edge = output.get(i);
            Assignment inputAssigment = inputs.get(i);
            Optional<Assignment> optional = edge.getAssigments().stream().filter(a -> inputAssigment.isCompatible(a))
                    .sorted(Comparator.comparingInt(this::score).reversed()).map(a -> a.combine(inputAssigment)).findFirst();
            if(optional.isPresent() && optional.get() != null) {
                oututSequence.add(edge, optional.get());
            } else {
                throw new RuntimeException("Selected an incompatible edge!");
            }

        }


        return oututSequence;
    }

    private List<Edge> findBest(List<Edge> l1, List<Edge> l2) {
        long n1 = getNumbOfAcceptanceStates(l1);
        long n2 = getNumbOfAcceptanceStates(l2);
        if(n1 > n2)
            return l1;
        if(n2 > n1)
            return l2;

        if(scoreList(l1) >= scoreList(l2))
            return l1;
        else
            return l2;
//        int i1 = countVars(l1, spec.getInputVariables());
//        int i2 = countVars(l2, spec.getInputVariables());
//
//        if(i1 > i2)
//            return l1;
//        if(i2 > i1)
//            return l2;
//
//        int o1 = countVars(l1, spec.getOutputVariables());
//        int o2 = countVars(l2, spec.getOutputVariables());
//
//        if(o1 < o2)
//            return l1;
//        else
//            return l2;
    }

    private int countVars(List<Edge> list, Set<Atom> vars) {
        int counter = 0;

        for(Edge e: list) {

            for(Assignment a: e.getAssigments()) {
                int aCounter = 0;
                for(Map.Entry<Atom, Boolean> entry: a.getAssignments().entrySet()) {
                    if(vars.contains(entry.getKey()))
                        ++aCounter;
                }

                if(counter < aCounter)
                    counter = aCounter;
            }
        }

        return counter;
    }

    private int scoreList(List<Edge> list) {
        int counter = 0;
        for(Edge e: list) {
            counter += e.getAssigments().stream().mapToInt(this::score).max().getAsInt();
        }

        return counter;
    }

    private int score(Assignment a) {
        int inputs = 0, outputs = 0;

        for(Map.Entry<Atom, Boolean> entry: a.getAssignments().entrySet()) {
            if(spec.getInputVariables().contains(entry.getKey()))
                ++inputs;
            if(spec.getOutputVariables().contains(entry.getKey()))
                ++outputs;
        }

        return -outputs;
        //return inputs - outputs;
    }

    @Override
    protected List<Edge> select(Set<List<Edge>> paths) {
        return paths.stream().reduce(this::findBest).get();
    }
}
