package br.com.jornada.casadocodigo.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 2
 */

@Embeddable
public class DadosCliente {

    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    private String telefone;
    private String cep;
    @OneToOne
    // +1
    private Estado estado;
    @OneToOne
    // +1
    private Pais pais;

    public DadosCliente(String email, String nome, String sobrenome, String documento, String endereco, String complemento, String cidade, Pais pais, String telefone, String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
    }
    @Deprecated
    public DadosCliente() {
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Estado getEstado() {
        return estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void adicionaEstado(Estado estado) {
        this.estado = estado;
    }

}
