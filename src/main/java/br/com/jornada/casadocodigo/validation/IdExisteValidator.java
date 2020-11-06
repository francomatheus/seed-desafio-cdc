package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.annotation.IdExiste;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IdExisteValidator implements ConstraintValidator<IdExiste, String> {

    private static Logger logger = LoggerFactory.getLogger(IdExisteValidator.class);

    private Class<?> className;

    @PersistenceContext
    private final EntityManager manager;

    public IdExisteValidator(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void initialize(IdExiste constraintAnnotation) {
        className = constraintAnnotation.className();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        logger.info("Validando se id existe: {}", value);

        List<?> objetoBuscadoPorId = manager.createQuery("select c from " + className.getName() + " c where c.id = :pValue")
                .setParameter("pValue", value)
                .getResultList();

        return objetoBuscadoPorId.size()>0;
    }
}
