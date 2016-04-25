package hu.codingmentor.service;

import hu.codingmentor.entity.Address;
import hu.codingmentor.entity.AmusementPark;
import hu.codingmentor.entity.Guestbook;
import hu.codingmentor.entity.Machine;
import hu.codingmentor.entity.Visitor;
import hu.codingmentor.exception.AlreadyOwnedMachineException;
import hu.codingmentor.exception.MachineInUseException;
import hu.codingmentor.exception.ParkNotExistException;
import hu.codingmentor.exception.ParkNotHaveMachineException;
import hu.codingmentor.exception.TooBigMachineException;
import hu.codingmentor.exception.TooExpensiveMachineException;
import hu.codingmentor.exception.TooExpensiveParkTicketException;
import hu.codingmentor.exception.VisitorNotExistException;
import hu.codingmentor.exception.VisitorNotInParkException;
import hu.codingmentor.exception.VisitorOnMachineException;
import java.util.Objects;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AmusementParkService {
    
    @Inject
    EntityFacade entityService;
    
    @Inject
    AddressService addressService;
    
    @Inject
    MachineService machineService;
    
    @Inject
    VisitorService visitorService;
    
    @Inject
    GuestbookService guestbookService;
    
    private static final String PARK_NOT_EXIST = "There is no park in the database with the given Id.";
    
    public void create(AmusementPark amusementPark, Long addressId) {
        amusementPark.setAddress(addressService.read(addressId));
        amusementPark.setFreeArea(amusementPark.getTotalArea());
        entityService.create(amusementPark);
    }

    public AmusementPark read(Long parkId) {
        return entityService.read(AmusementPark.class, parkId);
    }
    
    public AmusementPark update(Long parkId, AmusementPark amusementPark) {
        Address address = read(parkId).getAddress();
        Set<Machine> machines = read(parkId).getMachines();
        Long freeArea = read(parkId).getFreeArea();
        
        amusementPark.setParkId(parkId);
        amusementPark.setAddress(address);
        amusementPark.setMachines(machines);
        
        amusementPark.setFreeArea(freeArea);
        entityService.update(amusementPark);
        
        for(Machine m : entityService.findAll(Machine.class)){
            if(m.getAmusementPark() != null && Objects.equals(m.getAmusementPark().getParkId(), parkId)){
                m.setAmusementPark(read(parkId));
                entityService.update(m);
            }
        }
        
        for(Visitor v : entityService.findAll(Visitor.class)){
            if(v.getAmusementPark() != null && Objects.equals(v.getAmusementPark().getParkId(), parkId)){
                v.setAmusementPark(read(parkId));
                entityService.update(v);
            }
        }
        
        for(Guestbook g : entityService.findAll(Guestbook.class)){
            if(g.getAmusementPark() != null && Objects.equals(g.getAmusementPark().getParkId(), parkId)){
                g.setAmusementPark(read(parkId));
                entityService.update(g);
            }
        }
        
        return amusementPark;       
    }
    
    public AmusementPark delete(Long parkId) {
        getAllMachinesOutOfThePark(parkId);
        getAllVisitorsOutOfThePark(parkId);
        for(Guestbook g : entityService.findAll(Guestbook.class)){
            if(g.getAmusementPark() != null && Objects.equals(g.getAmusementPark().getParkId(), parkId)){
                entityService.delete(g);
            }
        }
        return entityService.delete(read(parkId));
    }
    
    public AmusementPark updateAmusementParkAddress(Long parkId, Long addressId){
        AmusementPark amusementPark = read(parkId);
        amusementPark.setAddress(addressService.read(addressId));
        return amusementPark;
    }

    public Machine addMachineToPark(Long parkId, Long machineId) {
        isParkExist(parkId);
        machineService.isMachineExist(machineId);        
        
        AmusementPark amusementPark = read(parkId);
        Machine machine = machineService.read(machineId);
        
        if(machine.getAmusementPark() != null)
            throw new AlreadyOwnedMachineException("This machine is already owned by a park.");
        
        else if(amusementPark.getCapital() < machine.getPrice()){
            throw new TooExpensiveMachineException("This park doesn't have enough capital for this machine.");
        }
        
        else if(amusementPark.getFreeArea() < machine.getExtent())
            throw new TooBigMachineException("There is not enough space for this machine in this park.");
        
        
        machine.setAmusementPark(amusementPark);
        amusementPark.getMachines().add(machine);
        amusementPark.setCapital(amusementPark.getCapital()-machine.getPrice());
        entityService.update(machine);
        entityService.update(amusementPark);
        return machine;
    }
    
    public Machine removeMachineFromPark(Long parkId, Long machineId) {
        isParkExist(parkId);
        machineService.isMachineExist(machineId);
        
        AmusementPark amusementPark = read(parkId);
        Machine machine = machineService.read(machineId);
        
        if(!amusementPark.getMachines().contains(machine))
            throw new ParkNotHaveMachineException("This machine cannot be removed because this park doesn't contain that.");
        
        for(Visitor v : entityService.findAll(Visitor.class)){
            if(v.getMachine() != null && Objects.equals(v.getMachine().getMachineId(), machineId)){
                throw new MachineInUseException("This machine cannot be removed because its being used by visitor(s).");
            }
        }
        
        amusementPark.getMachines().remove(machine);
        amusementPark.setCapital(amusementPark.getCapital() + machine.getPrice());
        machine.setAmusementPark(null);
        entityService.update(machine);
        entityService.update(amusementPark);
        return machine;
    }
    
    public Visitor enterVisitorInPark(Long parkId, Long visitorId) {
        if(read(parkId) == null)
            throw new ParkNotExistException(PARK_NOT_EXIST);
        
        if(visitorService.read(visitorId) == null)
            throw new VisitorNotExistException("There is no visitor in the database with the given Id.");         
        
        AmusementPark amusementPark = read(parkId);
        Visitor visitor = visitorService.read(visitorId);
        
        if(visitor.getSpendingMoney() < amusementPark.getTicketPrice())
            throw new TooExpensiveParkTicketException("This visitor doesn't have enough money to be able to buy a park ticket.");
        
        visitor.setSpendingMoney(visitor.getSpendingMoney() - amusementPark.getTicketPrice());
        amusementPark.setCapital(amusementPark.getCapital() + amusementPark.getTicketPrice());
        visitor.setAmusementPark(amusementPark);
        visitor.setActive(Boolean.TRUE);
        entityService.update(visitor);
        entityService.update(amusementPark);
        return visitor;
    }
    
    public Visitor exitVisitorFromPark(Long parkId, Long visitorId) {
        if(read(parkId) == null)
            throw new ParkNotExistException(PARK_NOT_EXIST);
        
        if(visitorService.read(visitorId) == null)
            throw new VisitorNotExistException("There is no visitor in the database with the given Id.");         
        
        AmusementPark amusementPark = read(parkId);
        Visitor visitor = visitorService.read(visitorId);
        
        if(visitor.getAmusementPark() != null && !Objects.equals(visitor.getAmusementPark().getParkId(), amusementPark.getParkId()))
            throw new VisitorNotInParkException("This visitor cannot leave this park because he/she isn't in this park.");
        
        if(visitor.getMachine() != null)
            throw new VisitorOnMachineException("This visitor cannot leave this park because he/she is on a machine right now.");
        
        visitor.setAmusementPark(null);
        visitor.setActive(Boolean.FALSE);
        entityService.update(visitor);
        entityService.update(amusementPark);
        return visitor;
    }
    
    public void getAllMachinesOutOfThePark(Long parkId){
        AmusementPark amusementPark = read(parkId);
        Set<Machine> machines = amusementPark.getMachines();
        
        for(Machine m : machines){
            m.setAmusementPark(null);
            machineService.getAllVisitorsOfMachine(m.getMachineId());
        }
        
        amusementPark.getMachines().clear();
        entityService.update(amusementPark);
    }
    
    public void getAllVisitorsOutOfThePark(Long parkId){
        for(Visitor v : entityService.findAll(Visitor.class)){
            if(v.getAmusementPark() != null && Objects.equals(v.getAmusementPark().getParkId(), parkId)){
                v.setAmusementPark(null);
                entityService.update(v);
            }
        }
    }
    
    public boolean isParkExist(Long parkId){
        if(read(parkId) == null){
            throw new ParkNotExistException(PARK_NOT_EXIST);
        }
        return false;
    }
}    

