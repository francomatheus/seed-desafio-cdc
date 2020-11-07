package br.com.jornada.casadocodigo.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 2
 */

@Entity
@Table(name = "carrinhoCompra")
public class CarrinhoCompra {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private BigDecimal total;

    @OneToMany(cascade = CascadeType.PERSIST)
    // +1
    private Set<ItensCompra> itensCompras = new HashSet<>();

    @Deprecated
    public CarrinhoCompra() {
    }

    public CarrinhoCompra(@NotNull @Positive BigDecimal total, Set<ItensCompra> itensCompras) {
        this.total = total;
        this.itensCompras = itensCompras;
    }

    public BigDecimal totalCompra(){

        double somaTotal = itensCompras.stream()
                // +1
                .mapToDouble(itensCompra -> {
                    return itensCompra.resultadoPedido().floatValue();
                }).sum();

        return BigDecimal.valueOf(somaTotal);
    }

    public String getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Set<ItensCompra> getItensCompras() {
        return itensCompras;
    }
}
