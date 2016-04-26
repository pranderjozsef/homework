package hu.codingmentor.rest;

import hu.codingmentor.entity.Guestbook;
import hu.codingmentor.service.GuestbookService;
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

@Path("/guestbooks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuestbookRESTService {

    @Inject
    private GuestbookService guestbookService;
    
    @Path("visitors/{visitorId}/parks/{parkId}")
    @POST
    public Guestbook writeIntoGuestBook(@PathParam("visitorId") Long visitorId, @PathParam("parkId") Long parkId, Guestbook guestbook){  
        guestbookService.create(visitorId, parkId, guestbook);
        return guestbook;     
    }
    
    @Path("/{gueestbookId}")
    @GET
    public Guestbook getGueestbook(@PathParam("gueestbookId") Long gueestbookId){
        return guestbookService.read(gueestbookId);
    }
    
    @Path("/{gueestbookId}")
    @PUT
    public Guestbook updateGueestbook(@PathParam("gueestbookId") Long gueestbookId, Guestbook guestbook){
        return guestbookService.update(gueestbookId, guestbook);
    } 
    
    @Path("/{gueestbookId}")
    @DELETE
    public Guestbook deleteGueestbook(@PathParam("gueestbookId") Long gueestbookId){
        return guestbookService.delete(gueestbookId);
    }     
}
