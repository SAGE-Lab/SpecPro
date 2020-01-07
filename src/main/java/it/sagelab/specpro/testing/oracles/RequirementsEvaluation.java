package it.sagelab.specpro.testing.oracles;

import it.sagelab.specpro.models.InputRequirement;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.ltl.assign.Trace;
import it.sagelab.specpro.reasoners.LTL2BA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequirementsEvaluation {

    private HashMap<Formula, BuchiAutomaton> automatons;
    private LTLSpec spec;
    private TestOracle oracle;

    public RequirementsEvaluation(LTLSpec spec, TestOracle oracle) throws IOException {

        automatons = new HashMap<>();
        this.spec = spec;
        this.oracle = oracle;

        for(Formula f: spec.getRequirements()) {
            LTL2BA ltl2ba = new LTL2BA();

            ltl2ba.setType(LTL2BA.AutomatonType.NBA);
            LTLSpec reqSpec = new LTLSpec();
            reqSpec.addRequirement(f);
            BuchiAutomaton automaton = ltl2ba.translate(reqSpec);
            automaton.expandEdges();
            automatons.put(f, automaton);
        }
    }

    public List<Formula> findViolatedRequirements(Trace t) {
        ArrayList<Formula> violatedReqs = new ArrayList<>();

        for(Map.Entry<Formula, BuchiAutomaton> entry: automatons.entrySet()) {
            oracle.reset(entry.getValue());
            if(oracle.evaluateComplete(t) == TestOracle.Value.FALSE) {
                violatedReqs.add(entry.getKey());
            }
        }

        return violatedReqs;
    }


}
