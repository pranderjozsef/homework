package hu.codingmentor.exception;

public class MachineNotExistException extends RuntimeException {
    
    public static final String ERROR_CODE = "MACHINE_NOT_EXIST";
    
    public MachineNotExistException(String msg) {
        super(msg);
    }

    public MachineNotExistException() {
        super();
    }    
}
