package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.request.NovoAutorRequest;
import br.com.jornada.casadocodigo.domain.response.AutorResponseDto;
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
    public ResponseEntity<?> criaAutor(@RequestBody @Valid NovoAutorRequest novoAutorRequest, UriComponentsBuilder uriComponentsBuilder){
        logger.info("Requisição para criar novo autor recebida: {}", novoAutorRequest);

        Autor autor = novoAutorRequest.toAutor();

        manager.persist(autor);

        AutorResponseDto autorResponseDto = new AutorResponseDto(autor);
        logger.info("Autor criado: {}", autorResponseDto);
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/autores/{id}").buildAndExpand(autorResponseDto.getId()).toUri())
                .build();
    }



}
