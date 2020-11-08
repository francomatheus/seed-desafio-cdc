package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.model.Categoria;
import br.com.jornada.casadocodigo.domain.model.Livro;
import br.com.jornada.casadocodigo.domain.request.NovoLivroRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

class LivroResourceTest {

    @Test
    @DisplayName("")
    void deveCriarLivroComSucessoComStatus_201(){
        EntityManager manager = mock(EntityManager.class);

        LivroResource livroResource = new LivroResource(manager);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        NovoLivroRequest novoLivroRequest = new NovoLivroRequest("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),"1","1");

        Categoria categoria = new Categoria("categoria");
        Autor autor = new Autor("jose@email.com","Jose","è um autor");
        Livro livro = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);

        when(manager.find(Categoria.class,novoLivroRequest.getCategoriaId())).thenReturn(categoria);
        when(manager.find(Autor.class,novoLivroRequest.getAutorId())).thenReturn(autor);
        //when(manager.persist(livro)).thenReturn(livro);

        ResponseEntity<?> responseEntity = livroResource.criaLivro(novoLivroRequest, uriComponentsBuilder);

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        verify(manager,times(1)).persist(any(Livro.class));
    }

    @Test
    @DisplayName("Deve retornar IllegalArgumentException com categoria nula")
    void deveRetornarErroComCategoriaNula(){
        EntityManager manager = mock(EntityManager.class);

        LivroResource livroResource = new LivroResource(manager);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        NovoLivroRequest novoLivroRequest = new NovoLivroRequest("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),"1","1");

        Categoria categoria = new Categoria("categoria");
        Autor autor = new Autor("jose@email.com","Jose","è um autor");
        Livro livro = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);

        when(manager.find(Categoria.class,novoLivroRequest.getCategoriaId())).thenReturn(null);
        when(manager.find(Autor.class,novoLivroRequest.getAutorId())).thenReturn(autor);

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> livroResource.criaLivro(novoLivroRequest, uriComponentsBuilder));

        Assertions.assertEquals("Id da categoria não encontrado", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Deve retornar IllegalArgumentException com autor nula")
    void deveRetornarErroComAutorNulo(){
        EntityManager manager = mock(EntityManager.class);

        LivroResource livroResource = new LivroResource(manager);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        NovoLivroRequest novoLivroRequest = new NovoLivroRequest("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),"1","1");

        Categoria categoria = new Categoria("categoria");
        Autor autor = new Autor("jose@email.com","Jose","è um autor");
        Livro livro = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);

        when(manager.find(Categoria.class,novoLivroRequest.getCategoriaId())).thenReturn(categoria);
        when(manager.find(Autor.class,novoLivroRequest.getAutorId())).thenReturn(null);

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> livroResource.criaLivro(novoLivroRequest, uriComponentsBuilder));

        Assertions.assertEquals("Id do autor não encontrado", illegalArgumentException.getMessage());
    }
}