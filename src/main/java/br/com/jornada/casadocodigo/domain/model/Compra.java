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

    public BigDecimal totalCompraConfirmacao(){
        return this.carrinhoCompra.totalCompra();
    }

    public BigDecimal totalCompraCliente(){
        return this.carrinhoCompra.getTotal();
    }
}
