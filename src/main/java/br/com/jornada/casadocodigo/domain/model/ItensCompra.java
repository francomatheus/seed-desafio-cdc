package br.com.jornada.casadocodigo.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itensCompra")
public class ItensCompra {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private Integer quantidade;
    @ManyToOne
    private Livro livro;

    public ItensCompra(Livro livro, Integer quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
    }

    @Deprecated
    public ItensCompra() {
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Livro getLivro() {
        return livro;
    }

    public BigDecimal resultadoPedido(){
        return this.livro.getPreco().multiply(new BigDecimal(quantidade));
    }
}
