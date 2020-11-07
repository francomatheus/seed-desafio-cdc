package br.com.jornada.casadocodigo.domain.model;

import java.math.BigDecimal;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 0
 */

public class DetalheCompra {

    private final BigDecimal valorTotal;
    private final BigDecimal valorDesconto;
    private final BigDecimal valorFinal;

    public DetalheCompra(BigDecimal valorTotal, BigDecimal valorDesconto, BigDecimal valorFinal) {
        this.valorTotal = valorTotal;
        this.valorDesconto = valorDesconto;
        this.valorFinal = valorFinal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }
}
