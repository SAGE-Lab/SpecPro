package it.sagelab.specpro.reasoners;

import it.sagelab.specpro.reasoners.translators.LTLToolTranslator;

public class GenericMC extends ModelChecker {

    public GenericMC(LTLToolTranslator translator) { super(translator); }

    @Override
    protected String[] getCommand(String filePath) {
        return null;
    }

    @Override
    protected Result parseOutput(String output, String error) {
        return null;
    }
}
