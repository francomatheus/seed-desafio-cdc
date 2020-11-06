package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Compra;
import br.com.jornada.casadocodigo.domain.request.CompraRequest;
import br.com.jornada.casadocodigo.repository.EstadoRepository;
import br.com.jornada.casadocodigo.validation.EstadoPertencePaisValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/compras")
public class CompraResource {

    private static Logger logger = LoggerFactory.getLogger(CompraResource.class);
    @PersistenceContext
    private final EntityManager manager;

    private final EstadoRepository estadoRepository;

    public CompraResource(EntityManager manager, EstadoRepository estadoRepository) {
        this.manager = manager;
        this.estadoRepository = estadoRepository;
    }

    @InitBinder
    protected void init(WebDataBinder binder){
        binder.addValidators(new EstadoPertencePaisValidator(estadoRepository));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> compra(@RequestBody @Valid CompraRequest compraRequest, UriComponentsBuilder uriComponentsBuilder){

        logger.info("Requisição para compra recebida: {}", compraRequest);

        Compra compra = compraRequest.toModel(manager);

        logger.info("Total da compra - Confirmação: {}", compra.totalCompraConfirmacao().setScale(2));
        logger.info("Total da compra - Cliente: {}", compra.totalCompraCliente().setScale(2));

        Assert.isTrue(compra.totalCompraCliente().setScale(2).equals(compra.totalCompraConfirmacao().setScale(2)),"Valor total esta diferente do total real do carrinho!!");

        manager.persist(compra);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/compras/{id}").buildAndExpand(1).toUri())
                .build();
    }
}
