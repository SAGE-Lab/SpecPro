package it.sagelab.specpro.cli;

import it.sagelab.specpro.consistency.BinaryInconsistencyFinder;
import it.sagelab.specpro.consistency.ConsistencyChecker;
import it.sagelab.specpro.consistency.InconsistencyFinder;
import it.sagelab.specpro.consistency.LinearInconsistencyFinder;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.reasoners.Aalta;
import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.util.List;

public class MucCommand extends Command {

    @Override
    public String getName() {
        return "muc";
    }

    @Override
    public String getDescription() {
        return "Performs Minimaml Unsatisfiable Core of the given requirements with the selected model checker";
    }

    @Override
    public Options createOptionMenu() {
        Options options = new Options();

        OptionGroup mcGroup = new OptionGroup();
        mcGroup.addOption(new Option("a", "aalta", false, "translate for Aalta model checker. " +
                "In order to run muc with Aalta you have to set SPECPRO_AALTA environment variable."));
        mcGroup.addOption(new Option("n", "nusmv", false, "translate for NuSMV model checker (default). " +
                "In order to run muc with NuSMV you have to set SPECPRO_NUSMV environment variable."));
        options.addOptionGroup(mcGroup);

        options.addOption(null, "timeout", true, "The timeout for the model checker call in seconds");
        options.addOption(null, "alg", true, "Select the algorithm to use. The possible values are 'linear' or 'dichotomic' (default).");

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

        Snl2FlParser snl2FlParser = new Snl2FlParser();
        snl2FlParser.parseFile(inputFile);

        ConsistencyChecker consistencyChecker = new ConsistencyChecker(mc, snl2FlParser, "out.temp");
        InconsistencyFinder muc = null;
        if(commandLine.hasOption("alg")) {
            String value = commandLine.getOptionValue("alg");
            if ("linear".equals(value)) {
                muc = new LinearInconsistencyFinder(consistencyChecker);
            } else if ("binary".equals(value)) {
                muc = new BinaryInconsistencyFinder(consistencyChecker);
            } else {
                System.err.println("Value for option alg not valid");
                System.exit(-1);
            }
        } else {
            muc = new BinaryInconsistencyFinder(consistencyChecker);
        }

        List<Requirement> reqs = muc.run();
        if(reqs == null) {
            outStream.println("Fail occured during model checking call.");
            outStream.println(mc.getMessage());
        }
        else {
            outStream.println("# MUC of " + reqs.size() + " elements found: ");
            for (Requirement r : reqs) {
                outStream.println(r.getText());
            }
        }
    }
}
