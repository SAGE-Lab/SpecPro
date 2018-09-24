package it.sagelab.specpro.fe.snl2fl;

import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.expressions.BooleanVariableExpression;
import it.sagelab.specpro.reasoners.translators.aalta.AALTATranslator;
import it.sagelab.specpro.reasoners.translators.nusmv.NuSMVTranslator;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class Snl2FlParserTests {

    @ParameterizedTest()
    @CsvFileSource(resources = "/nusmv-comparison.csv")
    public void translateReqFileToNusmv(String inputFile, String outputFile) throws IOException {
        translateAndCheck(new NuSMVTranslator(), inputFile, outputFile);
    }

    @ParameterizedTest()
    @CsvFileSource(resources = "/aalta-comparison.csv")
    public void translateReqFileToAalta(String inputFile, String outputFile) throws IOException {
        translateAndCheck(new AALTATranslator(), inputFile, outputFile);
    }

    @Test
    public void parseGloballyUniversalityPattern() {
        String pattern = "Globally, it is always the case that X holds.";

        Snl2FlParser parser = new Snl2FlParser();

        parser.parseString(pattern);

        assertNotNull(parser.getRequirements());
        assertEquals(1, parser.getRequirements().size());

        checkGloballyUniversalityPattern(parser, "REQ1");
    }

    @Test
    public void parseGloballyUniversalityPatternWithoutScope() {
        String pattern = "It is always the case that X holds.";

        Snl2FlParser parser = new Snl2FlParser();

        parser.parseString(pattern);

        checkGloballyUniversalityPattern(parser, "REQ1");
    }

    @Test
    public void parseGloballyUniversalityPatternWithReqID() {
        Snl2FlParser parser = new Snl2FlParser();

        String pattern = "[ReqId=R1] Globally, it is always the case that X holds.";
        parser.parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R1");

        pattern = "[REQID=R2] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R2");

        pattern = "[ID=R3] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R3");

        pattern = "[id=R4] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R4");

        pattern = "[Id=R5] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R5");

        pattern = "[R6] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R6");

        pattern = "[reqid=R7] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R7");
    }

    /* Help methods */

    private void translateAndCheck(Snl2FlTranslator translator, String inputFileName, String outputFileName) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        File inputFile = new File(getClass().getResource("/" + inputFileName).getFile());
        new Snl2FlParser().parseFile(inputFile.getAbsolutePath()).translate(translator, new PrintStream(stream));

        File outputFile = new File(getClass().getResource("/" + outputFileName).getFile());
        assertEquals(FileUtils.readFileToString(outputFile), stream.toString());

    }

    private void checkGloballyUniversalityPattern(Snl2FlParser parser, String reqId) {
        assertNotNull(parser.getRequirements());
        assertEquals(1, parser.getRequirements().size());
        Requirement r = parser.getRequirement(0);

        assertEquals(reqId, r.getReqId());
        Assertions.assertEquals(it.sagelab.specpro.models.psp.Scope.Type.GLOBALLY, r.getScope().getType());
        assertEquals(Collections.emptyList(), r.getScope().getExpressions());
        assertEquals(1, r.getExpressions().size());
        assertTrue(r.getExpressions().get(0) instanceof BooleanVariableExpression);
        assertEquals("X", ((BooleanVariableExpression) r.getExpressions().get(0)).getName());
    }

}
