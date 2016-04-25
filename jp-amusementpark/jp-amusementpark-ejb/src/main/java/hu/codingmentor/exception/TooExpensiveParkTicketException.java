package hu.codingmentor.exception;

public class TooExpensiveParkTicketException extends RuntimeException {
    
    public static final String ERROR_CODE = "TOO_EXPENSIVE_PARK_TICKET";
    
    public TooExpensiveParkTicketException(String msg) {
        super(msg);
    }

    public TooExpensiveParkTicketException() {
        super();
    }     
}
