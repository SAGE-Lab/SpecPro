package it.sagelab.specpro.reasoners;

import it.sagelab.specpro.reasoners.translators.panda.PANDATranslator;

public class Panda extends ModelChecker {

    public Panda(long timeout) {
        super(timeout, new PANDATranslator());
    }

    @Override
    protected String[] getCommand(String filePath) {
        return null;
    }

    @Override
    protected Result parseOutput(String output, String error) {
        return null;
    }
}
