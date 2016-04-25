package hu.codingmentor.exception;

public class WrongParkGuestbookException extends RuntimeException  {
    public static final String ERROR_CODE = "WRONG_PARK_GUESTBOOK";
    
    public WrongParkGuestbookException(String msg) {
        super(msg);
    }

    public WrongParkGuestbookException() {
        super();
    }     
}
