package br.com.jornada.casadocodigo.domain.response;

import br.com.jornada.casadocodigo.domain.model.CupomDesconto;

public class CupomDescontoResponseDto {

    private final String id;

    public CupomDescontoResponseDto(CupomDesconto cupomDesconto) {
        this.id = cupomDesconto.getId();
    }

    public String getId() {
        return id;
    }
}
