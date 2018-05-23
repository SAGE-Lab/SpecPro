package rcc.mc;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public abstract class ModelChecker {

    private Thread thread;
    private final long timeout;

    public ModelChecker(long timeout) {
        this.timeout = timeout;
    }

    public Response run(String filePath) {
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        String[] command = getCommand(filePath);
        try {
            process = rt.exec(command);

            process.waitFor(timeout, TimeUnit.SECONDS);

            String output = IOUtils.toString(process.getInputStream());
            String errorMessage = IOUtils.toString(process.getErrorStream());

            return parseOutput(output, errorMessage);

        } catch (IOException | InterruptedException e) {
            if(process != null)
                process.destroy();
            return new Response(e.getMessage(), Response.ResponseState.FAIL);
        }
    }

    public void runAsync(String filePath, Consumer<Response> consumer) {
        thread = new Thread(() -> consumer.accept(run(filePath)));

        thread.start();
    }

    protected abstract String[] getCommand(String filePath);

    protected abstract Response parseOutput(String output, String error);
}
