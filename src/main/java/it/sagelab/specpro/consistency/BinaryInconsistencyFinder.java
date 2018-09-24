package it.sagelab.specpro.consistency;

import it.sagelab.specpro.models.psp.Requirement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BinaryInconsistencyFinder extends InconsistencyFinder{

    public BinaryInconsistencyFinder(ConsistencyChecker cc) {
        super(cc);
    }

    public List<Requirement> run() {
        ArrayList<Requirement> inconsistentRequirements = new ArrayList<>();
        List<Requirement> requirements = new ArrayList<>(cc.getParser().getRequirements());
        find(requirements, inconsistentRequirements);
        return inconsistentRequirements;
    }

    protected void find(List<Requirement> reqs, List<Requirement> incReqs) {
        //System.out.println("reqs: " + reqs.stream().map(requirement -> requirement.getReqId()).collect(toList()));
        //System.out.println("incReqs: " + incReqs.stream().map(requirement -> requirement.getReqId()).collect(toList()));
        if(reqs.size() <= 1) {
            if(isConsistent(null, incReqs))
                incReqs.addAll(reqs);
            return;
        }
        List<Requirement>[] r = split(reqs);

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

    protected boolean isConsistent(List<Requirement> requirements, List<Requirement> inconsitentRequirements) {
        cc.getParser().getRequirements().clear();
        if(requirements != null)
            cc.getParser().getRequirements().addAll(requirements);
        if(inconsitentRequirements != null)
            cc.getParser().getRequirements().addAll(inconsitentRequirements);
        // System.out.println("reqs: " + cc.getParser().getRequirements().stream().map(Requirement::getReqId).collect(toList()));

        ConsistencyChecker.Result result = cc.run();
        return result == ConsistencyChecker.Result.CONSISTENT;

    }

    protected List<Requirement>[] split(List<Requirement> requirements) {
        Collections.shuffle(requirements);
        int m = requirements.size() / 2;
        List<Requirement> r1 = requirements.subList(0, m);
        List<Requirement> r2 = requirements.subList(m, requirements.size());
        return new List[]{r1, r2};
    }

}
