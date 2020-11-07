package br.com.jornada.casadocodigo.domain.request;

import br.com.jornada.casadocodigo.annotation.ValorExiste;
import br.com.jornada.casadocodigo.domain.model.CupomDesconto;

public class CupomDescontoCompraRequest {

    @ValorExiste(className = CupomDesconto.class, fieldName = "codigo")
    private String codigo;

    public CupomDescontoCompraRequest() {
    }

    public CupomDescontoCompraRequest(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
