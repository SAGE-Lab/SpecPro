package it.sagelab.specpro.atg.pipes;

import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.List;

public interface TestPipe {

    List<Assignment> process(List<Assignment> test);

}
