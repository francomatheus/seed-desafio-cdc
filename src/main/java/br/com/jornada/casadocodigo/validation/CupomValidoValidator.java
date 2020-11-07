package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.domain.model.CupomDesconto;
import br.com.jornada.casadocodigo.domain.request.CompraRequest;
import br.com.jornada.casadocodigo.repository.CupomDescontoRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class CupomValidoValidator implements Validator {

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

        CompraRequest compraRequest = (CompraRequest) o;

        if (compraRequest.getCupomDesconto() != null){

            Optional<CupomDesconto> cupomDescontoByCodigo = cupomDescontoRepository.findByCodigo(compraRequest.pegaCodigoCupomDesconto());
            if (cupomDescontoByCodigo.isEmpty() ){
                errors.rejectValue("cupomDesconto.codigo", null, "Cupom Desconto não existe!!");
            }
        }


    }
}
