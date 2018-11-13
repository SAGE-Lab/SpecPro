package it.sagelab.specpro.reasoners;

import it.sagelab.specpro.reasoners.translators.aalta.AALTATranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aalta extends ModelChecker {

    public Aalta() {
        super(new AALTATranslator());
        this.execPath = System.getenv("SPECPRO_AALTA");
    }

    public Aalta(long timeout) {
        super(timeout, new AALTATranslator());
        this.execPath = System.getenv("SPECPRO_AALTA");
    }

    @Override
    protected String[] getCommand(String filePath) {
        if(execPath == null) {
            throw new IllegalArgumentException("Environment varibale SPECPRO_AALTA not defined.");
        }
        return new String[]{"/bin/sh", "-c", "cat " + filePath + " | " + this.execPath};
    }

    @Override
    protected Result parseOutput(String output, String error) {
        Pattern regex = Pattern.compile("(sat|unsat)");

        Matcher matcher = regex.matcher(output);
        if(!matcher.find()) {
            message = "[ERROR] no response found\n"+error;
            return Result.FAIL;
        }
        else {
            message = output;
        }

        String res = matcher.group();
        return res.equals("sat") ? Result.SAT : Result.UNSAT;
    }
}
