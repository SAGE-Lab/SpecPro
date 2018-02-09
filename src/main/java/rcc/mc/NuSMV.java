package rcc.mc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NuSMV extends ModelChecker {

    final String commandName = "/home/simone/Tools/NuSMV-2.6.0-Linux/bin/NuSMV";

    public NuSMV(long timeout) {
        super(timeout);
    }

    @Override
    protected String[] getCommand(String filePath) {
        return new String[]{commandName, filePath};
    }

    @Override
    protected Response parseOutput(String output, String error) {


        Pattern regex = Pattern.compile("-- specification .* is (?<res>(true|false))");

        Matcher matcher = regex.matcher(output);
        if(!matcher.find())
            return new Response("[ERROR] no response found\n"+error, Response.ResponseState.FAIL);

        String res = matcher.group("res");

        Response.ResponseState state;
        if(res.equals("false"))
            state = Response.ResponseState.CONSISTENT;
        else
            state = Response.ResponseState.INCONSISTENT;

        return new Response(output, state);
    }
}
