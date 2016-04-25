package hu.codingmentor.service;

import hu.codingmentor.entity.Guestbook;
import hu.codingmentor.entity.Visitor;
import hu.codingmentor.enums.State;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VisitorService {
    @Inject
    EntityFacade entityService;
    
    @Inject
    GuestbookService guestbookService;
    
    public void create(Visitor visitor) {
        visitor.setActive(Boolean.FALSE);
        visitor.setState(State.REST);
        entityService.create(visitor);
    }
    
    public Visitor read(Long visitorId) {
        return entityService.read(Visitor.class, visitorId);
    }
    
    public Visitor update(Long visitorId, Visitor visitor) {
        Visitor originalVisitor = read(visitorId);
        
        visitor.setVisitorId(visitorId);
        visitor.setAmusementPark(originalVisitor.getAmusementPark());
        visitor.setMachine(originalVisitor.getMachine());
        visitor.setState(originalVisitor.getState());
        visitor.setActive(originalVisitor.getActive());
        
        for(Guestbook g : entityService.findAll(Guestbook.class)){
            if(g.getVisitor() != null && Objects.equals(g.getVisitor().getVisitorId(), visitorId)){
                g.setVisitor(visitor);
                entityService.update(g);
            }
        }
        return entityService.update(visitor);
    }
    
    public Visitor delete(Long visitorId) {
        for(Guestbook g : entityService.findAll(Guestbook.class)){
            if(g.getVisitor() != null && Objects.equals(g.getVisitor().getVisitorId(), visitorId)){
                g.setVisitor(null);
                entityService.update(g);
            }
        }
        return entityService.delete(read(visitorId));
    }      
}
