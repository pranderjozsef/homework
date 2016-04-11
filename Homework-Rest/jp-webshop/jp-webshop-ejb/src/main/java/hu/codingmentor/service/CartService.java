package hu.codingmentor.service;

import java.util.ArrayList;
import java.util.List;
import hu.codingmentor.dto.MobileDTO;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

@Stateful
@StatefulTimeout(value = 600, unit = TimeUnit.SECONDS)
public class CartService{
    
    @Resource
    SessionContext context;
 
    private final List<MobileDTO> mobiles = new ArrayList<>();

    public List<MobileDTO> getMobiles() {
        return mobiles;
    }

    public MobileDTO addToCart(MobileDTO mobile) {
        mobiles.add(mobile);
        return mobile;
    }
    
    public Integer getNumberOfTheSameMobile(String type, String manufacturer){
        int numberOfMobilesOfTheSameMobile = 0;
        
        for(MobileDTO m : mobiles){
            if(type.equals(m.getType()) && manufacturer.equals(m.getManufacturer())){
              numberOfMobilesOfTheSameMobile++;
            }
        }
        return numberOfMobilesOfTheSameMobile;
    }
    
    @Remove
    public void checkout() {
        mobiles.clear();
    }   
}
