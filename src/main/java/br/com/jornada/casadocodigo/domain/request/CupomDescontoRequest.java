package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.annotation.ValorUnico;
import br.com.jornada.casadocodigo.domain.model.CupomDesconto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 1
 */

public class CupomDescontoRequest {

    @NotBlank @ValorUnico(className = CupomDesconto.class, fieldName = "codigo")
    private final String codigo;
    @NotNull @Positive @Max(100)
    private final Double desconto;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    @NotNull
    private final LocalDate validoAte;

    public CupomDescontoRequest(@NotBlank String codigo, @NotNull @Positive Double desconto, @FutureOrPresent @NotNull LocalDate validoAte) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.validoAte = validoAte;
    }

    public String getCodigo() {
        return codigo;
    }

    public Double getDesconto() {
        return desconto;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    @Override
    public String toString() {
        return "CupomRequest{" +
                "codigo='" + codigo + '\'' +
                ", desconto=" + desconto +
                ", validoAte=" + validoAte +
                '}';
    }

    // +1
    public CupomDesconto toModel(){
        return new CupomDesconto(this.codigo,this.desconto,this.validoAte);
    }
}
