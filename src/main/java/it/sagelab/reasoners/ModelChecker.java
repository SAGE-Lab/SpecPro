package it.sagelab.reasoners;

import org.apache.commons.io.IOUtils;
import it.sagelab.fe.snl2fl.Snl2FlTranslator;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public abstract class ModelChecker {

    public enum Result {
        SAT,
        UNSAT,
        FAIL
    }

    private Thread thread;
    private final long timeout;
    private Snl2FlTranslator translator;
    protected String message;
    protected String execPath;

    public ModelChecker(long timeout, Snl2FlTranslator translator) {
        this.timeout = timeout;
        this.translator = translator;
        this.execPath = System.getenv("SNL2FL_MODELCHECKER");

    }

    public Result run(String filePath) {
        this.message = null;
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        String[] command = getCommand(filePath);
        try {
            process = rt.exec(command);

            process.waitFor(timeout, TimeUnit.SECONDS);

            if(process.isAlive())
                throw new InterruptedException("Timeout exceeded");

            String output = IOUtils.toString(process.getInputStream());
            String errorMessage = IOUtils.toString(process.getErrorStream());

            return parseOutput(output, errorMessage);

        } catch (IOException | InterruptedException e) {
            if(process != null)
                process.destroyForcibly();
                this.message = e.getMessage();
            return Result.FAIL;
        }
    }

    public void runAsync(String filePath, Consumer<Result> consumer) {
        thread = new Thread(() -> consumer.accept(run(filePath)));
        thread.start();
    }

    public String getMessage() {
      return message;
    }

    protected abstract String[] getCommand(String filePath);

    protected abstract Result parseOutput(String output, String error);

    public Snl2FlTranslator getTranslator() {
        return translator;
    }

    public void setTranslator(Snl2FlTranslator translator) {
        this.translator = translator;
    }

    public String getExecPath() {
        return execPath;
    }

    public void setExecPath(String execPath) {
        this.execPath = execPath;
    }
}
