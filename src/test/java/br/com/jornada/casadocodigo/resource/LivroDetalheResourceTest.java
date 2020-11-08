package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Livro;
import br.com.jornada.casadocodigo.domain.response.LivroDetalheResponseDto;
import br.com.jornada.casadocodigo.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class LivroDetalheResourceTest {

    @Test
    @DisplayName("Deve retornar lista de livros com sucesso.")
    void deveCriarListaDeLivroDetalheComSucesso(){

        LivroRepository livroRepository = mock(LivroRepository.class);
        List<Livro> livrosDoBandoDeDados = List.of(new Livro(), new Livro(), new Livro());
        List<LivroDetalheResponseDto> listaDeLivros = List.of(new LivroDetalheResponseDto("1", "Spring"), new LivroDetalheResponseDto("2", "Spring 2"));

        //when(livroRepository.findAll()).thenReturn(livrosDoBandoDeDados);

        LivroDetalheResource livroDetalheResource = new LivroDetalheResource(livroRepository);

        ResponseEntity<List<LivroDetalheResponseDto>> listResponseEntity = livroDetalheResource.todosLivros();

        Assertions.assertEquals(HttpStatus.OK.value(), listResponseEntity.getStatusCodeValue());

    }

    @Test
    @DisplayName("Deve retornar status 200 e resposta com lista vazia quando não houver livros no banco de dados")
    void deveCriarLivroDetalheComSucesso(){
        List<LivroDetalheResponseDto> todosLivros = new ArrayList<>();
        LivroRepository livroRepository = mock(LivroRepository.class);
        List<Livro> livrosDoBandoDeDados = List.of(new Livro(), new Livro(), new Livro());

        when(livroRepository.findAll()).thenReturn(List.of());

        LivroDetalheResource livroDetalheResource = new LivroDetalheResource(livroRepository);

        ResponseEntity<List<LivroDetalheResponseDto>> listResponseEntity = livroDetalheResource.todosLivros();

        Assertions.assertEquals(HttpStatus.OK.value(), listResponseEntity.getStatusCodeValue());
        Assertions.assertTrue(listResponseEntity.getBody().size()==0);
    }


}