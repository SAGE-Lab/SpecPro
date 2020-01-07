package it.sagelab.specpro.testing.oracles;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.ac.EndsWithAcceptanceStateCondition;

public class FLTLOracle extends ValidPrefixOracle {

    public FLTLOracle(BuchiAutomaton automaton) {
        super(automaton);
        this.getExplorer().getAcceptanceConditions().clear();
        this.getExplorer().addAcceptanceCondition(new EndsWithAcceptanceStateCondition());
   }
}