package hu.codingmentor.exception;

public class TooBigMachineException extends RuntimeException {
    
    public static final String ERROR_CODE = "TOO_BIG_MACHINE";
    
    public TooBigMachineException(String msg) {
        super(msg);
    }

    public TooBigMachineException() {
        super();
    }    
}
