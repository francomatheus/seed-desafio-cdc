package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.CupomDesconto;
import br.com.jornada.casadocodigo.domain.request.CupomDescontoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class CadastraCupomResourceTest {

    @Test
    @DisplayName("Deve criar com sucesso um cupom com desconto e retornar status 201")
    void deveCriarCupomComDesconto(){
        EntityManager manager = mock(EntityManager.class);
        CupomDescontoRequest cupomDescontoRequest = new CupomDescontoRequest("Cupom",20.0, LocalDate.of(2020,11,9));
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        CadastraCupomResource cadastraCupomResource = new CadastraCupomResource(manager);

        ResponseEntity<?> responseEntity = cadastraCupomResource.criaCupom(cupomDescontoRequest, uriComponentsBuilder);

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        verify(manager,times(1)).persist(any(CupomDesconto.class));
    }

}