package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Categoria;
import br.com.jornada.casadocodigo.domain.request.NovaCategoriaRequest;
import br.com.jornada.casadocodigo.validation.NomeCategoriaDuplicadoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;


class CategoriaResourceTest {

    private static NovaCategoriaRequest novaCategoriaRequest;

    @BeforeEach
    void setUpd(){
        novaCategoriaRequest = new NovaCategoriaRequest("Livros");
    }

    @Test
    @DisplayName("deve retornar status 201 ao criar uma nova categoria")
    void deveRetornarCreateParaNovaCategoria() {
        EntityManager manager = mock(EntityManager.class);
        CategoriaResource categoriaResource = new CategoriaResource(manager);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        Categoria categoria = novaCategoriaRequest.toCategoria();
        categoria.setId("asdasdad");

        doNothing().when(manager).persist(categoria);

        ResponseEntity<?> responseEntity = categoriaResource.criaCategoria(novaCategoriaRequest, uriComponentsBuilder);

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        //verify(manager,times(1)).persist(categoria);
    }
}