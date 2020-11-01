package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Categoria;
import br.com.jornada.casadocodigo.domain.request.NovaCategoriaRequest;
import br.com.jornada.casadocodigo.domain.response.NovaCategoriaResponseDto;
import br.com.jornada.casadocodigo.validation.NomeCategoriaDuplicadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaResource {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private NomeCategoriaDuplicadoValidator nomeCategoriaDuplicadoValidator;

    @InitBinder
    protected void init(WebDataBinder binder){
        binder.addValidators(nomeCategoriaDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criaCategoria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest, UriComponentsBuilder uriComponentsBuilder){

        Categoria categoria = novaCategoriaRequest.toCategoria();

        manager.persist(categoria);

        NovaCategoriaResponseDto novaCategoriaResponseDto = new NovaCategoriaResponseDto(categoria);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/categorias/{id}").buildAndExpand(novaCategoriaResponseDto.getId()).toUri())
                .build();
    }
}
