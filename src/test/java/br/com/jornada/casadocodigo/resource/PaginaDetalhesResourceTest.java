package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.model.Categoria;
import br.com.jornada.casadocodigo.domain.model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaginaDetalhesResourceTest {

    private EntityManager manager;
    private PaginaDetalhesResource paginaDetalhesResource;

    @BeforeEach
    void setup(){
        manager = mock(EntityManager.class);
        paginaDetalhesResource = new PaginaDetalhesResource(manager);
    }

    @Test
    @DisplayName("Deve retornar os dados de um livro a partir do id com sucesso")
    void deveRetornarPaginaDetalheComSucesso(){
        Categoria categoria = new Categoria();
        Autor autor = new Autor();
        Livro livro = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);

        when(manager.find(Livro.class, "1")).thenReturn(livro);

        ResponseEntity<?> responseEntity = paginaDetalhesResource.paginaDetalhe("1");

        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Não deve retornar os dados de um livro a partir de um id não encontrado")
    void naoDeveRetornarPaginaDetalheComSucesso(){
        when(manager.find(Livro.class, "1")).thenReturn(null);

        ResponseEntity<?> responseEntity = paginaDetalhesResource.paginaDetalhe("1");

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
    }

}