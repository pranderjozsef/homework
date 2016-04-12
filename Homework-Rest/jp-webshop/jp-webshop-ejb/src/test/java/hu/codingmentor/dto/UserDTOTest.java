package hu.codingmentor.dto;

import hu.codingmentor.constraint.DateOfBirthConstraint;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDTOTest {
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
    public void testUsernameCorrect() {
        UserDTO user = new UserDTO("username", "Password0", "Miklós", "Berényi", 
                            LocalDate.of(1965, 07, 14), LocalDate.of(2012, 10, 20), true);
        
        user.setUsername("User");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    } 
    
    @Test
    public void testUsernameIncorrect() {
        UserDTO user = new UserDTO("username", "Password0", "Miklós", "Berényi", 
                            LocalDate.of(1965, 07, 14), LocalDate.of(2012, 10, 20), true);
        
        user.setUsername("U");
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(user, "username");
        Assert.assertEquals(1, violations.size());
    }
    
    @Test
    public void testPasswordCorrect() {
        UserDTO user = new UserDTO("username", "Password0", "Miklós", "Berényi", 
                            LocalDate.of(1965, 07, 14), LocalDate.of(2012, 10, 20), true);
        
        user.setPassword("Password2");
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    } 

    @Test
    public void testPasswordIncorrect() {
        UserDTO user = new UserDTO("username", "Password0", "Miklós", "Berényi", 
                            LocalDate.of(1965, 07, 14), LocalDate.of(2012, 10, 20), true);
        
        user.setPassword("Aa1");
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(user, "password");
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void testDateOfBirthCorrect() {
        UserDTO user = new UserDTO("username", "Password0", "Miklós", "Berényi", 
                            LocalDate.of(1965, 07, 14), LocalDate.of(2012, 10, 20), true);
        
        user.setDateOfBirth(LocalDate.of(1970, 10, 14));
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }
    
    @Test
    public void testDateOfBirthIncorrect() {
        UserDTO user = new UserDTO("username", "Password0", "Miklós", "Berényi", 
                            LocalDate.of(1965, 07, 14), LocalDate.of(2012, 10, 20), true);
        
        user.setDateOfBirth(LocalDate.of(2015, 07, 14));
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Iterator<ConstraintViolation<UserDTO>> iterator = violations.iterator();
        while (iterator.hasNext()) {
            Assert.assertEquals("The dateOfBirth isn't in the past or it's later then the registrationDate.", iterator.next().getMessage());
        }
    }
    
    @Test
    public void testRegistrationDateCorrect() {
        UserDTO user = new UserDTO("username", "Password0", "Miklós", "Berényi", 
                            LocalDate.of(1965, 07, 14), LocalDate.of(2012, 10, 20), true);
        
        user.setRegistrationDate(LocalDate.of(2014, 10, 20));
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }
    
    @Test
    public void testRegistrationDateIncorrect() {
        UserDTO user = new UserDTO("username", "Password0", "Miklós", "Berényi", 
                            null, null, true);
        
        user.setRegistrationDate(null);
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(user, "registrationDate");
        Assert.assertEquals(1, violations.size());
    }
}
