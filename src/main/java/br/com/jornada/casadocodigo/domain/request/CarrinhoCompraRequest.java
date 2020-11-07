package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.domain.model.CarrinhoCompra;
import br.com.jornada.casadocodigo.domain.model.ItensCompra;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 5
 */

public class CarrinhoCompraRequest {

    @NotNull
    @Positive
    private BigDecimal total;
    @Size(min = 1)
    // +1
    private Set< @Valid ItensCompraRequest> itens = new HashSet<>();

    public CarrinhoCompraRequest(@NotNull @Positive BigDecimal total, @Size(min = 1) Set<@Valid ItensCompraRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Set<ItensCompraRequest> getItens() {
        return itens;
    }
    // +1
    public CarrinhoCompra toModel(EntityManager manager){
        // +1
        Set<ItensCompra> itensCompras = this.itens.stream()
                // +1
                .map(itensCompraRequest -> {
                    return itensCompraRequest.toModel(manager);
                })
                // +1
                .collect(Collectors.toSet());

        return new CarrinhoCompra(this.total, itensCompras);
    }
}
