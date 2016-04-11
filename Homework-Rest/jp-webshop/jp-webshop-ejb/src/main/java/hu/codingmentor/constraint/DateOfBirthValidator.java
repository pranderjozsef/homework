package hu.codingmentor.constraint;

import hu.codingmentor.dto.UserDTO;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, UserDTO> {

    @Override
    public void initialize(DateOfBirthConstraint constraintAnnotation) {
        //Empty
    }

    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        return value.getDateOfBirth() == null ||
                (value.getDateOfBirth().isBefore(LocalDate.now()) &&
                value.getDateOfBirth().isBefore(value.getRegistrationDate())); //ebbe a sorban van a hiba
    }  
}
