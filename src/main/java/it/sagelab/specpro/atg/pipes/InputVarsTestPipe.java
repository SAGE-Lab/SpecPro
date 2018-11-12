package it.sagelab.specpro.atg.pipes;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InputVarsTestPipe implements TestPipe {

    private final Set<String> inputVars;
    private boolean addNegatedInput;

    public InputVarsTestPipe(Set<String> inputVars, boolean addNegatedInput) {
        this.inputVars = inputVars;
        this.addNegatedInput = addNegatedInput;
    }

    @Override
    public List<Assignment> process(List<Assignment> test) {
        if(test == null) {
            return null;
        }
        List<Assignment> testingPath = new ArrayList<>();
        boolean containsInput = false;
        for(Assignment a: test) {
            Assignment ass = new Assignment(a);
            for(String iVar: inputVars) {
                Atom atom = ass.getAtom(iVar);
                if(atom != null && a.getValue(atom)) {
                    containsInput = true;
                }
                else if(addNegatedInput) {
                    ass.add(new Atom(iVar), false);
                }
            }
            testingPath.add(ass);
        }

        if(containsInput) {
            return testingPath;
        } else {
            return null;
        }
    }
}
