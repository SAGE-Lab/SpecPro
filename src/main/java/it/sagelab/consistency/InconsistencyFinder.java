package it.sagelab.consistency;

import it.sagelab.models.psp.Requirement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class InconsistencyFinder {

    private ConsistencyChecker cc;

    public InconsistencyFinder(ConsistencyChecker cc) {
        this.cc = cc;
    }

    public List<Requirement> run(BiConsumer<Requirement, ConsistencyChecker.Result> onProgress) {
        ArrayList<Requirement> inconsistentRequirements = new ArrayList<>();


        ConsistencyChecker.Result result = cc.run();

        if(result == ConsistencyChecker.Result.CONSISTENT)
            return inconsistentRequirements;


        String outputFilePath = cc.getOutputFilePath();
        cc.setOutputFilePath(outputFilePath + ".tmp");

        int reqSize = cc.getParser().getRequirements().size();
        int i = 0;
        while(i++ < reqSize && result != ConsistencyChecker.Result.FAIL) {
            Requirement r = cc.getParser().getRequirements().remove(0);
            result = cc.run();

            if(result == ConsistencyChecker.Result.CONSISTENT) {
                inconsistentRequirements.add(r);
                cc.getParser().getRequirements().add(r);
            }

            if(onProgress != null)
                onProgress.accept(r, result);
        }

        new File(cc.getOutputFilePath()).delete();
        cc.setOutputFilePath(outputFilePath);

        if(result == ConsistencyChecker.Result.FAIL)
            return null;
        else
            return inconsistentRequirements;
    }

    public ExecutorService runAsync(BiConsumer<Requirement, ConsistencyChecker.Result> onProgress,
                         Consumer<List<Requirement>> consumer) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> consumer.accept(run(onProgress)));
        return executor;
    }

}
