package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.annotation.ValorUnico;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

// +1
public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, String> {

    private Class className;
    private String fieldName;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        className = constraintAnnotation.className();
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // +1
        if (StringUtils.isEmpty(value)){
            return true;
        }
        // +1
        List<?> valorBuscado = manager.createQuery("select c from " + className.getName() + " c where c." + fieldName + " =: pValue")
                .setParameter("pValue", value)
                .getResultList();

        return !(valorBuscado.size()>0);
    }
}
