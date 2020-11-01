package br.com.jornada.casadocodigo.handlerAdvice;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ErroPadraoDto {

    private final LocalDateTime instante;
    private final HttpStatus status;
    private final List<String> erros;
    private final String pilhaErro;

    public ErroPadraoDto(HttpStatus status, List<String> erros, String pilhaErro) {
        this.instante = LocalDateTime.now();
        this.status = status;
        this.erros = erros;
        this.pilhaErro = pilhaErro;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getErros() {
        return erros;
    }

    public String getPilhaErro() {
        return pilhaErro;
    }
}
