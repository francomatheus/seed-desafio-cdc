package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.request.NovoAutorRequest;
import br.com.jornada.casadocodigo.domain.response.AutorResponseDto;
import br.com.jornada.casadocodigo.validation.EmailDuplicadoValidator;
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
@RequestMapping("/v1/autores")
public class NovoAutorResource {

    private static Logger logger = LoggerFactory.getLogger(NovoAutorResource.class);

    private final EntityManager manager;

    public NovoAutorResource(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaAutor(@RequestBody @Valid NovoAutorRequest novoAutorRequest,
                                       UriComponentsBuilder uriComponentsBuilder){
        logger.info("Requisição para criar novo autor recebida: {}", novoAutorRequest);
        // +1
        Autor autor = novoAutorRequest.toAutor();

        manager.persist(autor);
        // +1
        AutorResponseDto autorResponseDto = new AutorResponseDto(autor);
        logger.info("Autor criado com sucesso, id : {}", autorResponseDto.getId());
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/autores/{id}").buildAndExpand(autorResponseDto.getId()).toUri())
                .build();
    }



}
