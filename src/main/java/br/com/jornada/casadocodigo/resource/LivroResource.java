package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.model.Categoria;
import br.com.jornada.casadocodigo.domain.model.Livro;
import br.com.jornada.casadocodigo.domain.request.NovoLivroRequest;
import br.com.jornada.casadocodigo.domain.response.NovoLivroResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 7
 */

@RestController
@RequestMapping("/v1/livros")
public class LivroResource {

    private static Logger logger = LoggerFactory.getLogger(LivroResource.class);

    @PersistenceContext
    private final EntityManager manager;

    public LivroResource(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaLivro(@RequestBody @Valid NovoLivroRequest novoLivroRequest,
                                       UriComponentsBuilder uriComponentsBuilder){

        logger.info("Requisição para criar livro recebida: {}", novoLivroRequest);
        // +1
        Categoria categoria = manager.find(Categoria.class, novoLivroRequest.getCategoriaId());
        // +1
        Autor autor = manager.find(Autor.class, novoLivroRequest.getAutorId());
        // +1
        Assert.notNull(categoria , "Id da categoria não encontrado");
        // +1
        Assert.notNull(autor , "Id do autor não encontrado");
        // +1
        Livro livro = novoLivroRequest.toModel(autor, categoria);

        manager.persist(livro);
        // +1
        NovoLivroResponseDto novoLivroResponseDto = new NovoLivroResponseDto(livro);
        logger.info("Livro salvo com sucesso, id: {}", novoLivroResponseDto.getId());
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/livros/{id}").buildAndExpand(novoLivroResponseDto.getId()).toUri())
                .build();
    }
}
