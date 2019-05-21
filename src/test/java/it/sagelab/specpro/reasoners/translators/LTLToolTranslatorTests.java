package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.fe.PSPFrontEnd;
import it.sagelab.specpro.models.ltl.LTLSpec;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LTLToolTranslatorTests {

    @ParameterizedTest()
    @CsvFileSource(resources = "/nusmv-comparison.csv")
    public void testTranslateReqFileToNusmv(String inputFile, String outputFile) throws IOException {
        translateAndCheck(new NuSMVTranslator(), inputFile, outputFile);
    }

    @ParameterizedTest()
    @CsvFileSource(resources = "/aalta-comparison.csv")
    public void testTranslateReqFileToAalta(String inputFile, String outputFile) throws IOException {
        translateAndCheck(new AALTATranslator(), inputFile, outputFile);
    }

    /* Help methods */

    private void translateAndCheck(LTLToolTranslator translator, String inputFileName, String outputFileName) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        File inputFile = new File(getClass().getResource("/" + inputFileName).getFile());
        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseFile(inputFile.getAbsolutePath());
        translator.translate(new PrintStream(stream), spec);

        File outputFile = new File(getClass().getResource("/" + outputFileName).getFile());
        assertEquals(FileUtils.readFileToString(outputFile), stream.toString());

    }

}
