package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.domain.model.Categoria;
import br.com.jornada.casadocodigo.domain.request.NovaCategoriaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class NomeCategoriaDuplicadoValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NovaCategoriaRequest novaCategoriaRequest = (NovaCategoriaRequest) target;

        List<Categoria> categoriaByNome = manager.createQuery("select c from Categoria c where c.nome =: pValue", Categoria.class)
                .setParameter("pValue", novaCategoriaRequest.getNome())
                .getResultList();

        if (categoriaByNome.size()>0){
            String message = messageSource.getMessage("nome.duplicado", null, LocaleContextHolder.getLocale());
            errors.rejectValue("nome", null, message);
        }

    }
}
