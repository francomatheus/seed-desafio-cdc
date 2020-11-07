package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.CupomDesconto;
import br.com.jornada.casadocodigo.domain.request.CupomDescontoRequest;
import br.com.jornada.casadocodigo.domain.response.CupomDescontoResponseDto;
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
@RequestMapping("/v1/cupons")
public class CadastraCupomResource {

    private static Logger logger = LoggerFactory.getLogger(CadastraCupomResource.class);

    @PersistenceContext
    private final EntityManager manager;

    public CadastraCupomResource(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaCupom(@RequestBody @Valid CupomDescontoRequest cupomRequest,
                                       UriComponentsBuilder uriComponentsBuilder){
        logger.info("Requisição para criação de cupom recebida: {}", cupomRequest);
        // +1
        CupomDesconto cupomDesconto = cupomRequest.toModel();

        manager.persist(cupomDesconto);
        // +1
        CupomDescontoResponseDto cupomDescontoResponseDto = new CupomDescontoResponseDto(cupomDesconto);
        logger.info("Cupom Desconto cadastrado com sucesso. Id do cupom: {}", cupomDescontoResponseDto.getId());

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/cupons/{id}").buildAndExpand(cupomDescontoResponseDto.getId()).toUri())
                .build();
    }

}
