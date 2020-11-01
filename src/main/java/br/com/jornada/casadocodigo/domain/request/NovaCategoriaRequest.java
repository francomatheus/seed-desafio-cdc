package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.domain.model.Categoria;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    private String nome;

    public NovaCategoriaRequest() {
    }

    public NovaCategoriaRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "NovaCategoriaRequest{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public Categoria toCategoria(){
        return new Categoria(this.nome);
    }
}
