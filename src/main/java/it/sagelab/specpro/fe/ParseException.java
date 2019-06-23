package it.sagelab.specpro.fe;

import org.antlr.v4.runtime.misc.ParseCancellationException;

public class ParseException extends ParseCancellationException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable throwable) {
        super(throwable);
    }

}
