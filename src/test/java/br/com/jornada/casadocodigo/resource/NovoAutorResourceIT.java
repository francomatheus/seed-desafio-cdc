package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.Autor;
import br.com.jornada.casadocodigo.domain.request.NovoAutorRequest;
import br.com.jornada.casadocodigo.handlerAdvice.ErroPadraoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.net.URI;
import java.net.URISyntaxException;

@WebMvcTest
public class NovoAutorResourceIT {

    private final String URI_BASE = "http://localhost:8080";

    @MockBean
    private EntityManager manager;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    @DisplayName("Salva novo autor se for for valido")
    public void deveSalvarAutorSeForValido() throws URISyntaxException {

        NovoAutorRequest novoAutorRequest = new NovoAutorRequest("Teste@gmail.com","Admin", "Texto qualquer");
        Autor autor = novoAutorRequest.toAutor();

        URI pathAutores = new URI(URI_BASE.concat("/v1/autores"));
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity( novoAutorRequest, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> exchange = restTemplate
                .exchange(pathAutores, HttpMethod.POST, httpEntity, Void.class);

        String autoresLocation = exchange.getHeaders().getLocation().toString();

        //verify(manager).persist(autor);
        Assertions.assertEquals(HttpStatus.CREATED, exchange.getStatusCode());

        Assertions.assertTrue(StringUtils.hasText(autoresLocation));

    }

    @Test
    @DisplayName("Deve retornar 400 se email for invalido")
    public void deverRetornar400SeEmailForInvalido() {
        NovoAutorRequest novoAutorRequest = new NovoAutorRequest("Teste","Admin", "Texto qualquer");

        String pathAutores = URI_BASE.concat("/v1/autores");
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity( novoAutorRequest, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ErroPadraoDto> response = restTemplate
                .exchange(pathAutores, HttpMethod.POST, httpEntity, ErroPadraoDto.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
    }

    @Test
    @DisplayName("Deve retornar 400 se email for nulo")
    public void deverRetornar400SeEmailForNulo(){

    }

    @Test
    @DisplayName("Deve retornar 400 se email for vazio")
    public void deverRetornar400SeEmailForVazio(){

    }

    @Test
    @DisplayName("Deve retornar 400 se nome for nulo")
    public void deverRetornar400SeNomeForNulo(){

    }

    @Test
    @DisplayName("Deve retornar 400 se nome for vazio")
    public void deverRetornar400SeNomeForVazio(){

    }

    @Test
    @DisplayName("Deve retornar 400 se descrição for nula")
    public void deverRetornar400SeDescricaoForNula(){

    }

}
