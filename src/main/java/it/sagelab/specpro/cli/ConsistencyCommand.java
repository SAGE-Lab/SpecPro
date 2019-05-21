package it.sagelab.specpro.cli;

import it.sagelab.specpro.consistency.ConsistencyChecker;
import it.sagelab.specpro.fe.PSPFrontEnd;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.reasoners.Aalta;
import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

import java.io.IOException;

public class ConsistencyCommand extends Command {

    @Override
    public String getName() {
        return "consistency";
    }

    @Override
    public String getDescription() {
        return "Performs Consistency Checking with the selected model checker";
    }

    @Override
    public Options createOptionMenu() {
        Options options = new Options();

        OptionGroup mcGroup = new OptionGroup();
        mcGroup.addOption(new Option("a", "aalta", false, "translate for Aalta model checker. " +
                "In order to run consistency with Aalta you have to set SPECPRO_AALTA environment variable."));
        mcGroup.addOption(new Option("n", "nusmv", false, "translate for NuSMV model checker (default). " +
                "In order to run muc with NuSMV you have to set SPECPRO_NUSMV environment variable."));
        options.addOptionGroup(mcGroup);

        options.addOption(null, "timeout", true, "The timeout for the model checker call in seconds");

        return options;
    }

    @Override
    public void run(CommandLine commandLine) throws IOException {
        int timeout = 60;

        if(commandLine.hasOption("timeout")) {
            timeout = Integer.parseInt(commandLine.getOptionValue("timeout"));
        }

        ModelChecker mc;
        if(commandLine.hasOption("a")) {
            mc = new Aalta(timeout);
        } else {
            mc = new NuSMV(timeout);
        }

        ConsistencyChecker consistencyChecker = new ConsistencyChecker(mc, spec, "out.temp");
        ConsistencyChecker.Result result = consistencyChecker.run();
        outStream.println(result);
        if(result == ConsistencyChecker.Result.FAIL) {
            System.out.println(mc.getMessage());
        }

    }

}
