package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.annotation.ValorUnico;
import br.com.jornada.casadocodigo.domain.model.Categoria;

import javax.validation.constraints.NotBlank;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 1
 */

public class NovaCategoriaRequest {

    @NotBlank @ValorUnico(className = Categoria.class, fieldName = "nome")
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
    // +1
    public Categoria toCategoria(){
        return new Categoria(this.nome);
    }
}
