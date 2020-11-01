package br.com.jornada.casadocodigo.handlerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrosHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadraoDto> validacaoHandlerAdvice(MethodArgumentNotValidException exception){
        List<String> todosErrosValidacao = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String mensagem = fieldError.getField() +" "+ fieldError.getDefaultMessage();
            todosErrosValidacao.add(mensagem);
        });
        ErroPadraoDto erroPadraoDto = new ErroPadraoDto( HttpStatus.BAD_REQUEST, todosErrosValidacao, exception.getMessage());

        return ResponseEntity.badRequest().body(erroPadraoDto);
    }
}
