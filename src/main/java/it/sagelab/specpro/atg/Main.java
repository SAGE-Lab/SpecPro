package it.sagelab.specpro.atg;
import it.sagelab.specpro.atg.coverage.ConditionCoverage;
import it.sagelab.specpro.atg.coverage.StateCoverage;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void printPathAsFormula(List<Assignment> path, PrintWriter pw) {

        pw.print("LTLSPEC G(");
        for(int i = 0; i < path.size(); ++i) {
            for(int j = 0; j < i; ++j)
                pw.print("X ");
            pw.print("!(");
            Assignment a = path.get(i);
            int count = 0;
            for(Map.Entry<Atom, Boolean> entry: a.getAssignments().entrySet()) {
                if(entry.getValue().equals(Boolean.FALSE))
                    pw.print("!");
                pw.print(entry.getKey().getName());
                if(count < a.getAssignments().size() - 1)
                    pw.print(" & ");
                ++count;
            }
            pw.print(")");

            if(i < path.size() - 1)
                pw.print(" | ");
            else
                pw.println(")");
        }
    }


    public static void main(String[] args) throws IOException {

        String filePath = "robot.req";

        AutomaticTestGenerator rtg = new AutomaticTestGenerator();
        rtg.setCoverageCriterion(new StateCoverage());
        rtg.parseRequirements(filePath);
        //rtg.addFormula("G(!t -> (!p U t) | G!p)");
        //rtg.addFormula("G(!r -> F p)");

        rtg.setMaxLength(8);
        rtg.setFilterInputVars(false);
        Map<BuchiAutomaton, Set<TestSequence>> tests = rtg.generate();

    }

}
