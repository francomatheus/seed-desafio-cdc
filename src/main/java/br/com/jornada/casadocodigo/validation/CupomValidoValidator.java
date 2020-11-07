package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.domain.model.CupomDesconto;
import br.com.jornada.casadocodigo.domain.request.CompraRequest;
import br.com.jornada.casadocodigo.repository.CupomDescontoRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 5
 */

@Component
public class CupomValidoValidator implements Validator {
    // +1
    private final CupomDescontoRepository cupomDescontoRepository;

    public CupomValidoValidator(CupomDescontoRepository cupomDescontoRepository) {
        this.cupomDescontoRepository = cupomDescontoRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //+1
        CompraRequest compraRequest = (CompraRequest) o;
        // +1
        if (compraRequest.getCupomDesconto() != null){
            // +1
            Optional<CupomDesconto> cupomDescontoByCodigo = cupomDescontoRepository.findByCodigo(compraRequest.pegaCodigoCupomDesconto());
            // +1
            if (cupomDescontoByCodigo.isEmpty() ){
                errors.rejectValue("cupomDesconto.codigo", null, "Cupom Desconto não existe!!");
            }
        }
    }
}
