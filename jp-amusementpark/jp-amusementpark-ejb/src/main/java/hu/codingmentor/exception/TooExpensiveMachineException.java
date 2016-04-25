package hu.codingmentor.exception;

public class TooExpensiveMachineException extends RuntimeException {
    
    public static final String ERROR_CODE = "TOO_EXPENSIVE_MACHINE";
    
    public TooExpensiveMachineException(String msg) {
        super(msg);
    }

    public TooExpensiveMachineException() {
        super();
    }     
}
