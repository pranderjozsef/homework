package hu.codingmentor.exception;

public class MachineInUseException extends RuntimeException {
    
    public static final String ERROR_CODE = "MACHINE_IN_USE";
    
    public MachineInUseException(String msg) {
        super(msg);
    }

    public MachineInUseException() {
        super();
    }    
}
