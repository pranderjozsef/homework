package hu.codingmentor.exception;

public class VisitorNotExistException extends RuntimeException {
    
    public static final String ERROR_CODE = "VISITOR_NOT_EXIST";
    
    public VisitorNotExistException(String msg) {
        super(msg);
    }

    public VisitorNotExistException() {
        super();
    }    
}
