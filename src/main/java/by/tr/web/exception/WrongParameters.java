package by.tr.web.exception;

public class WrongParameters extends RuntimeException {
    public WrongParameters() {
        super();
    }

    public WrongParameters(String message) {
        super(message);
    }

    public WrongParameters(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongParameters(Throwable cause) {
        super(cause);
    }
}
