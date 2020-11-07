package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.annotation.DocumentoValido;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 2
 */
// +1
public class DocumentoValidator implements ConstraintValidator<DocumentoValido, String> {
    private static Logger logger = LoggerFactory.getLogger(DocumentoValidator.class);

    @Override
    public void initialize(DocumentoValido constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        logger.info("Validando documento: {}", value);
        // +1
        if (StringUtils.isEmpty(value)){
            return true;
        }

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        String valueSemCaracterEspecial = value
                .replace(".", "")
                .replace("-", "")
                .replace("/", "");



        String documento = StringUtils.trimAllWhitespace(valueSemCaracterEspecial);


        return cpfValidator.isValid(documento,null) || cnpjValidator.isValid(documento,null);
    }
}
