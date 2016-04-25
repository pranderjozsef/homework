package hu.codingmentor.service;

import hu.codingmentor.entity.Address;
import hu.codingmentor.entity.AmusementPark;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Provider;

@Stateless
public class AddressService {
    @Inject
    EntityFacade entityService;
    
    @Inject
    private Provider <AmusementParkService>  amusementParkService;
    
    public void create(Address address) {
        entityService.create(address);
    }
    
    public Address read(Long addressId) {
        return entityService.read(Address.class, addressId);
    }
    
    public Address update(Long addressId, Address address) {
        address.setAddressId(addressId);
        entityService.update(address);
        
        for(AmusementPark a: entityService.findAll(AmusementPark.class)){
            if(Objects.equals(a.getAddress().getAddressId(), addressId)){
                a.setAddress(read(addressId));
                amusementParkService.get().update(a.getParkId(), a);
            }
        }
        return address;
    }
    
    public Address delete(Long addressId) {
        for(AmusementPark a: entityService.findAll(AmusementPark.class)){
            if(Objects.equals(a.getAddress().getAddressId(), addressId)){
                amusementParkService.get().delete(a.getParkId());
            }
        }
        return entityService.delete(read(addressId));
    }
}
