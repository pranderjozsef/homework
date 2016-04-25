package hu.codingmentor.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WrongParkGuestbookExceptionMapper implements ExceptionMapper<WrongParkGuestbookException> {

    @Override
    public Response toResponse(WrongParkGuestbookException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(WrongParkGuestbookException.ERROR_CODE, exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
