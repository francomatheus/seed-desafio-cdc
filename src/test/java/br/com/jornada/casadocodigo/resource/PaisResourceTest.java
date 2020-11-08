package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Pais;
import br.com.jornada.casadocodigo.domain.request.NovoPaisRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaisResourceTest {

    private EntityManager manager;
    private PaisResource paisResource;

    @BeforeEach
    void setup(){
        manager = mock(EntityManager.class);
        paisResource = new PaisResource(manager);
    }

    @Test
    @DisplayName("Deve criar pais com sucesso")
    void deverCriarPaisComSucesso(){
        NovoPaisRequest novoPaisRequest = new NovoPaisRequest("Brasil");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        ResponseEntity<?> responseEntity = paisResource.criaPais(novoPaisRequest, uriComponentsBuilder);

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        verify(manager, times(1)).persist(any(Pais.class));
    }

}