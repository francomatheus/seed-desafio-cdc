package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.domain.model.CarrinhoCompra;
import br.com.jornada.casadocodigo.domain.model.Compra;
import br.com.jornada.casadocodigo.domain.model.DadosCliente;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CompraRequest {
    @Valid @NotNull
    private DadosClienteRequest dadosCliente;
    @Valid @NotNull
    private CarrinhoCompraRequest carrinhoCompra;

    public CompraRequest(DadosClienteRequest dadosCliente, CarrinhoCompraRequest carrinhoCompra) {
        this.dadosCliente = dadosCliente;
        this.carrinhoCompra = carrinhoCompra;
    }

    public DadosClienteRequest getDadosCliente() {
        return dadosCliente;
    }

    public CarrinhoCompraRequest getCarrinhoCompra() {
        return carrinhoCompra;
    }

    public String getEstado(){
        return this.dadosCliente.getEstadoId();
    }

    public Compra toModel(EntityManager manager){
        DadosCliente dadosCliente = this.dadosCliente.toModel(manager);
        CarrinhoCompra carrinhoCompra = this.carrinhoCompra.toModel(manager);
        return new Compra(dadosCliente, carrinhoCompra);
    }
}
