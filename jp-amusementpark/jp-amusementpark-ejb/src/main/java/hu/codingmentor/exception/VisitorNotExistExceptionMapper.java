package hu.codingmentor.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class VisitorNotExistExceptionMapper implements ExceptionMapper<VisitorNotExistException>  {

    @Override
    public Response toResponse(VisitorNotExistException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(VisitorNotExistException.ERROR_CODE, exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
