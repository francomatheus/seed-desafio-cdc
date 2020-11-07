package br.com.jornada.casadocodigo.repository;

import br.com.jornada.casadocodigo.domain.model.CupomDesconto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomDescontoRepository extends CrudRepository<CupomDesconto, String> {

    Optional<CupomDesconto> findByCodigo(String codigo);
}
