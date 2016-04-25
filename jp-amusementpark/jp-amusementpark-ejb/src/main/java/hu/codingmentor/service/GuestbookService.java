package hu.codingmentor.service;

import hu.codingmentor.entity.AmusementPark;
import hu.codingmentor.entity.Guestbook;
import hu.codingmentor.entity.Visitor;
import hu.codingmentor.exception.ParkNotExistException;
import hu.codingmentor.exception.VisitorNotExistException;
import hu.codingmentor.exception.VisitorNotInParkException;
import hu.codingmentor.exception.WrongParkGuestbookException;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Provider;

@Stateless
public class GuestbookService {
    @Inject
    EntityFacade entityService;
    
    @Inject
    private Provider <AmusementParkService> amusementParkService;
    
    @Inject
    private Provider <VisitorService> visitorService;
    
    public void create(Long visitorId, Long parkId, Guestbook guestbook){  
        if(visitorService.get().read(visitorId) == null)
            throw new VisitorNotExistException("There is no visitor in the database with the given Id.");
        
        if(amusementParkService.get().read(parkId) == null)
            throw new ParkNotExistException("There is no park in the database with the given Id.");
        
        Visitor visitor = visitorService.get().read(visitorId); 
        
        if(visitor.getAmusementPark() == null)
            throw new VisitorNotInParkException("This visitor cannot write into the guestbook of this park because he/she isn't in the park.");
        
        if(!Objects.equals(parkId, visitor.getAmusementPark().getParkId())){
            throw new WrongParkGuestbookException("This visitor cannot write into the guestbook of this park because he/she is in another park.");
        }
        
        AmusementPark amusementPark = amusementParkService.get().read(parkId);
        
        guestbook.setAmusementPark(amusementPark);
        guestbook.setVisitor(visitor);
        
        entityService.create(guestbook);       
    }
    
    public Guestbook read(Long guestbookId) {
        return entityService.read(Guestbook.class, guestbookId);
    }
    
    public Guestbook update(Long guestbookId, Guestbook guestbook) {  
        guestbook.setGuestbookId(guestbookId);
        guestbook.setAmusementPark(read(guestbookId).getAmusementPark());
        guestbook.setVisitor(read(guestbookId).getVisitor());
        
        return entityService.update(guestbook);
    }
    
    public Guestbook delete(Long guestbookId) {
        Guestbook guestbook = read(guestbookId);
        entityService.delete(guestbook);
        return guestbook;
    }
}
