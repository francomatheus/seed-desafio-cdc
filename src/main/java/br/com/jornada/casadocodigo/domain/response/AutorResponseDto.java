package br.com.jornada.casadocodigo.domain.response;

import br.com.jornada.casadocodigo.domain.model.Autor;

import java.time.LocalDateTime;

public class AutorResponseDto {
    private final String id;
    private final String email;
    private final String nome;
    private final String descricao;
    private final LocalDateTime instante;

    public AutorResponseDto(Autor autor) {
        this.id = autor.getId();
        this.email = autor.getEmail();
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
        this.instante = autor.getInstante();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    @Override
    public String toString() {
        return "AutorResponseDto{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", instante=" + instante +
                '}';
    }
}
