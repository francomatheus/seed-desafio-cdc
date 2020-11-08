package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Livro;
import br.com.jornada.casadocodigo.domain.response.PaginaDetalheResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@RestController
@RequestMapping("/v1/livros")
public class PaginaDetalhesResource {

    private static Logger logger = LoggerFactory.getLogger(PaginaDetalhesResource.class);

    @PersistenceContext
    private final EntityManager manager;

    public PaginaDetalhesResource(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> paginaDetalhe(@PathVariable("id") String idLivro){
        logger.info("Requisição para pagina de detalhe do livro recebdio. Id: {}", idLivro);
        // +1
        Livro livro = manager.find(Livro.class, idLivro);
        // +1
        if (livro==null){
            logger.warn("Não existe pagina de Detalhe para idLivro: {}", idLivro);
            return ResponseEntity.notFound().build();
        }
        // +1
        PaginaDetalheResponseDto paginaDetalheResponseDto = new PaginaDetalheResponseDto(livro);

        logger.info("Detalhes da pagina disponiveis: {} ", paginaDetalheResponseDto);
        return ResponseEntity.ok(paginaDetalheResponseDto);
    }
}
