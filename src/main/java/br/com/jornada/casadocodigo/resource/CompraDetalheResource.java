package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Compra;
import br.com.jornada.casadocodigo.domain.response.CompraDetalheResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/v1/compras")
public class CompraDetalheResource {

    private static Logger logger = LoggerFactory.getLogger(CompraDetalheResource.class);

    @PersistenceContext
    private final EntityManager manager;

    public CompraDetalheResource(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> compraDetalhe(@PathVariable(value = "id",required = true) String idCompra){

        logger.info("Requisição para detalhes compra recebida para id: {}", idCompra);
        Compra compra = manager.find(Compra.class, idCompra);

        Assert.notNull(compra,"Compra não encontrada com o id: ".concat(idCompra));

        CompraDetalheResponseDto compraDetalheResponseDto = new CompraDetalheResponseDto(compra);

        return ResponseEntity.ok().body(compraDetalheResponseDto);
    }
}
