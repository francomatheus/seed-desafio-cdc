package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.response.LivroDetalheResponseDto;
import br.com.jornada.casadocodigo.repository.LivroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/livros")
public class LivroDetalheResource {

    private static Logger logger = LoggerFactory.getLogger(LivroDetalheResource.class);
    // +1
    private final LivroRepository livroRepository;

    public LivroDetalheResource(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public ResponseEntity<List<LivroDetalheResponseDto>> todosLivros(){
        // +1
        List<LivroDetalheResponseDto> todosLivros = new ArrayList<>();

        livroRepository.findAll()
                // +1
                .forEach(livro -> {
                    todosLivros.add(new LivroDetalheResponseDto(livro.getId(), livro.getTitulo()));
                });
        logger.info("Todos os livros disponiveis: {}", todosLivros);
        return ResponseEntity.ok(todosLivros);
    }
}
