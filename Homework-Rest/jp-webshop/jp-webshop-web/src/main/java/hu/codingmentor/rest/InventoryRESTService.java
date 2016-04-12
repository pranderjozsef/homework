package hu.codingmentor.rest;

import hu.codingmentor.check.LoginCheck;
import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.interceptor.BeanValidation;
import hu.codingmentor.service.InventoryService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/inventory")
@BeanValidation
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SessionScoped
public class InventoryRESTService implements Serializable{
    
    @Inject
    private InventoryService inventoryService;
    
    @GET
    public List<MobileDTO> getMobilesList(@Context HttpServletRequest request) {
        LoginCheck.isLogedIn(request);
        return inventoryService.getMobileList();
    }

    @POST
    public List<MobileDTO> addMobile(@Context HttpServletRequest request, MobileDTO mobile) {
        LoginCheck.isLogedInAsAdmin(request);
        boolean isMobileOnTheList = false;
        for(MobileDTO m : inventoryService.getMobileList()){
            if(mobile.equals(m)){
               isMobileOnTheList = true;
            }
        }
        if(isMobileOnTheList){
            inventoryService.setMobileOnList(mobile);
            return inventoryService.getMobileList();
        }
        else{
            inventoryService.addMobile(mobile);
            return inventoryService.getMobileList();
        }    
    }
}
