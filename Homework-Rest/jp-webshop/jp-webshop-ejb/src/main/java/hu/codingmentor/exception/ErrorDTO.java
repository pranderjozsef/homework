package hu.codingmentor.exception;

public class ErrorDTO {

    private String errorMessage;

    public ErrorDTO() {
        //Empty
    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
