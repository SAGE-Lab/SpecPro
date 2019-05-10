package it.sagelab.specpro.consistency;

import it.sagelab.specpro.models.InputRequirement;
import it.sagelab.specpro.models.ltl.Formula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class BinaryInconsistencyFinder extends InconsistencyFinder{

    private Random random;

    public BinaryInconsistencyFinder(ConsistencyChecker cc) {
        super(cc);
        long seed = System.currentTimeMillis();
        //System.out.println("seed: " + seed);
        random = new Random(seed);
    }

    public List<InputRequirement> run() {
        ArrayList<Formula> inconsistentRequirements = new ArrayList<>();
        List<Formula> requirements = new ArrayList<>(cc.getLTLSpec().getRequirements());
        find(requirements, inconsistentRequirements);
        return inconsistentRequirements.stream().map(r -> cc.getLTLSpec().getInputRequirement(r)).collect(toList());
    }

    protected void find(List<Formula> reqs, List<Formula> incReqs) {
        //System.out.println("reqs: " + reqs.stream().map(requirement -> requirement.getReqId()).collect(toList()));
        //System.out.println("incReqs: " + incReqs.stream().map(requirement -> requirement.getReqId()).collect(toList()));
        if(reqs.size() <= 1) {
            if(isConsistent(null, incReqs))
                incReqs.addAll(reqs);
            return;
        }
        List<Formula>[] r = split(reqs);

        if(r[0].size() > 1 && r[1].size() > 1) {
            if (!isConsistent(r[0], incReqs)) {
                find(r[0], incReqs);
                return;
            }

            if (!isConsistent(r[1], incReqs)) {
                find(r[1], incReqs);
                return;
            }
        }


        incReqs.addAll(r[0]);
        find(r[1], incReqs);
        incReqs.removeAll(r[0]);
        find(r[0], incReqs);
    }

    protected boolean isConsistent(List<Formula> requirements, List<Formula> inconsitentRequirements) {
        cc.getLTLSpec().getRequirements().clear();
        if(requirements != null)
            cc.getLTLSpec().getRequirements().addAll(requirements);
        if(inconsitentRequirements != null)
            cc.getLTLSpec().getRequirements().addAll(inconsitentRequirements);
        // System.out.println("reqs: " + cc.getParser().getRequirements().stream().map(Requirement::getReqId).collect(toList()));

        ConsistencyChecker.Result result = cc.run();
        return result == ConsistencyChecker.Result.CONSISTENT;

    }

    protected List<Formula>[] split(List<Formula> requirements) {
        Collections.shuffle(requirements, random);
        int m = requirements.size() / 2;
        List<Formula> r1 = requirements.subList(0, m);
        List<Formula> r2 = requirements.subList(m, requirements.size());
        return new List[]{r1, r2};
    }

}
