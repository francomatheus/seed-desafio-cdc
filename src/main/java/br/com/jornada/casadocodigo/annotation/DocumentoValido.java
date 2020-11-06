package br.com.jornada.casadocodigo.annotation;

import br.com.jornada.casadocodigo.validation.DocumentoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DocumentoValidator.class)
public @interface DocumentoValido {

    String message() default "{javax.validation.constraints.DocumentoValido.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
