package hu.codingmentor.dto;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MobileDTOTest {
    private static ValidatorFactory vf;
    private static Validator validator;  

    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }
    
    @AfterClass
    public static void tearDownClass() {
        vf.close();
    }
    
    @Test
    public void testTypeCorrect() {
        MobileDTO mobile = new MobileDTO("Smart Phones", "Samsung", 10000, 1);
        
        mobile.setType("Smart Phones");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    } 
    
    @Test
    public void testTypeIncorrect() {
        MobileDTO mobile = new MobileDTO("Smart Phones", "Samsung", 10000, 1);
        
        mobile.setType("S");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
    }
    
    @Test
    public void testManufacturerCorrect() {
        MobileDTO mobile = new MobileDTO("Smart Phones", "Samsung", 10000, 1);
        
        mobile.setManufacturer("Smart Phones");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    } 
    
    @Test
    public void testManufacturerIncorrect() {
        MobileDTO mobile = new MobileDTO("Smart Phones", "Samsung", 10000, 1);
        
        mobile.setManufacturer("S");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
    }
    
    @Test
    public void testPriceCorrect() {
        MobileDTO mobile = new MobileDTO("Smart Phones", "Samsung", 10000, 1);
        
        mobile.setPrice(1);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }    
    
    @Test
    public void testPriceIncorrect() {
        MobileDTO mobile = new MobileDTO("Smart Phones", "Samsung", 10000, 1);
        
        mobile.setPrice(0);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void testPieceCorrect() {
        MobileDTO mobile = new MobileDTO("Smart Phones", "Samsung", 10000, 1);
        
        mobile.setPiece(0);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }    
    
    @Test
    public void testPieceIncorrect() {
        MobileDTO mobile = new MobileDTO("Smart Phones", "Samsung", 10000, 1);
        
        mobile.setPiece(-1);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
    }    
}
