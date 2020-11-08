package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Compra;
import br.com.jornada.casadocodigo.domain.request.CompraRequest;
import br.com.jornada.casadocodigo.domain.response.CompraResponseDto;
import br.com.jornada.casadocodigo.repository.CompraRepository;
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

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 9
 */

@RestController
@RequestMapping("/v1/compras")
public class CompraResource {

    private static Logger logger = LoggerFactory.getLogger(CompraResource.class);
    @PersistenceContext
    private final EntityManager manager;
    // +1
    private final CompraRepository compraRepository;
    // +1
    private final EstadoRepository estadoRepository;
    // +1
    private final CupomDescontoRepository cupomDescontoRepository;

    public CompraResource(EntityManager manager, CompraRepository compraRepository, EstadoRepository estadoRepository, CupomDescontoRepository cupomDescontoRepository) {
        this.manager = manager;
        this.compraRepository = compraRepository;
        this.estadoRepository = estadoRepository;
        this.cupomDescontoRepository = cupomDescontoRepository;
    }

    @InitBinder
    // +2
    protected void init(WebDataBinder binder){
        binder.addValidators(new EstadoPertencePaisValidator(estadoRepository), new CupomValidoValidator(cupomDescontoRepository));
    }

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> compra(@RequestBody @Valid CompraRequest compraRequest,
                                    UriComponentsBuilder uriComponentsBuilder){

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

        // +1
        Compra compra = compraRequest.toModel(manager, cupomDescontoRepository);

        logger.info("Total da compra - Confirmação: {}", compra.totalCompraConfirmacao().setScale(2));
        logger.info("Total da compra - Cliente: {}", compra.totalCompraCliente().setScale(2));
        // +1
        Assert.isTrue(compra.totalCompraCliente().setScale(2).equals(compra.totalCompraConfirmacao().setScale(2)),"Valor total esta diferente do total real do carrinho!!");

        compraRepository.save(compra);
        // +1
        CompraResponseDto compraResponseDto = new CompraResponseDto(compra);
        logger.info("Compra realizada com sucesso, com id: {} ", compraResponseDto.getId());
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/compras/{id}").buildAndExpand(compraResponseDto.getId()).toUri())
                .build();
    }
}
