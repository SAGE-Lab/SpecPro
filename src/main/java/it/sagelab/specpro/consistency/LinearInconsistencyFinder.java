package it.sagelab.specpro.consistency;

import it.sagelab.specpro.models.InputRequirement;
import it.sagelab.specpro.models.ltl.Formula;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LinearInconsistencyFinder extends InconsistencyFinder {

    public LinearInconsistencyFinder(ConsistencyChecker cc) {
        super(cc);
    }

    public List<InputRequirement> run(){
        return run((req, res) -> {});
    }

    public List<InputRequirement> run(BiConsumer<InputRequirement, ConsistencyChecker.Result> onProgress) {
        ArrayList<InputRequirement> inconsistentRequirements = new ArrayList<>();


        ConsistencyChecker.Result result = cc.run();

        if(result == ConsistencyChecker.Result.CONSISTENT)
            return inconsistentRequirements;


        String outputFilePath = cc.getOutputFilePath();
        cc.setOutputFilePath(outputFilePath + ".tmp");

        long seed = System.currentTimeMillis();
        //System.out.println("seed: " + seed);
        Collections.shuffle(cc.getLTLSpec().getRequirements(), new Random(seed));

        int reqSize = cc.getLTLSpec().getRequirements().size();
        int i = 0;
        while(i++ < reqSize && result != ConsistencyChecker.Result.FAIL) {
            Formula r = cc.getLTLSpec().getRequirements().remove(0);
            result = cc.run();

            if(result == ConsistencyChecker.Result.CONSISTENT) {
                inconsistentRequirements.add(cc.getLTLSpec().getInputRequirement(r));
                cc.getLTLSpec().getRequirements().add(r);
            }

            if(onProgress != null)
                onProgress.accept(cc.getLTLSpec().getInputRequirement(r), result);
        }

        new File(cc.getOutputFilePath()).delete();
        cc.setOutputFilePath(outputFilePath);

        if(result == ConsistencyChecker.Result.FAIL)
            return null;
        else
            return inconsistentRequirements;
    }

}
