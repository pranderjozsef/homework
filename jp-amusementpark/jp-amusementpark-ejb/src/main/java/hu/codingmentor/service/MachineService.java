package hu.codingmentor.service;

import hu.codingmentor.entity.AmusementPark;
import hu.codingmentor.entity.Machine;
import hu.codingmentor.entity.Visitor;
import hu.codingmentor.enums.State;
import hu.codingmentor.exception.MachineFullException;
import hu.codingmentor.exception.MachineNotExistException;
import hu.codingmentor.exception.NotInSameParkException;
import hu.codingmentor.exception.ParkNotHaveMachineException;
import hu.codingmentor.exception.TooExpensiveMachineTicketException;
import hu.codingmentor.exception.TooYoungVisitorException;
import hu.codingmentor.exception.VisitorNotExistException;
import hu.codingmentor.exception.VisitorNotInParkException;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MachineService {
    
    @Inject
    private EntityFacade entityService;
    
    @Inject
    private VisitorService visitorService;   
    
    public void create(Machine machine) {
        entityService.create(machine);
    }
    
    public Machine read(Long machineId) {
        return entityService.read(Machine.class, machineId);
    }
    
    public Machine update(Long machineId, Machine machine) {
        getAllVisitorsOfMachine(machineId);  
        machine.setMachineId(machineId);
        machine.setAmusementPark(read(machineId).getAmusementPark());
        
        for(AmusementPark a : entityService.findAll(AmusementPark.class)){
            if(machine.getAmusementPark()!= null && Objects.equals(a.getParkId(), machine.getAmusementPark().getParkId())){
                a.getMachines().remove(machine);
                a.getMachines().add(machine);
                entityService.update(a);
            }
        }
        
        return entityService.update(machine);
    }
    
    public Machine delete(Long machineId) {
        Machine machine = read(machineId);
        getAllVisitorsOfMachine(machineId);
        
        for(AmusementPark a : entityService.findAll(AmusementPark.class)){
            if(machine.getAmusementPark()!= null && Objects.equals(a.getParkId(), machine.getAmusementPark().getParkId())){
                a.getMachines().remove(machine);
                entityService.update(a);
            }
        }
        entityService.delete(machine);
        return machine;
    }
    
    public Visitor getVisitorOnMachine(Long machineId, Long visitorId){
        isMachineExist(machineId);
        isVisitorExist(visitorId);
      
        Machine machine = read(machineId);
        Visitor visitor = visitorService.read(visitorId);
        
        if(visitor.getAmusementPark() == null)
            throw new VisitorNotInParkException("This visitor cannot get on this machine because he/she isn't in the park.");
        
        if(machine.getAmusementPark() == null)
            throw new ParkNotHaveMachineException("This visitor cannot get on this machine because this park doesn't contain that.");

        if(!Objects.equals(visitor.getAmusementPark().getParkId(), machine.getAmusementPark().getParkId())){
            throw new NotInSameParkException("This visitor can't get on this machine because its not being in the park where the visitor is.");
        }
        
        isMachineTicketTooExpensive(visitor, machine);
        isMachineFull(machineId, machine);
        isVisitorTooYoung(visitor, machine);
        
        visitor.setSpendingMoney(visitor.getSpendingMoney()-machine.getTicketPrice());
        visitor.setState(State.ON_MACHINE);
        visitor.setMachine(machine);
        entityService.update(visitor);
        entityService.update(machine);
        return visitor;        
    }
    
    public Visitor getVisitorOffMachine(Long machineId, Long visitorId) {
        isMachineExist(machineId);
        isVisitorExist(visitorId);        
        
        Machine machine = read(machineId);
        Visitor visitor = visitorService.read(visitorId);
        
        if(visitor.getAmusementPark() == null)
            throw new VisitorNotInParkException("This visitor cannot get off this machine because he/she isn't in the park.");
        
        if(machine.getAmusementPark() == null)
            throw new ParkNotHaveMachineException("This visitor cannot get on this machine because this park doesn't contain that.");

        if(!Objects.equals(visitor.getAmusementPark().getParkId(), machine.getAmusementPark().getParkId())){
            throw new NotInSameParkException("This visitor can't get off this machine because its not being in the park where the visitor is.");
        }
        
        if(visitor.getMachine() == null || !Objects.equals(visitor.getMachine().getMachineId(), machineId)){
            throw new VisitorNotInParkException("This visitor can't get off this machine because he/she isn't on this machine.");   
        }
        
        visitor.setState(State.REST);
        visitor.setMachine(null);
        entityService.update(visitor);
        return visitor;
    }  
    
    public void getAllVisitorsOfMachine(Long machineId){
        for(Visitor v : entityService.findAll(Visitor.class)){
            if(v.getMachine() != null && Objects.equals(v.getMachine().getMachineId(), machineId)){
                v.setState(State.REST);
                v.setMachine(null);
                entityService.update(v);
            }
        }
    }
    
    public boolean isMachineExist(Long machineId){
        if(read(machineId) == null){
            throw new MachineNotExistException("There is no machine in the database with the given Id.");
        }
        return false;
    }
    
    public boolean isVisitorExist(Long visitorId){
        if(visitorService.read(visitorId) == null){
            throw new VisitorNotExistException("There is no visitor in the database with the given Id.");
        }
        return false;
    }
    
    public boolean isMachineTicketTooExpensive(Visitor visitor, Machine machine){
        if(visitor.getSpendingMoney() < machine.getTicketPrice()){
            throw new TooExpensiveMachineTicketException("This visitor doesn't have enough money to be able to buy a machine ticket.");
        }
        return false;
    }
    
    public boolean isMachineFull(Long machineId, Machine machine){
        int i = 0;
        for(Visitor v : entityService.findAll(Visitor.class)){
            if(v.getMachine() != null && Objects.equals(v.getMachine().getMachineId(), machineId)){
                i++;
            }
        }
        
        if(i >= machine.getCapacity()){
            throw new MachineFullException("This visitor can't get on this machine because its being full.");
        }
        return false;
    }
    
    public boolean isVisitorTooYoung(Visitor visitor, Machine machine){
        if(visitor.getAge() < machine.getAgeRequirement()){
            throw new TooYoungVisitorException("This visitor is too young for this machine.");
        }
        return false;
    }    
}
