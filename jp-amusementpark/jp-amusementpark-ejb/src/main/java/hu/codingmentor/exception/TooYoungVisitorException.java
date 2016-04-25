package hu.codingmentor.exception;

public class TooYoungVisitorException extends RuntimeException {
    public static final String ERROR_CODE = "TOO_YOUNG_VISITOR";
    
    public TooYoungVisitorException(String msg) {
        super(msg);
    }

    public TooYoungVisitorException() {
        super();
    }     
}
