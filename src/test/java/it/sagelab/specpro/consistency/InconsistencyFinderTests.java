package it.sagelab.specpro.consistency;

import it.sagelab.specpro.fe.PSPFrontEnd;
import it.sagelab.specpro.models.InputRequirement;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InconsistencyFinderTests {

    @EnabledIf("systemEnvironment.get('SPECPRO_NUSMV') != null")
    @ParameterizedTest
    @CsvFileSource(resources = "/inconsistency.csv")
    public void testConsistencyWithNuSMV(String filePath, int mucSize) throws IOException {
        ModelChecker mc = new NuSMV(30);

        File inputFile = new File(getClass().getResource("/" + filePath).getFile());
        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseFile(inputFile.getAbsolutePath());

        ConsistencyChecker cc = new ConsistencyChecker(mc, spec, "test.nusmv");

        BinaryInconsistencyFinder incFinder = new BinaryInconsistencyFinder(cc);



        List<InputRequirement> requirementList = incFinder.run();

        assertEquals(mucSize, requirementList.size());

    }


}

