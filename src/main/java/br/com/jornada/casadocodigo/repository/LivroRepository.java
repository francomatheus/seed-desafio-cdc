package br.com.jornada.casadocodigo.repository;

import br.com.jornada.casadocodigo.domain.model.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Carga intrínseca máxima permitida - 3
 * Carga intrínseca da classe - 1
 */

@Repository
// +1
public interface LivroRepository extends CrudRepository<Livro, String> {

}
