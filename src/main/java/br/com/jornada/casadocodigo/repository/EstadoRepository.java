package br.com.jornada.casadocodigo.repository;

import br.com.jornada.casadocodigo.domain.model.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 3
 * Carga intrínseca da classe - 1
 */

@Repository
// +1
public interface EstadoRepository extends CrudRepository<Estado, String> {

    Optional<Estado> findByPaisId(String paisId);

}
