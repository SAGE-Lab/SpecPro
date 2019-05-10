package it.sagelab.specpro.consistency;

import it.sagelab.specpro.fe.PSPFrontEnd;
import it.sagelab.specpro.models.InputRequirement;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import it.sagelab.specpro.fe.psp.Snl2FlParser;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InconsistencyFinderTests {

    @EnabledIf("systemEnvironment.get('SPECPRO_NUSMV') != null")
    @ParameterizedTest
    //@ValueSource(strings = {"test7.req", "fault1.req", "fault2.req", "fault3.req", "fault4.req", "fault5.req", "fault6.req"})
    @ValueSource(strings = {"inconsistency1.req", "inconsistency2.req"})
    public void testConsistencyWithNuSMV(String filePath) throws IOException {
        ModelChecker mc = new NuSMV(30);

        File inputFile = new File(getClass().getResource("/" + filePath).getFile());
        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseFile(inputFile.getAbsolutePath());

        Snl2FlParser parser = new Snl2FlParser();
        ConsistencyChecker cc = new ConsistencyChecker(mc, spec, "test.nusmv");

        BinaryInconsistencyFinder incFinder = new BinaryInconsistencyFinder(cc);



        List<InputRequirement> requirementList = incFinder.run();

//        for(Requirement r : requirementList)
//            System.out.println(r.getText());

    }


}

