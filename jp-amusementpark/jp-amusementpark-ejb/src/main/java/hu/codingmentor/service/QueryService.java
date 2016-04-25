package hu.codingmentor.service;

import hu.codingmentor.entity.Address;
import hu.codingmentor.entity.Guestbook;
import hu.codingmentor.entity.Machine;
import hu.codingmentor.entity.Visitor;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

@Stateless
public class QueryService {
    
    @Inject
    EntityFacade entityService;
    
    private static final String PARK_ID = "parkId";
    
    public List<Machine> getMachinesByParkId(Long parkId) {
        TypedQuery<Machine> machines = entityService.getEm().createNamedQuery("findMachinesByParkId", Machine.class);
        machines.setParameter(PARK_ID, parkId);
        return machines.getResultList();
    }
    
    public List<Visitor> getVisitorsByMachineId(Long machineId) {
        TypedQuery<Visitor> visitors = entityService.getEm().createNamedQuery("findVisitorsByMachineId", Visitor.class);
        visitors.setParameter("machineId", machineId);
        return visitors.getResultList();
    }
    
    public Integer getNumberOfRestingVisitorByParkId(Long parkId) {
        TypedQuery<Integer> visitors = entityService.getEm().createNamedQuery("numberOfRestingVisitorByParkId", Integer.class);
        visitors.setParameter(PARK_ID, parkId);
        return ((Number)visitors.getSingleResult()).intValue();
    }
    
    public List<Guestbook> getWritingsByParkIdAndVisitorId(Long parkId, Long visitorId) {
        TypedQuery<Guestbook> guestbooks = entityService.getEm().createNamedQuery("findWritingsByParkIdAndVisitorId", Guestbook.class);
        guestbooks.setParameter(PARK_ID, parkId);
        guestbooks.setParameter("visitorId", visitorId);
        return guestbooks.getResultList();
    }     
    
    public Address getAddressByParkId(Long parkId) {
        TypedQuery<Address> address = entityService.getEm().createNamedQuery("findAddressByParkId", Address.class);
        address.setParameter(PARK_ID, parkId);
        return address.getSingleResult();
    }    
}
