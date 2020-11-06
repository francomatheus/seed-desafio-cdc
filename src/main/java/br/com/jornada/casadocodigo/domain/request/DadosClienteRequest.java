package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.annotation.DocumentoValido;
import br.com.jornada.casadocodigo.domain.model.DadosCliente;
import br.com.jornada.casadocodigo.domain.model.Estado;
import br.com.jornada.casadocodigo.domain.model.Pais;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class DadosClienteRequest {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank @DocumentoValido
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String paisId;
    private String estadoId;
    @NotBlank @Length(min = 11,max = 12)
    private String telefone;
    @NotBlank @Length(min = 8, max = 8) @Positive
    private String cep;

    @Deprecated
    public DadosClienteRequest() {
    }

    public DadosClienteRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade, @NotBlank String paisId, @NotBlank String estadoId, @NotBlank String telefone, @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
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

    public String getPaisId() {
        return paisId;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return "DadosClienteRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", paisId='" + paisId + '\'' +
                ", estadoId='" + estadoId + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }

    public DadosCliente toModel(EntityManager manager){
        Pais pais = manager.find(Pais.class, this.paisId);
        Assert.notNull(pais, "Pais não encontrado para o id: ".concat(paisId));

        DadosCliente dadosCliente = new DadosCliente(this.email, this.nome, this.sobrenome, this.documento, this.endereco, this.complemento, this.cidade, pais, this.telefone, this.cep);
        if (estadoId != null){
            Estado estado = manager.find(Estado.class, estadoId);
            Assert.notNull(estado, "Estado não encontrado para o id: ".concat(estadoId));
            dadosCliente.adicionaEstado(estado);
        }


        return dadosCliente;
    }
}
