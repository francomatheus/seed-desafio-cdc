package br.com.jornada.casadocodigo.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotBlank @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime instante;

    @Deprecated
    public Autor() {

    }

    public Autor(@NotBlank @Email String email,@NotBlank String nome,@NotBlank String descricao) {
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
        this.instante = LocalDateTime.now();
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
        return "Autor{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", instante=" + instante +
                '}';
    }
}
