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

public class CarrinhoCompraRequest {

    @NotNull
    @Positive
    private BigDecimal total;
    @Size(min = 1)
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

    public CarrinhoCompra toModel(EntityManager manager){

        Set<ItensCompra> itensCompras = this.itens.stream().map(itensCompraRequest -> {
            return itensCompraRequest.toModel(manager);
        }).collect(Collectors.toSet());

        return new CarrinhoCompra(this.total, itensCompras);
    }
}
