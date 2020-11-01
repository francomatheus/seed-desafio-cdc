package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.resource.NovoAutorResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.mockito.Mockito.mock;

class NovoAutorRequestTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeEach
    void setup(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void deverRetornar400SeEmailForInvalido() throws Exception {

        NovoAutorRequest novoAutorRequest = new NovoAutorRequest("Teste","Admin", "Texto qualquer");
        EntityManager mock = mock(EntityManager.class);

        Set<ConstraintViolation<NovoAutorRequest>> validate = validator.validate(novoAutorRequest);

        Assertions.assertTrue(validate.size()==1);
    }

}