package hu.codingmentor.exception;

public class VisitorNotInParkException extends RuntimeException{

    public static final String ERROR_CODE = "VISITOR_NOT_IN_PARK";
    
    public VisitorNotInParkException(String msg)  {
        super(msg);
    }

    public VisitorNotInParkException() {
        super();
    }    
}
