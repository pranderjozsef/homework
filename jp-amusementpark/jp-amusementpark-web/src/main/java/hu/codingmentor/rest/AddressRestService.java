package hu.codingmentor.rest;

import hu.codingmentor.entity.Address;
import hu.codingmentor.service.AddressService;
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


@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressRestService {
    @Inject
    private AddressService addressService;
    
    @POST
    public Address createAddress(Address address){
        addressService.create(address);
        return address;
    }
    
    @Path("/{addressId}")
    @GET
    public Address getAddress(@PathParam("addressId") Long addressId){
        return addressService.read(addressId);
    }
    
    @Path("/{addressId}")
    @PUT
    public Address updateAddress(@PathParam("addressId") Long addressId, Address address){
        return addressService.update(addressId, address);
    } 
    
    @Path("/{addressId}")
    @DELETE
    public Address deleteAddress(@PathParam("addressId") Long addressId){
        return addressService.delete(addressId);
    }  
}
