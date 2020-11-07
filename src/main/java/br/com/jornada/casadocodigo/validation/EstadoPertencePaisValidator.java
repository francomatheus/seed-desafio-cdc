package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.domain.model.Estado;
import br.com.jornada.casadocodigo.domain.request.CompraRequest;
import br.com.jornada.casadocodigo.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 5
 */

@Component
public class EstadoPertencePaisValidator implements Validator {

    // +1
    private final EstadoRepository estadoRepository;

    public EstadoPertencePaisValidator(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // +1
        CompraRequest compraRequest = (CompraRequest) target;
        // +1
        if (compraRequest.getEstado() != null){

            String estadoId = compraRequest.getEstado();
            String paisId = compraRequest.getDadosCliente().getPaisId();
            // +1
            Optional<Estado> byPaisId = estadoRepository.findByPaisId(paisId);
            // +1
            if (  !(byPaisId.isPresent() && byPaisId.get().getId().equals(estadoId))){
                errors.rejectValue("dadosCliente.estadoId", null, "Estado não pertence ao Pais");
            }
        }
    }
}
