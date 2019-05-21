package it.sagelab.specpro.reasoners;

import it.sagelab.specpro.reasoners.translators.LTLToolTranslator;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.function.Consumer;

public abstract class ModelChecker {

    public enum Result {
        SAT,
        UNSAT,
        FAIL
    }

    private Thread thread;
    private long timeout;
    private LTLToolTranslator translator;
    protected String message;
    protected String execPath;

    public ModelChecker(long timeout, LTLToolTranslator translator) {
        this.timeout = timeout;
        this.translator = translator;
        this.execPath = "";
    }

    public ModelChecker(LTLToolTranslator translator) {
        this.translator = translator;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Result run(String filePath) {
        this.message = null;
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        String[] command = getCommand(filePath);
        try {

            ProcessBuilder builder = new ProcessBuilder(command);
            //builder.redirectErrorStream(true);

            builder.redirectOutput(ProcessBuilder.Redirect.PIPE);
            builder.redirectError(ProcessBuilder.Redirect.PIPE);
            process = builder.start();

            /* Read output and error streams in separate threads to avoid deadlock */
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

            final Process finalProcess = process;
            Future<String> fOutput = newFixedThreadPool.submit(() -> IOUtils.toString(finalProcess.getInputStream()));
            Future<String> fError = newFixedThreadPool.submit(() -> IOUtils.toString(finalProcess.getErrorStream()));
            newFixedThreadPool.shutdown();

            /* Wait for the process to end or until timeout expires */
            process.waitFor(timeout, TimeUnit.SECONDS);

            if(process.isAlive())
                throw new InterruptedException("Timeout expired");

            String output = fOutput.get();
            String error = fError.get();

            return parseOutput(output, error);

        } catch (IOException | InterruptedException | ExecutionException e) {
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

    public LTLToolTranslator getTranslator() {
        return translator;
    }

    public void setTranslator(LTLToolTranslator translator) {
        this.translator = translator;
    }

    public String getExecPath() {
        return execPath;
    }

    public void setExecPath(String execPath) {
        this.execPath = execPath;
    }
}
