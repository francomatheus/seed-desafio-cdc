package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Compra;
import br.com.jornada.casadocodigo.domain.request.CompraRequest;
import br.com.jornada.casadocodigo.domain.response.CompraResponseDto;
import br.com.jornada.casadocodigo.repository.CompraRespository;
import br.com.jornada.casadocodigo.repository.CupomDescontoRepository;
import br.com.jornada.casadocodigo.repository.EstadoRepository;
import br.com.jornada.casadocodigo.validation.CupomValidoValidator;
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
import java.util.Optional;

@RestController
@RequestMapping("/v1/compras")
public class CompraResource {

    private static Logger logger = LoggerFactory.getLogger(CompraResource.class);
    @PersistenceContext
    private final EntityManager manager;

    private final CompraRespository compraRespository;

    private final EstadoRepository estadoRepository;

    private final CupomDescontoRepository cupomDescontoRepository;

    public CompraResource(EntityManager manager, CompraRespository compraRespository, EstadoRepository estadoRepository, CupomDescontoRepository cupomDescontoRepository) {
        this.manager = manager;
        this.compraRespository = compraRespository;
        this.estadoRepository = estadoRepository;
        this.cupomDescontoRepository = cupomDescontoRepository;
    }

    @InitBinder
    protected void init(WebDataBinder binder){
        binder.addValidators(new EstadoPertencePaisValidator(estadoRepository), new CupomValidoValidator(cupomDescontoRepository));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> compra(@RequestBody @Valid CompraRequest compraRequest, UriComponentsBuilder uriComponentsBuilder){

        logger.info("Requisição para compra recebida: {}", compraRequest);
        /**
         * uma vez associado o cupom, uma compra nunca pode ter essa informação alterada.
         * O cupom só pode ser associada com uma compra que ainda não foi registrada no banco de dados (esse daqui eu não implementei)
         *
         * if (compraRequest.getCupomDesconto() != null){
         *             Optional<Compra> compraComCupomDesconto = compraRespository.findByCupomDescontoAndCodigo(compraRequest.pegaCodigoCupomDesconto());
         *             Assert.isNull(compraComCupomDesconto,"Cupom Desconta já utilizado!!");
         *         }
         */


        Compra compra = compraRequest.toModel(manager, cupomDescontoRepository);

        logger.info("Total da compra - Confirmação: {}", compra.totalCompraConfirmacao().setScale(2));
        logger.info("Total da compra - Cliente: {}", compra.totalCompraCliente().setScale(2));

        Assert.isTrue(compra.totalCompraCliente().setScale(2).equals(compra.totalCompraConfirmacao().setScale(2)),"Valor total esta diferente do total real do carrinho!!");

        compraRespository.save(compra);

        CompraResponseDto compraResponseDto = new CompraResponseDto(compra);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/compras/{id}").buildAndExpand(compraResponseDto.getId()).toUri())
                .build();
    }
}
