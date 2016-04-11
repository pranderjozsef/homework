package hu.codingmentor.rest;

import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.exception.BadRequestException;
import hu.codingmentor.interceptor.BeanValidation;
import hu.codingmentor.service.InventoryService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class InventoryRESTService{
    
    @Inject
    private InventoryService inventoryService;
    
    @GET
    public List<MobileDTO> getMobilesList(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {        
                return inventoryService.getMobileList();
            }
        }
        throw new BadRequestException("You are not logged in.");
    }

    @POST
    public List<MobileDTO> addMobile(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                UserDTO currentUser = (UserDTO) userObject;
                if (currentUser.isAdmin()) {
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
                throw new BadRequestException("Only admins can add mobiles");
            }
        }
        throw new BadRequestException("You are not logged in.");    
    }
}
