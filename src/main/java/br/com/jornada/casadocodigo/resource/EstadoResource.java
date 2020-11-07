package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Estado;
import br.com.jornada.casadocodigo.domain.request.NovoEstadoRequest;
import br.com.jornada.casadocodigo.domain.response.NovoEstadoResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
 * Carga intrínseca da classe - 3
 */

@RestController
@RequestMapping("/v1/estados")
public class EstadoResource {

    private static Logger logger = LoggerFactory.getLogger(EstadoResource.class);

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaEstado(@RequestBody @Valid NovoEstadoRequest novoEstadoRequest,
                                        UriComponentsBuilder uriComponentsBuilder){
        logger.info("Requisição para criar novo estado recebedi: {}", novoEstadoRequest);
        // +1
        Estado estado = novoEstadoRequest.toModel(manager);

        manager.persist(estado);
        // +1
        NovoEstadoResponseDto novoEstadoResponseDto = new NovoEstadoResponseDto(estado);
        logger.info("Estado criado com sucesso, id: {}", novoEstadoResponseDto.getId());
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/estados/{id}").buildAndExpand(novoEstadoResponseDto.getId()).toUri())
                .build();
    }

}
