package hu.codingmentor.rest;

import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.exception.BadRequestException;
import hu.codingmentor.interceptor.BeanValidation;
import hu.codingmentor.service.CartService;
import hu.codingmentor.service.InventoryService;
import java.util.Collection;
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

@Path("/cart")
@BeanValidation
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartRESTService{
    private static final String NOT_LOGED_IN = "You are not logged in."; 
    
    @Inject
    private CartService cartService;
    
    @Inject
    private InventoryService inventoryService;
    
    @GET
    public Collection<MobileDTO> getMobiles(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                return cartService.getMobiles();
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);
    }
    
    @POST
    public List <MobileDTO> addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                for(MobileDTO m : inventoryService.getMobileList()){
                    if(mobile.getType().equals(m.getType()) && mobile.getManufacturer().equals(m.getManufacturer())){
                        if(m.getPiece()-cartService.getNumberOfTheSameMobile(m.getType(), m.getManufacturer()) > 0)
                        {
                            cartService.addToCart(mobile);
                            return cartService.getMobiles();
                        }
                        throw new BadRequestException("This mobile is out of stock");
                    }
                }
                throw new BadRequestException("This shop doesn't sell this kind of mobile");
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);  
    }
    
    @POST
    @Path("/buy")
    public List <MobileDTO> buyMobile(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                for(MobileDTO mobileInCart : cartService.getMobiles()){
                    for(MobileDTO mobileInStock : inventoryService.getMobileList()){
                        if(mobileInCart.equals(mobileInStock)){
                            if(inventoryService.getNumberOfTheSameMobile(mobileInStock.getType(), mobileInStock.getManufacturer()) >
                                    cartService.getNumberOfTheSameMobile(mobileInCart.getType(), mobileInCart.getManufacturer())){
                                inventoryService.buyMobile();
                                cartService.checkout();
                                return cartService.getMobiles();  
                            }
                            else{
                                cartService.checkout();
                                throw new BadRequestException("Some of your chosen mobiles got sold. Your cart is empty now.");
                            }
                        }
                    }
                }
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);
    }
    
    @POST
    @Path("/checkout")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer checkout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session != null){
            session.setMaxInactiveInterval(600);
            Object userObject = session.getAttribute("user");
            if ((userObject != null) && (userObject instanceof UserDTO)) {
                cartService.checkout();
                return 1;
            }
        }
        throw new BadRequestException(NOT_LOGED_IN);
    }    
}

