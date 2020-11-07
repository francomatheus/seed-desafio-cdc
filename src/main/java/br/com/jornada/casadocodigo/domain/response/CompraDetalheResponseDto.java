package br.com.jornada.casadocodigo.domain.response;

import br.com.jornada.casadocodigo.domain.model.CarrinhoCompra;
import br.com.jornada.casadocodigo.domain.model.Compra;
import br.com.jornada.casadocodigo.domain.model.DadosCliente;
import br.com.jornada.casadocodigo.domain.model.DetalheCompra;

import java.math.BigDecimal;

public class CompraDetalheResponseDto {

    private final String id;
    private final DadosCliente dadosCliente;
    private final CarrinhoCompra carrinhoCompra;
    private final Boolean existeCupom;
    private final Double valorCupom;
    private final DetalheCompra valorfinal;

    public CompraDetalheResponseDto(Compra compra) {
        this.id = compra.getId();
        this.dadosCliente = compra.getDadosCliente();
        this.carrinhoCompra = compra.getCarrinhoCompra();
        this.existeCupom = existeCupom(compra);
        this.valorCupom = valorCupom(compra);
        this.valorfinal = calculaValorFinal(compra);
    }

    public String getId() {
        return id;
    }

    public DadosCliente getDadosCliente() {
        return dadosCliente;
    }

    public CarrinhoCompra getCarrinhoCompra() {
        return carrinhoCompra;
    }

    public Boolean getExisteCupom() {
        return existeCupom;
    }

    public Double getValorCupom() {
        return valorCupom;
    }

    public DetalheCompra getValorfinal() {
        return valorfinal;
    }

    public boolean existeCupom(Compra compra){
        return compra.existeCupom();
    }

    public Double valorCupom(Compra compra){
        if (compra.existeCupom()) {
            return compra.getCupomDesconto().getDesconto();
        }
        return null;
    }

    public DetalheCompra calculaValorFinal(Compra compra){
        if (compra.existeCupom()) {
            var valorTotal = compra.getValorTotal();
            var desconto = compra.getDesconto();

            var valorADescontar = valorTotal.multiply(new BigDecimal(desconto).divide(new BigDecimal(100))).setScale(2);

            var valorFinal = valorTotal.subtract(valorADescontar).setScale(2);

            DetalheCompra detalheCompra = new DetalheCompra(valorTotal,valorADescontar,valorFinal);
            return detalheCompra;
        }

        return new DetalheCompra(compra.getValorTotal(), new BigDecimal(0),compra.getValorTotal());
    }
}
