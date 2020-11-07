package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.annotation.ValorExiste;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 2
 */

// +1
public class ValorExisteValidator implements ConstraintValidator<ValorExiste, String> {

    private static Logger logger = LoggerFactory.getLogger(ValorExisteValidator.class);

    private Class<?> className;
    private String fieldName;

    @PersistenceContext
    private final EntityManager manager;

    public ValorExisteValidator(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void initialize(ValorExiste constraintAnnotation) {
        className = constraintAnnotation.className();
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        logger.info("Validando se id existe: {}", value);

        // +1
        List<?> objetoBuscadoPorId = manager.createQuery("select c from " + className.getName() + " c where c."+fieldName+" = :pValue")
                .setParameter("pValue", value)
                .getResultList();

        return objetoBuscadoPorId.size()>0;
    }
}
