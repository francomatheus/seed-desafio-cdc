package br.com.jornada.casadocodigo.domain.response;

import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.model.Livro;

import java.math.BigDecimal;

public class PaginaDetalheResponseDto {

    private final String id;
    private final String titulo;
    private final String resumo;
    private final String sumario;
    private final BigDecimal preco;
    private final Integer numeroPagina;
    private final String isbn;
    private final Autor autor;

    public PaginaDetalheResponseDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPagina = livro.getNumeroPagina();
        this.isbn = livro.getIsbn();
        this.autor = livro.getAutor();
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

    public Autor getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "PaginaDetalheResponseDto{" +
                "titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPagina=" + numeroPagina +
                ", isbn='" + isbn + '\'' +
                ", autor=" + autor +
                '}';
    }
}
