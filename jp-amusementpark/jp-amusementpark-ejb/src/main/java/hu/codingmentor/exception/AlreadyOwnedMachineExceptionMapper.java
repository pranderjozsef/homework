package hu.codingmentor.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AlreadyOwnedMachineExceptionMapper implements ExceptionMapper<AlreadyOwnedMachineException> {
    
    @Override
    public Response toResponse(AlreadyOwnedMachineException exception) {
        return Response.status(Status.BAD_REQUEST).entity(new ErrorDTO(AlreadyOwnedMachineException.ERROR_CODE, exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
