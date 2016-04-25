package hu.codingmentor.exception;

public class MachineFullException extends RuntimeException {
    public static final String ERROR_CODE = "MACHINE_FULL";
    
    public MachineFullException(String msg) {
        super(msg);
    }

    public MachineFullException() {
        super();
    }    
}
