package it.sagelab.specpro.testing;

import it.sagelab.specpro.collections.SetUtils;
import it.sagelab.specpro.models.fsm.Edge;
import it.sagelab.specpro.models.fsm.MealyMachine;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.*;

public class MealyMutantGenerator {

    private final MealyMachine mealyMachine;
    private Random random = new Random();

    public MealyMutantGenerator(MealyMachine mealyMachine) {
        this.mealyMachine = mealyMachine;
    }

    public MealyMachine generateMutant(int nMutations) {
        MealyMachine mutant = new MealyMachine();
        mutant.setInputs(mealyMachine.getInputs());
        mutant.setOutputs(mealyMachine.getOutputs());
        mutant.setResetState(mealyMachine.getResetState());

        for(String state: mealyMachine.vertexSet()) {
            mutant.addVertex(state);
        }
        for(Edge edge: mealyMachine.edgeSet()) {
            Edge newEdge = new Edge(edge.getSource(), edge.getTarget(), new Assignment(edge.getInputs()), new Assignment(edge.getOutputs()));
            mutant.addEdge(newEdge.getSource(), newEdge.getTarget(), newEdge);
        }

        for(int i = 0; i < nMutations; ++i) {
            Edge e = SetUtils.pickRandom(mutant.edgeSet());
            int r = random.nextInt(2);

            switch (r) {
                case 0:
                    Set<String> vertexes = new HashSet<>(mutant.vertexSet());
                    vertexes.remove(e.getTarget());
                    String nextState = SetUtils.pickRandom(vertexes);
                    if(nextState == null) {
                        ++nMutations;
                    } else {
                        mutant.removeEdge(e);
                        Edge newEdge = new Edge(e.getSource(), nextState, e.getInputs(), e.getOutputs());
                        mutant.addEdge(newEdge.getSource(), newEdge.getTarget(), newEdge);
                    }
                    break;
                case 1:
                    Atom flipAtom = SetUtils.pickRandom(e.getOutputs().getAssignments().keySet());
                    Assignment newOutput = new Assignment();
                    for(Map.Entry<Atom,Boolean> entry: e.getOutputs().getAssignments().entrySet()) {
                        if(entry.getKey().equals(flipAtom)) {
                            newOutput.add(entry.getKey(), !entry.getValue());
                        } else {
                            newOutput.add(entry.getKey(), entry.getValue());
                        }
                    }
                    mutant.removeEdge(e);
                    Edge newEdge = new Edge(e.getSource(), e.getTarget(), e.getInputs(), newOutput);
                    mutant.addEdge(newEdge.getSource(), newEdge.getTarget(), newEdge);
            }

        }

        return mutant;
    }





}
