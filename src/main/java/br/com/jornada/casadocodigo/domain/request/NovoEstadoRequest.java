package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.annotation.ValorUnico;
import br.com.jornada.casadocodigo.domain.model.Estado;
import br.com.jornada.casadocodigo.domain.model.Pais;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class NovoEstadoRequest {

    @NotBlank @ValorUnico(className = Estado.class, fieldName = "nome")
    private final String nome;
    @NotBlank
    private final String idPais;

    public NovoEstadoRequest(@NotBlank String nome, @NotBlank String idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public String getIdPais() {
        return idPais;
    }

    @Override
    public String toString() {
        return "NovoEstadoRequest{" +
                "nome='" + nome + '\'' +
                ", idPais='" + idPais + '\'' +
                '}';
    }

    public Estado toModel(EntityManager manager){
        Pais pais = manager.find(Pais.class, this.idPais);
        Assert.notNull(pais, "Id do Pais não encontrado!!");

        return new Estado(this.nome, pais);

    }
}
