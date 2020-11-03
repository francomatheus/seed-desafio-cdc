package br.com.jornada.casadocodigo.annotation;

import br.com.jornada.casadocodigo.validation.ValorUnicoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValorUnicoValidator.class)
@Documented
public @interface ValorUnico {

    String message() default "{javax.validation.constraints.ValorUnico.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> className();
    String fieldName();
}
