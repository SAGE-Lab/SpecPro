package it.sagelab.specpro.reasoners;

import it.sagelab.specpro.reasoners.translators.PANDATranslator;

public class Panda extends ModelChecker {

    public Panda() { super(new PANDATranslator()); }

    @Override
    protected String[] getCommand(String filePath) {
        return null;
    }

    @Override
    protected Result parseOutput(String output, String error) {
        return null;
    }
}
