package br.com.jornada.casadocodigo.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Embedded
    private DadosCliente dadosCliente;
    @OneToOne(cascade = CascadeType.PERSIST)
    private CarrinhoCompra carrinhoCompra;
    @OneToOne
    private CupomDesconto cupomDesconto;

    public Compra(DadosCliente dadosCliente, CarrinhoCompra carrinhoCompra) {
        this.dadosCliente = dadosCliente;
        this.carrinhoCompra = carrinhoCompra;
    }

    @Deprecated
    public Compra() {
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

    public CupomDesconto getCupomDesconto() {
        return cupomDesconto;
    }

    public BigDecimal totalCompraConfirmacao(){
        return this.carrinhoCompra.totalCompra();
    }

    public BigDecimal totalCompraCliente(){
        return this.carrinhoCompra.getTotal();
    }

    public void aplicaCupomDesconto(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public boolean existeCupom(){
        return this.cupomDesconto!=null;
    }

    public BigDecimal getValorTotal() {
        return this.carrinhoCompra.getTotal().setScale(2);
    }

    public Double getDesconto() {
        return this.cupomDesconto.getDesconto();
    }
}
