package br.com.jornada.casadocodigo.domain.response;

import br.com.jornada.casadocodigo.domain.model.Pais;

public class NovoPaisResponseDto {

    private String id;
    private String nome;

    public NovoPaisResponseDto(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "NovoPaisResponseDto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
