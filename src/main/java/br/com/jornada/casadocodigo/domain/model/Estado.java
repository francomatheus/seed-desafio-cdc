package br.com.jornada.casadocodigo.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 1
 */

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String nome;
    @OneToOne
    // +1
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(@NotBlank String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
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
        return "Estado{" +
                "nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
    }
}
