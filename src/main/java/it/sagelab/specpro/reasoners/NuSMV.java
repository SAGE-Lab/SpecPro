package it.sagelab.specpro.reasoners;

import it.sagelab.specpro.reasoners.translators.nusmv.NuSMVTranslator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NuSMV extends ModelChecker {

    public NuSMV(long timeout) {
        super(timeout, new NuSMVTranslator());
        this.execPath = System.getenv("SPECPRO_NUSMV");
    }

    @Override
    protected String[] getCommand(String filePath) {
        if(execPath == null) {
            throw new IllegalArgumentException("Environment varibale SPECPRO_NUSMV not defined.");
        }
        return new String[]{this.execPath, filePath};
    }

    @Override
    protected Result parseOutput(String output, String error) {


        Pattern regex = Pattern.compile("-- specification .* is (?<res>(true|false))");

        Matcher matcher = regex.matcher(output);
        if(!matcher.find()) {
            message = "[ERROR] no response found\n"+error;
            return Result.FAIL;
        }
        else {
            message = output;
        }

        String res = matcher.group("res");
        return res.equals("false") ? Result.SAT : Result.UNSAT;
    }
}
