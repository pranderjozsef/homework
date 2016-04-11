package hu.codingmentor.service;

import hu.codingmentor.dto.MobileDTO;
import java.io.Serializable;
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
public class InventoryService implements Serializable{
    private static final String SMART_PHONE = "Smart Phone"; 
    private static final List<MobileDTO> mobileList = new ArrayList<>();
    
    @Inject
    private CartService cartService;
    
    @PostConstruct
    public void init() {
        MobileDTO mobile = new MobileDTO(SMART_PHONE, "Sony", 200, 3);
        MobileDTO mobile2 = new MobileDTO(SMART_PHONE, "Samsung", 400, 12);
        MobileDTO mobile3 = new MobileDTO(SMART_PHONE, "Nokia", 100, 5);
        MobileDTO mobile4 = new MobileDTO(SMART_PHONE, "Motorola", 250, 0);
        
        mobileList.add(mobile);
        mobileList.add(mobile2);
        mobileList.add(mobile3);
        mobileList.add(mobile4);
    }
    
    public List<MobileDTO> getMobileList() {
        return mobileList;
    }
    
    public MobileDTO getMobile(MobileDTO mobile){
        return mobileList.get(mobileList.indexOf(mobile));
    }
    
    public void setMobileOnList(MobileDTO mobile){
        int index = -1;
        int i = 0;
        
        for(MobileDTO m : mobileList){
            if(m.equals(mobile)){
                index = i;
            }
            i++;
        }
        
        if(index >= 0){
            MobileDTO mobileFromTheList = mobileList.get(index);
            mobileFromTheList.setPiece(mobileFromTheList.getPiece() + mobile.getPiece());
            mobileList.set(index, mobileFromTheList);
        }
    }
    
    public MobileDTO addMobile(MobileDTO mobile) {
        mobileList.add(mobile);
        return mobile;
    }
    
    public void buyMobile() {
        for (MobileDTO mobileInCart : cartService.getMobiles()) {
            for(MobileDTO mobileInStock : mobileList){
                if(mobileInCart.equals(mobileInStock)){
                    int index = mobileList.indexOf(mobileInStock);
                    mobileInStock.setPiece(mobileInStock.getPiece()-1);
                    mobileList.set(index, mobileInStock);
                }
            }       
        }
    }
    
    public Integer getNumberOfTheSameMobile(String type, String manufacturer){
        for(MobileDTO m : mobileList){
            if(type.equals(m.getType()) && manufacturer.equals(m.getManufacturer())){
                return m.getPiece();
            }
        }
        return 0;
    }
}
