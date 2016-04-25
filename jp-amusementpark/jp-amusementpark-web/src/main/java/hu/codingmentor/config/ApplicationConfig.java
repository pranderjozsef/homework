/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.codingmentor.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Józsi
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(hu.codingmentor.exception.AlreadyOwnedMachineExceptionMapper.class);
        resources.add(hu.codingmentor.exception.MachineFullExceptionMapper.class);
        resources.add(hu.codingmentor.exception.MachineInUseExceptionMapper.class);
        resources.add(hu.codingmentor.exception.MachineNotExistExceptionMapper.class);
        resources.add(hu.codingmentor.exception.NotInSameParkExceptionMapper.class);
        resources.add(hu.codingmentor.exception.ParkNotExistExceptionMapper.class);
        resources.add(hu.codingmentor.exception.ParkNotHaveMachineExceptionMapper.class);
        resources.add(hu.codingmentor.exception.TooBigMachineExceptionMapper.class);
        resources.add(hu.codingmentor.exception.TooExpensiveMachineExceptionMapper.class);
        resources.add(hu.codingmentor.exception.TooExpensiveMachineTicketExceptionMapper.class);
        resources.add(hu.codingmentor.exception.TooExpensiveParkTicketExceptionMapper.class);
        resources.add(hu.codingmentor.exception.TooYoungVisitorExceptionMapper.class);
        resources.add(hu.codingmentor.exception.VisitorNotExistExceptionMapper.class);
        resources.add(hu.codingmentor.exception.VisitorNotInParkExceptionMapper.class);
        resources.add(hu.codingmentor.exception.VisitorOnMachineExceptionMapper.class);
        resources.add(hu.codingmentor.exception.WrongParkGuestbookExceptionMapper.class);
        resources.add(hu.codingmentor.rest.AddressRestService.class);
        resources.add(hu.codingmentor.rest.AmusementParkRESTService.class);
        resources.add(hu.codingmentor.rest.GuestbookRESTService.class);
        resources.add(hu.codingmentor.rest.MachineRestService.class);
        resources.add(hu.codingmentor.rest.QueryRESTService.class);
        resources.add(hu.codingmentor.rest.VisitorRESTService.class);
    }
    
}
