package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Categoria;
import br.com.jornada.casadocodigo.domain.request.NovaCategoriaRequest;
import br.com.jornada.casadocodigo.domain.response.NovaCategoriaResponseDto;
import br.com.jornada.casadocodigo.validation.NomeCategoriaDuplicadoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaResource {
    private static Logger logger = LoggerFactory.getLogger(CategoriaResource.class);

    private final EntityManager manager;

    public CategoriaResource(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaCategoria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest,
                                           UriComponentsBuilder uriComponentsBuilder){
        logger.info("Requisição para criar categoria recebida: {}", novaCategoriaRequest);
        // +1
        Categoria categoria = novaCategoriaRequest.toCategoria();

        manager.persist(categoria);
        // +1
        NovaCategoriaResponseDto novaCategoriaResponseDto = new NovaCategoriaResponseDto(categoria);
        logger.info("Categoria criada com sucesso. idCategoria: {}", novaCategoriaResponseDto.getId());
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/categorias/{id}").buildAndExpand(novaCategoriaResponseDto.getId()).toUri())
                .build();
    }
}
