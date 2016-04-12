package hu.codingmentor.rest;

import hu.codingmentor.check.LoginCheck;
import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.exception.BadRequestException;
import hu.codingmentor.interceptor.BeanValidation;
import hu.codingmentor.service.CartService;
import hu.codingmentor.service.InventoryService;
import java.io.Serializable;
import java.util.Collection;
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

@Path("/cart")
@BeanValidation
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SessionScoped
public class CartRESTService implements Serializable{
    
    @Inject
    private CartService cartService;
    
    @Inject
    private InventoryService inventoryService;
    
    @GET
    public Collection<MobileDTO> getMobiles(@Context HttpServletRequest request) {
        LoginCheck.isLogedIn(request);
        return cartService.getMobiles();
    }
    
    @POST
    public List <MobileDTO> addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        LoginCheck.isLogedIn(request);
        if(inventoryService.getMobileList().contains(mobile)){
            if(mobile.getPiece()-cartService.getNumberOfTheSameMobileInTheCart(mobile) > 0){
                cartService.addToCart(mobile);
                return cartService.getMobiles();                
            }
            throw new BadRequestException("This mobile is out of stock.");
        }
        throw new BadRequestException("This shop doesn't sell this kind of mobile.");
    }
    
    @POST
    @Path("/buy")
    public List <MobileDTO> buyMobile(@Context HttpServletRequest request) {
        LoginCheck.isLogedIn(request);
        for(MobileDTO mobileInCart : cartService.getMobiles()){
            if(inventoryService.getMobile(mobileInCart).getPiece() >
                    cartService.getNumberOfTheSameMobileInTheCart(mobileInCart)){
                inventoryService.buyMobile();
                cartService.checkout();
                return cartService.getMobiles();  
            } else {
                cartService.checkout();
                throw new BadRequestException("Some of your chosen mobiles got sold. Your cart is empty now.");
            }
        }
        throw new BadRequestException("Your cart is empty.");
    }
}

