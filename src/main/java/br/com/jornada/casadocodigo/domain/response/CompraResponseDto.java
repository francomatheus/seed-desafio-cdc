package br.com.jornada.casadocodigo.domain.response;

import br.com.jornada.casadocodigo.domain.model.Compra;

public class CompraResponseDto {
    private String id;

    public CompraResponseDto(Compra compra) {
        this.id = compra.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
