package hu.codingmentor.rest;

import hu.codingmentor.entity.Address;
import hu.codingmentor.entity.Guestbook;
import hu.codingmentor.entity.Machine;
import hu.codingmentor.entity.Visitor;
import hu.codingmentor.service.QueryService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/queries")
@Produces(MediaType.APPLICATION_JSON)
public class QueryRESTService {
    
    @Inject
    QueryService queryService;
    
    @GET
    @Path("/machinesByParkId/{parkId}")
    public List<Machine> getMachinesByParkId(@PathParam("parkId") Long parkId) {
        return queryService.getMachinesByParkId(parkId);
    }
    
    @GET
    @Path("/visitorsByMachineId/{machineId}")
    public List<Visitor> getVisitorsByMachineId(@PathParam("machineId") Long machineId) {
        return queryService.getVisitorsByMachineId(machineId);
    }

    @GET
    @Path("/numberOfRestingVisitorByParkId/{parkId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getNumberOfRestingVisitorByParkId(@PathParam("parkId") Long parkId) {
        return queryService.getNumberOfRestingVisitorByParkId(parkId);
    }
    
    @GET
    @Path("/writingsByParkIdAndVisitorId/{parkId}/{visitorId}")
    public List<Guestbook> getWritingsByParkIdAndVisitorId(@PathParam("parkId") Long parkId, @PathParam("visitorId") Long visitorId) {
        return queryService.getWritingsByParkIdAndVisitorId(parkId, visitorId);
    }
    
    @GET
    @Path("/addressByParkId/{parkId}")
    public Address getAddressByParkId(@PathParam("parkId") Long parkId) {
        return queryService.getAddressByParkId(parkId);
    } 
}
