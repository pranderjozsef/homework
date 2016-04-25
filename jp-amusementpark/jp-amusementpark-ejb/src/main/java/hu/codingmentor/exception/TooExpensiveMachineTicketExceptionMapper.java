package hu.codingmentor.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TooExpensiveMachineTicketExceptionMapper implements ExceptionMapper<TooExpensiveMachineTicketException> {

    @Override
    public Response toResponse(TooExpensiveMachineTicketException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(TooExpensiveMachineTicketException.ERROR_CODE, exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
