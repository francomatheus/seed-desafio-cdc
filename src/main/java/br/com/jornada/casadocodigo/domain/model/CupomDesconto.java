package br.com.jornada.casadocodigo.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "cupomDesconto")
public class CupomDesconto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotNull @Column(unique = true)
    private String codigo;
    @NotNull @Positive
    private Double desconto;
    @NotNull @Future
    private LocalDate validoAte;

    @Deprecated
    public CupomDesconto() {
    }

    public CupomDesconto(@NotBlank String codigo, @NotNull @Positive Double desconto, @FutureOrPresent @NotNull LocalDate validoAte) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.validoAte = validoAte;
    }

    public String getId() {
        return id;
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
        return "CupomDesconto{" +
                "id='" + id + '\'' +
                ", codigo='" + codigo + '\'' +
                ", desconto=" + desconto +
                ", validoAte=" + validoAte +
                '}';
    }
}
