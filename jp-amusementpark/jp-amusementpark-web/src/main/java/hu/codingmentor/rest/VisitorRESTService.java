package hu.codingmentor.rest;

import hu.codingmentor.entity.Visitor;
import hu.codingmentor.service.VisitorService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/visitors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VisitorRESTService {
    @Inject
    private VisitorService visitorService;   
    
    @POST
    public Visitor createVisitor(Visitor visitor){
        visitorService.create(visitor);
        return visitor;
    }
    
    @Path("/{visitorId}")
    @GET
    public Visitor getVisitor(@PathParam("visitorId") Long visitorId){
        return visitorService.read(visitorId);
    }
    
    @Path("/{visitorId}")
    @PUT
    public Visitor updateVisitor(@PathParam("visitorId") Long visitorId, Visitor visitor){
        return visitorService.update(visitorId, visitor);
    } 
    
    @Path("/{visitorId}")
    @DELETE
    public Visitor deleteGueestbook(@PathParam("visitorId") Long visitorId){
        return visitorService.delete(visitorId);
    }  
}
