package br.com.jornada.casadocodigo.annotation;

import br.com.jornada.casadocodigo.validation.ValorExisteValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValorExisteValidator.class)
public @interface ValorExiste {

    String message() default "{javax.validation.constraints.ExisteId.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> className();
    String fieldName();
}
