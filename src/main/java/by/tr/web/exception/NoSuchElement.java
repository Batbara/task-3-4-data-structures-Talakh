package by.tr.web.exception;

public class NoSuchElement extends RuntimeException {
    public NoSuchElement() {
        super();
    }

    public NoSuchElement(String message) {
        super(message);
    }

    public NoSuchElement(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElement(Throwable cause) {
        super(cause);
    }
}
