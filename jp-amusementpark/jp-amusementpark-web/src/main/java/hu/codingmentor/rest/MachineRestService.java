/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.codingmentor.rest;

import hu.codingmentor.entity.Machine;
import hu.codingmentor.entity.Visitor;
import hu.codingmentor.service.MachineService;
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

@Path("/machines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MachineRestService {
    @Inject
    private MachineService machineService;    
    
    @POST
    public Machine createMachine(Machine machine){
        machineService.create(machine);
        return machine;
    }
    
    @Path("/{machineId}")
    @GET
    public Machine getMachine(@PathParam("machineId") Long machineId){
        return machineService.read(machineId);
    }    
    
    @Path("/{machineId}")
    @PUT
    public Machine updateAmusementPark(@PathParam("machineId") Long machineId, Machine machine){
        return machineService.update(machineId, machine);
    }
    
    @Path("/{machineId}")
    @DELETE
    public Machine deleteMachine(@PathParam("machineId") Long machineId){
        return machineService.delete(machineId);
    }

    @Path("/{machineId}/visitors/{visitorId}/geton")
    @PUT
    public Visitor getVisitorOnMachine(@PathParam("machineId") Long machineId, @PathParam("visitorId") Long visitorId){
        return machineService.getVisitorOnMachine(machineId, visitorId);
    }
    
    @Path("/{machineId}/visitors/{visitorId}/getoff")
    @PUT
    public Visitor getVisitorOffMachine(@PathParam("machineId") Long machineId, @PathParam("visitorId") Long visitorId){
        return machineService.getVisitorOffMachine(machineId, visitorId);
    }
}
