package br.com.jornada.casadocodigo.validation;

import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.request.NovoAutorRequest;
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
public class EmailDuplicadoValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NovoAutorRequest novoAutorRequest = (NovoAutorRequest) target;

        List<Autor> autorByEmail = manager.createQuery("select a from Autor a where a.email =: pValue", Autor.class)
                .setParameter("pValue", novoAutorRequest.getEmail())
                .getResultList();

        if (autorByEmail.size()>0){
            String message = messageSource.getMessage("email.duplicado", null, LocaleContextHolder.getLocale());
            errors.rejectValue("email", null, message);
        }

    }

}
