package hu.codingmentor.exception;

public class VisitorOnMachineException extends RuntimeException {
    public static final String ERROR_CODE = "VISITOR_ON_MACHINE";
    
    public VisitorOnMachineException(String msg) {
        super(msg);
    }

    public VisitorOnMachineException() {
        super();
    }     
}
