package snl2fl;

public class Snl2FlException extends RuntimeException {

    public Snl2FlException(final String message) {
        super(message);
    }


    public Snl2FlException(final String message, final Throwable cause) {
        super(message, cause);
    }


    public Snl2FlException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }

}
