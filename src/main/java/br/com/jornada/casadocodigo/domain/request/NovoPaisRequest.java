package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.annotation.ValorUnico;
import br.com.jornada.casadocodigo.domain.model.Pais;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {

    @ValorUnico(className = Pais.class, fieldName = "nome")
    @NotBlank
    private String nome;

    public NovoPaisRequest() {
    }

    public NovoPaisRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "NovoPaisRequest{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public Pais toModel(){
        return new Pais(this.nome);
    }
}
