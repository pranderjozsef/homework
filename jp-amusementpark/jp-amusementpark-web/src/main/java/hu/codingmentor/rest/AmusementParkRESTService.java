package hu.codingmentor.rest;

import hu.codingmentor.entity.AmusementPark;
import hu.codingmentor.entity.Machine;
import hu.codingmentor.entity.Visitor;
import hu.codingmentor.service.AmusementParkService;
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

@Path("/parks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AmusementParkRESTService {
    @Inject
    private AmusementParkService amusementParkService;
    
    @Path("/address/{addressId}")
    @POST
    public AmusementPark createAmusementPark(AmusementPark amusementPark, @PathParam("addressId") Long addressId){
        amusementParkService.create(amusementPark, addressId);
        return amusementPark;
    }
    
    @Path("/{parkId}")
    @GET
    public AmusementPark getAmusementPark(@PathParam("parkId") Long parkId){
        return amusementParkService.read(parkId);
    }
    
    @Path("/{parkId}")
    @PUT
    public AmusementPark updateAmusementPark(@PathParam("parkId") Long parkId, AmusementPark amusementPark){
        return amusementParkService.update(parkId, amusementPark);
    }
    
    @Path("/{parkId}")
    @DELETE
    public AmusementPark deleteAmusementPark(@PathParam("parkId") Long parkId){
        return amusementParkService.delete(parkId);
    }
    
    @Path("/{parkId}/address/{addressId}")
    @PUT
    public AmusementPark updateAmusementParkAddress(@PathParam("parkId") Long parkId, @PathParam("addressId") Long addressId){
        return amusementParkService.updateAmusementParkAddress(parkId, addressId);
    }
    
    @Path("/{parkId}/machines/{machineId}/add")
    @PUT
    public Machine addMachineToPark(@PathParam("parkId") Long parkId, @PathParam("machineId") Long machineId){
        return amusementParkService.addMachineToPark(parkId, machineId);
    }
    
    @Path("/{parkId}/machines/{machineId}/remove")
    @PUT
    public Machine removeMachineFromPark(@PathParam("parkId") Long parkId, @PathParam("machineId") Long machineId){
        return amusementParkService.removeMachineFromPark(parkId, machineId);
    }
    
    @Path("/{parkId}/visitors/{visitorId}/enter")
    @PUT
    public Visitor enterVisitorToPark(@PathParam("parkId") Long parkId, @PathParam("visitorId") Long visitorId){
        return amusementParkService.enterVisitorInPark(parkId, visitorId);
    }
    
    @Path("/{parkId}/visitors/{visitorId}/exit")
    @PUT
    public Visitor exitVisitorFromPark(@PathParam("parkId") Long parkId, @PathParam("visitorId") Long visitorId){
        return amusementParkService.exitVisitorFromPark(parkId, visitorId);
    }    
}
