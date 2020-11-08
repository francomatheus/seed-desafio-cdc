package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Estado;
import br.com.jornada.casadocodigo.domain.model.Pais;
import br.com.jornada.casadocodigo.domain.request.NovoEstadoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;

class EstadoResourceTest {

    private static NovoEstadoRequest novoEstadoRequest;

    private EntityManager manager;
    private EstadoResource estadoResource;

    @BeforeEach
    void setup(){
        novoEstadoRequest = new NovoEstadoRequest("MG", "1");
        manager = mock(EntityManager.class);
        estadoResource = new EstadoResource(manager);
    }

    @Test
    @DisplayName("Deve criar estado com sucesso e retornar status 201")
    void deveCriarEstadoComSucesso(){
        Pais pais = new Pais("Brasil");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        when(manager.find(Pais.class, "1")).thenReturn(pais);

        ResponseEntity<?> responseEntity = estadoResource.criaEstado(novoEstadoRequest, uriComponentsBuilder);

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        verify(manager, times(1)).persist(any(Estado.class));
    }

}