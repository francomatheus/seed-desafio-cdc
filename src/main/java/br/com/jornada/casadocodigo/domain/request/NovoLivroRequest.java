package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.annotation.ValorUnico;
import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.model.Categoria;
import br.com.jornada.casadocodigo.domain.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {

    @NotBlank @ValorUnico(className = Livro.class, fieldName = "titulo")
    private String titulo;
    @NotBlank @Length(max = 500)
    private String resumo;
    private String sumario;
    @Min(20) @NotNull @Positive
    private BigDecimal preco;
    @NotNull @Min(100) @Positive
    private Integer numeroPagina;
    @NotBlank @ValorUnico(className = Livro.class, fieldName = "isbn")
    private String isbn;
    @NotNull @Future
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotBlank
    private String categoriaId;
    @NotBlank
    private String autorId;

    public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Length(max = 500) String resumo, String sumario, @Min(20) @NotNull @Positive BigDecimal preco, @NotNull @Min(100) @Positive Integer numeroPagina, @NotBlank String isbn, @NotNull @Future LocalDate dataPublicacao, @NotBlank String categoriaId, @NotBlank String autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPagina = numeroPagina;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
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

    public String getCategoriaId() {
        return categoriaId;
    }

    public String getAutorId() {
        return autorId;
    }

    @Override
    public String toString() {
        return "NovoLivroRequest{" +
                "titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPagina=" + numeroPagina +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoriaId='" + categoriaId + '\'' +
                ", autorId='" + autorId + '\'' +
                '}';
    }

    public Livro toModel(Autor autor, Categoria categoria) {
        return new Livro(this.titulo,this.resumo,this.sumario,this.preco,this.numeroPagina,this.isbn,this.dataPublicacao,categoria,autor);
    }
}
