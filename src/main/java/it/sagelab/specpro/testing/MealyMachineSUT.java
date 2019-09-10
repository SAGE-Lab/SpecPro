package it.sagelab.specpro.testing;

import it.sagelab.specpro.models.fsm.Edge;
import it.sagelab.specpro.models.fsm.MealyMachine;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MealyMachineSUT extends SUT {

    private final MealyMachine mealyMachine;

    private String currentState;

    public MealyMachineSUT(MealyMachine mealyMachine) {
        super(new HashSet<>(mealyMachine.getInputs()), new HashSet<>(mealyMachine.getOutputs()));
        this.mealyMachine = mealyMachine;
        currentState = mealyMachine.getResetState();
    }

    @Override
    public void reset() {
        currentState = mealyMachine.getResetState();
    }

    @Override
    public Assignment exec(Assignment input) throws InvalidInputException {
        Set<Edge> enabledEdges = enabledEdges(input);

        if(enabledEdges.size() == 0) {
            currentState = null;
            throw new InvalidInputException("Impossible to execute input " + input);
        }

        Edge e = enabledEdges.iterator().next();
        currentState = e.getTarget();
        return e.getOutputs();
    }

    private Set<Edge> enabledEdges(Assignment input) {
        if(currentState == null) {
            return Collections.emptySet();
        }

        return mealyMachine.outgoingEdgesOf(currentState).stream().filter(e -> e.getInputs().isCompatible(input)).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return mealyMachine.toString();
    }

}
