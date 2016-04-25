package hu.codingmentor.exception;

public class TooExpensiveMachineTicketException extends RuntimeException {
    public static final String ERROR_CODE = "TOO_EXPENSIVE_MACHINE_TICKET";
    
    public TooExpensiveMachineTicketException(String msg) {
        super(msg);
    }

    public TooExpensiveMachineTicketException() {
        super();
    }      
}
