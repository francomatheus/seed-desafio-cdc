package br.com.jornada.casadocodigo.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 0
 */

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String nome;

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Categoria() {
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
