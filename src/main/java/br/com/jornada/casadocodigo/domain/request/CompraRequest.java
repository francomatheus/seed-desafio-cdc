package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.domain.model.CarrinhoCompra;
import br.com.jornada.casadocodigo.domain.model.Compra;
import br.com.jornada.casadocodigo.domain.model.CupomDesconto;
import br.com.jornada.casadocodigo.domain.model.DadosCliente;
import br.com.jornada.casadocodigo.repository.CupomDescontoRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CompraRequest {
    @Valid @NotNull
    private DadosClienteRequest dadosCliente;
    @Valid @NotNull
    private CarrinhoCompraRequest carrinhoCompra;

    private @Valid CupomDescontoCompraRequest cupomDesconto;

    public CompraRequest(DadosClienteRequest dadosCliente, CarrinhoCompraRequest carrinhoCompra, CupomDescontoCompraRequest cupomDesconto) {
        this.dadosCliente = dadosCliente;
        this.carrinhoCompra = carrinhoCompra;
        this.cupomDesconto = cupomDesconto;
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

    public CupomDescontoCompraRequest getCupomDesconto() {
        return cupomDesconto;
    }

    public String pegaCodigoCupomDesconto(){
        return this.cupomDesconto.getCodigo();
    }

    public Compra toModel(EntityManager manager, CupomDescontoRepository cupomDescontoRepository){
        DadosCliente dadosCliente = this.dadosCliente.toModel(manager);
        CarrinhoCompra carrinhoCompra = this.carrinhoCompra.toModel(manager);

        Compra compra = new Compra(dadosCliente, carrinhoCompra);

        if (cupomDesconto!=null){
            Optional<CupomDesconto> cupomDesconto = cupomDescontoRepository.findByCodigo(this.cupomDesconto.getCodigo());
            Assert.notNull(cupomDesconto, "Não existe esse cupom de desconto.");
            compra.aplicaCupomDesconto(cupomDesconto.get());
        }

        return compra;
    }
}
