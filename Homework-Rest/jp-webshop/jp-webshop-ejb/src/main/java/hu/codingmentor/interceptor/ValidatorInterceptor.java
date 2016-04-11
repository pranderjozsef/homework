package hu.codingmentor.interceptor;

import hu.codingmentor.annotation.Validate;
import hu.codingmentor.exception.ValidationException;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Interceptor
@BeanValidation
public class ValidatorInterceptor implements Serializable{

    @Inject
    private transient Validator validator;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        validateParameters(ic.getParameters());
        return ic.proceed();
    }

    private void validateParameters(Object[] parameters) {
        for (Object o : parameters) {
            if (o.getClass().isAnnotationPresent(Validate.class)) {
                validateBean(o);
            }
        }
    }

    private void validateBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().map(e -> "Validation error: " + e.getMessage()  + " - property: " + e.getPropertyPath().toString() + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }
}
