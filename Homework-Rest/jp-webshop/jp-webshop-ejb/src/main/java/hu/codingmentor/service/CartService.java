package hu.codingmentor.service;

import java.util.ArrayList;
import java.util.List;
import hu.codingmentor.dto.MobileDTO;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

@Stateful
@StatefulTimeout(value = 600, unit = TimeUnit.SECONDS)
public class CartService implements Serializable{
    
    private final List<MobileDTO> mobiles = new ArrayList<>();

    public List<MobileDTO> getMobiles() {
        return mobiles;
    }

    public MobileDTO addToCart(MobileDTO mobile) {
        mobiles.add(mobile);
        return mobile;
    }
    
    public Integer getNumberOfTheSameMobileInTheCart(MobileDTO mobile){
        int numberOfTheSameMobileInTheCart = 0;
        
        for(MobileDTO m : mobiles){
            if(m.equals(mobile)){
              numberOfTheSameMobileInTheCart++;
            }
        }
        return numberOfTheSameMobileInTheCart;
    }
    
    @Remove
    public void checkout() {
       mobiles.clear(); 
    }   
}
