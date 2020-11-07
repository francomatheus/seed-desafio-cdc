package br.com.jornada.casadocodigo.domain.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 2
 */

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotBlank
    private String titulo;
    @NotBlank @Length(max = 500)
    private String resumo;
    private String sumario;
    @Min(20) @NotNull
    @Positive
    private BigDecimal preco;
    @NotNull @Min(100) @Positive
    private Integer numeroPagina;
    @NotBlank
    private String isbn;
    @NotNull @Future
    private LocalDate dataPublicacao;
    @ManyToOne
    // +1
    private Categoria categoria;
    @ManyToOne
    // +1
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo, @NotBlank @Length(max = 500) String resumo, String sumario, @Min(20) @NotNull @Positive BigDecimal preco, @NotNull @Min(100) @Positive Integer numeroPagina, @NotBlank String isbn, @NotNull @Future LocalDate dataPublicacao, Categoria categoria, Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPagina = numeroPagina;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPagina() {
        return numeroPagina;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPagina=" + numeroPagina +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }
}
