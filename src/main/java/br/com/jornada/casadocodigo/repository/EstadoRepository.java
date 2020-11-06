package br.com.jornada.casadocodigo.repository;

import br.com.jornada.casadocodigo.domain.model.Estado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EstadoRepository extends CrudRepository<Estado, String> {

    Optional<Estado> findByPaisId(String paisId);

}
