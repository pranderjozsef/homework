package hu.codingmentor.service;

import hu.codingmentor.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
@LocalBean
public class InventoryService {
    private static final String SMART_PHONE = "Smart Phone"; 
    private final List<MobileDTO> mobilList = new ArrayList<>();
    
    @Inject
    private CartService cartService;
    
    @PostConstruct
    public void init() {
        MobileDTO mobile = new MobileDTO(SMART_PHONE, "Sony", 200, 3);
        MobileDTO mobile2 = new MobileDTO(SMART_PHONE, "Samsung", 400, 12);
        MobileDTO mobile3 = new MobileDTO(SMART_PHONE, "Nokia", 100, 5);
        MobileDTO mobile4 = new MobileDTO(SMART_PHONE, "Motorola", 250, 0);
        
        mobilList.add(mobile);
        mobilList.add(mobile2);
        mobilList.add(mobile3);
        mobilList.add(mobile4);
    }
    
    public List<MobileDTO> getMobileList() {
        return mobilList;
    }
    
    public MobileDTO getMobile(MobileDTO mobile){
        return mobilList.get(mobilList.indexOf(mobile));
    }
    
    public void setMobileOnList(MobileDTO mobile){
        int index = -1;
        int i = 0;
        
        for(MobileDTO m : mobilList){
            if(m.equals(mobile)){
                index = i;
            }
            i++;
        }
        
        if(index >= 0){
            MobileDTO mobileFromTheList = mobilList.get(index);
            mobileFromTheList.setPiece(mobileFromTheList.getPiece() + mobile.getPiece());
            mobilList.set(index, mobileFromTheList);
        }
    }
    
    public MobileDTO addMobile(MobileDTO mobile) {
        mobilList.add(mobile);
        return mobile;
    }
    
    public void buyMobile() {
        for (MobileDTO mobileInCart : cartService.getMobiles()) {
            for(MobileDTO mobileInStock : mobilList){
                if(mobileInCart.equals(mobileInStock)){
                    int index = mobilList.indexOf(mobileInStock);
                    mobileInStock.setPiece(mobileInStock.getPiece()-1);
                    mobilList.set(index, mobileInStock);
                }
            }       
        }
    }
    
    public Integer getNumberOfTheSameMobile(String type, String manufacturer){
        for(MobileDTO m : mobilList){
            if(type.equals(m.getType()) && manufacturer.equals(m.getManufacturer())){
                return m.getPiece();
            }
        }
        return 0;
    }
}
