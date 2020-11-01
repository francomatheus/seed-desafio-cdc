package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.domain.model.Autor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoAutorRequest {

    @Email @NotBlank
    private final String email;
    @NotBlank
    private final String nome;
    @NotBlank @Length(max = 400)
    private final String descricao;

    public NovoAutorRequest(@NotBlank @Email String email,@NotBlank String nome,@NotBlank String descricao) {
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return "NovoAutorRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Autor toAutor(){
        return new Autor(this.email, this.nome, this.descricao);
    }
}
