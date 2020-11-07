package br.com.jornada.casadocodigo.repository;

import br.com.jornada.casadocodigo.domain.model.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompraRespository extends CrudRepository<Compra, String> {

    Optional<Compra> findByCupomDescontoAndCodigo(String codigoCupom);
}
