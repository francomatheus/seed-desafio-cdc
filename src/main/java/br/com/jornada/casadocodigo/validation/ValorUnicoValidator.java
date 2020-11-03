package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.annotation.ValorUnico;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

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
        if (StringUtils.isEmpty(value)){
            return true;
        }

        List<?> valorBuscado = manager.createQuery("select c from " + className.getName() + " c where c." + fieldName + " =: pValue")
                .setParameter("pValue", value)
                .getResultList();

        return !(valorBuscado.size()>0);
    }
}
