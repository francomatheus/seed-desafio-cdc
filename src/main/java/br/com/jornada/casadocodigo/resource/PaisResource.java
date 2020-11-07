package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Pais;
import br.com.jornada.casadocodigo.domain.request.NovoPaisRequest;
import br.com.jornada.casadocodigo.domain.response.NovoPaisResponseDto;
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
@RequestMapping("/v1/paises")
public class PaisResource {

    private static Logger logger = LoggerFactory.getLogger(PaisResource.class);

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaPais(@RequestBody @Valid NovoPaisRequest novoPaisRequest,
                                      UriComponentsBuilder uriComponentsBuilder){
        logger.info("Requisição para cria pais recebida: {}", novoPaisRequest);
        // +1
        Pais pais = novoPaisRequest.toModel();

        manager.persist(pais);
        // +1
        NovoPaisResponseDto novoPaisResponseDto = new NovoPaisResponseDto(pais);
        logger.info("Pais criado com sucesso, id: {}", novoPaisResponseDto.getId());
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/paises/{id}").buildAndExpand(novoPaisResponseDto.getId()).toUri())
                .build();
    }
}
