package hu.codingmentor.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateOfBirthValidator.class)
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface DateOfBirthConstraint {

    String message() default "{DateOfBirthConstraint.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
