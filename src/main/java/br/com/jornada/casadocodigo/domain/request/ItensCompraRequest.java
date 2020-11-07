package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.annotation.ValorExiste;
import br.com.jornada.casadocodigo.domain.model.ItensCompra;
import br.com.jornada.casadocodigo.domain.model.Livro;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 3
 */

public class ItensCompraRequest {

    @ValorExiste(className = Livro.class, fieldName = "id")
    @NotBlank
    private String idLivro;
    @NotNull @Positive
    private Integer quantidade;

    public ItensCompraRequest(@NotBlank String idLivro, @NotNull @Positive Integer quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public String getIdLivro() {
        return idLivro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    // +1
    public ItensCompra toModel(EntityManager entityManager){
        // +1
        Livro livro = entityManager.find(Livro.class, this.idLivro);
        // +1
        Assert.notNull(livro, "Id do livro não encontrado");

        return new ItensCompra(livro,this.quantidade);
    }
}
