package hu.codingmentor.exception;

public class NotInSameParkException extends RuntimeException {

    public static final String ERROR_CODE = "NOT_IN_SAME_PARK";
    
    public NotInSameParkException(String msg) {
        super(msg);
    }

    public NotInSameParkException() {
        super();
    }
}
