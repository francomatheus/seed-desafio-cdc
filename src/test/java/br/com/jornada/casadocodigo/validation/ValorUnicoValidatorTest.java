package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.domain.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValorUnicoValidatorTest {



    @Test
    void naoDeveValidarValor() {
        Class<?> className = Categoria.class;
        final String fieldName = "nome";
        final String value = "livro";
        EntityManager manager = mock(EntityManager.class);


//        when(manager.createQuery("select c from "+className.getName()+ " c where c."+fieldName+ " =: pValue")
//                .setParameter("pValue", value)
//                .getResultList()).thenReturn(List.of(new Categoria("livro")));

        ValorUnicoValidator valorUnicoValidator = new ValorUnicoValidator();

        boolean valorValido = valorUnicoValidator.isValid(value, null);

        Assertions.assertFalse(valorValido);

        fail("Entity Manager dando null");

    }
}