package br.com.jornada.casadocodigo.domain.response;

import br.com.jornada.casadocodigo.domain.model.Categoria;

public class NovaCategoriaResponseDto {

    private final String id;
    private final String nome;


    public NovaCategoriaResponseDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "NovaCategoriaResponseDto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
