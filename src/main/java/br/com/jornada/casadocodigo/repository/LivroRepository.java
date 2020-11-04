package br.com.jornada.casadocodigo.repository;

import br.com.jornada.casadocodigo.domain.model.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends CrudRepository<Livro, String> {

}
