package hu.codingmentor.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class VisitorNotInParkExceptionMapper implements ExceptionMapper<VisitorNotInParkException> {

    @Override
    public Response toResponse(VisitorNotInParkException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(VisitorNotInParkException.ERROR_CODE, exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
