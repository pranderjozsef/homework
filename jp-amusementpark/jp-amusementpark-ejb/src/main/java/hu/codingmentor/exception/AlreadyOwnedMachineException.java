package hu.codingmentor.exception;

public class AlreadyOwnedMachineException extends RuntimeException {

    public static final String ERROR_CODE = "ALREADY_OWNED_MACHINE";
    
    public AlreadyOwnedMachineException(String msg) {
        super(msg);
    }

    public AlreadyOwnedMachineException() {
        super();
    }
}
