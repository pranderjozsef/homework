package hu.codingmentor.exception;

public class ParkNotHaveMachineException extends RuntimeException {
    public static final String ERROR_CODE = "PARK_NOT_HAVE_MACHINE";
    
    public ParkNotHaveMachineException(String msg) {
        super(msg);
    }

    public ParkNotHaveMachineException() {
        super();
    }    
}
