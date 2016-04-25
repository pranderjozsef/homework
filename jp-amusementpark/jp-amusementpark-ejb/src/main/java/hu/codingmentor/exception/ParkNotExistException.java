package hu.codingmentor.exception;

public class ParkNotExistException extends RuntimeException {
    
    public static final String ERROR_CODE = "PARK_NOT_EXIST";
    
    public ParkNotExistException(String msg) {
        super(msg);
    }

    public ParkNotExistException() {
        super();
    }     
}
