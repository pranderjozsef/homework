package hu.codingmentor.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class VisitorOnMachineExceptionMapper  implements ExceptionMapper<VisitorOnMachineException> {

    @Override
    public Response toResponse(VisitorOnMachineException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(VisitorOnMachineException.ERROR_CODE, exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
