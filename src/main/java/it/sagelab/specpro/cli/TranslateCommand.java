package it.sagelab.specpro.cli;

import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.reasoners.Aalta;
import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import it.sagelab.specpro.reasoners.Panda;
import it.sagelab.specpro.reasoners.translators.NuSMVTranslator;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

import java.io.IOException;

public class TranslateCommand extends Command {

    @Override
    public String getName() {
        return "translate";
    }

    @Override
    public String getDescription() {
        return "Translate the input file in the corresponding consistency checking specification";
    }

    @Override
    public Options createOptionMenu() {
        Options options = new Options();

        OptionGroup mcGroup = new OptionGroup();
        mcGroup.addOption(new Option("a", "aalta", false, "translate for Aalta model checker"));
        mcGroup.addOption(new Option("p", "panda", false, "translate for Panda model checker"));
        mcGroup.addOption(new Option("n", "nusmv", false, "translate for NuSMV model checker (default)"));
        options.addOptionGroup(mcGroup);

        options.addOption(null, "noinvar", false, "translate without INVAR statemens (for NuSMV only)");

        return options;
    }


    @Override
    public void run(CommandLine commandLine) throws IOException {
        ModelChecker mc = selectModelChecker(commandLine);
        Snl2FlParser snl2FlParser = new Snl2FlParser();
        snl2FlParser.parseFile(inputFile);
        snl2FlParser.translate(mc.getTranslator(), outStream);
    }

    protected static ModelChecker selectModelChecker(CommandLine commandLine) {
        if(commandLine.hasOption("a")) {
            return new Aalta();
        }
        else if(commandLine.hasOption("p")) {
            return new Panda();
        }
        else {
            NuSMVTranslator translator = new NuSMVTranslator().setNoinvar(commandLine.hasOption("noinvar"));
            NuSMV mc = new NuSMV();
            mc.setTranslator(translator);
            return mc;
        }
    }

}
