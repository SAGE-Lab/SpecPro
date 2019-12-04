package it.sagelab.specpro.consistency;

import it.sagelab.specpro.models.InputRequirement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public abstract class InconsistencyFinder {

    protected ConsistencyChecker cc;

    public InconsistencyFinder(ConsistencyChecker cc) {
        this.cc = cc;
    }

    public abstract List<InputRequirement> run();

    public Future<List<InputRequirement>> runAsync() {
        return runAsync(null);
    }

    public Future<List<InputRequirement>> runAsync(Consumer<List<InputRequirement>> consumer) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(() -> {
            List<InputRequirement> inconsistentRequirements = run();
            if(consumer != null) {
                consumer.accept(inconsistentRequirements);
            }
            return inconsistentRequirements;
        });
    }

}
