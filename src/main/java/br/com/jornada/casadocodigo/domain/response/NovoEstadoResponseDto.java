package br.com.jornada.casadocodigo.domain.response;

import br.com.jornada.casadocodigo.domain.model.Estado;
import br.com.jornada.casadocodigo.domain.model.Pais;

public class NovoEstadoResponseDto {

    private final String id;
    private final String nome;
    private final Pais pais;

    public NovoEstadoResponseDto(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.pais = estado.getPais();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "NovoEstadoResponseDto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
    }
}
