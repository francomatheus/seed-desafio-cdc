package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.request.NovoAutorRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class NovoAutorResourceTest2 {

    private ObjectMapper objectMapper;

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeEach
    void setup(){
        objectMapper = new ObjectMapper();
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void deveSalvarAutorSeForValido() throws Exception {

        NovoAutorRequest novoAutorRequest = new NovoAutorRequest("Teste@gmail.com","Admin", "Texto qualquer");
        EntityManager mock = mock(EntityManager.class);
        NovoAutorResource novoAutorResource = new NovoAutorResource(mock);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        ResponseEntity<?> responseEntity = novoAutorResource.criaAutor(novoAutorRequest, uriComponentsBuilder);
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        //Mockito.verify(mock,Mockito.times(1)).persist(novoAutorRequest.toAutor());
    }



}
