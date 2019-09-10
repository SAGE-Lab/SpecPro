package it.sagelab.specpro.testing;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GoalsContainer<E> {

    private final Set<E> unsatisfiedGoals;
    private final HashMap<E, Integer> goalTrials;

    private int maxTries;

    public GoalsContainer(Set<E> unsatisfiedGoals) {
        this(unsatisfiedGoals, 1);
    }

    public GoalsContainer(Set<E> unsatisfiedGoals, int maxTries) {
        this.unsatisfiedGoals = new HashSet<>(unsatisfiedGoals);
        this.maxTries = maxTries;
        this.goalTrials = new HashMap<>();
    }

    public void setMaxTries(int maxTries) {
        this.maxTries = maxTries;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public boolean hasNextGoal() {
        return unsatisfiedGoals.size() > 0;
    }

    public E getNextGoal() {
        E goal = unsatisfiedGoals.iterator().next();

        int counter = goalTrials.getOrDefault(goal, 0);
        ++counter;
        goalTrials.put(goal, counter);

        if(counter >= maxTries) {
            unsatisfiedGoals.remove(goal);
        }

        return goal;
    }

    public void setSatisfiedGoal(E goal) {
        unsatisfiedGoals.remove(goal);
    }

    public static GoalsContainer<Edge> newTransitionCoverageGoal(BuchiAutomaton automaton) {
        return new GoalsContainer<>(automaton.edgeSet().stream().filter(e -> !e.getSource().getId().equals("I")).collect(Collectors.toSet()));
    }

    public static GoalsContainer<Vertex> newStateCoverageGoal(BuchiAutomaton automaton) {
        return new GoalsContainer<>(automaton.vertexSet().stream().filter(v -> !v.getId().equals("I")).collect(Collectors.toSet()));
    }

}
