package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.*;
import br.com.jornada.casadocodigo.domain.request.CarrinhoCompraRequest;
import br.com.jornada.casadocodigo.domain.request.CompraRequest;
import br.com.jornada.casadocodigo.domain.request.DadosClienteRequest;
import br.com.jornada.casadocodigo.domain.request.ItensCompraRequest;
import br.com.jornada.casadocodigo.repository.CompraRepository;
import br.com.jornada.casadocodigo.repository.CupomDescontoRepository;
import br.com.jornada.casadocodigo.repository.EstadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.mockito.Mockito.*;

class CompraResourceTest {

    private static DadosClienteRequest dadosClienteRequest;
    private static Pais pais;
    private static Autor autor;
    private static Categoria categoria;
    private static Livro livro1;
    private static Livro livro2;
    private static ItensCompraRequest itensCompraRequest1;
    private static ItensCompraRequest itensCompraRequest2;
    private static CarrinhoCompraRequest carrinhoCompraRequestSucesso;
    private static CarrinhoCompraRequest carrinhoCompraRequestComErro;
    private static Set<ItensCompraRequest> itensCompraRequest;
    private static CompraResource compraResource;

    private EntityManager manager = mock(EntityManager.class);
    private CompraRepository compraRepository = mock(CompraRepository.class);
    private EstadoRepository estadoRepository = mock(EstadoRepository.class);
    private CupomDescontoRepository cupomDescontoRepository = mock(CupomDescontoRepository.class);

    @BeforeEach
    void setup(){

        dadosClienteRequest = new DadosClienteRequest("comprador@gmail","Comprador","Fulano", "documento", "Rua qualuqer", "complemento","Cidade","1",null,"telefone", "cep");
        pais = new Pais();
        autor = new Autor();
        categoria = new Categoria();
        livro1 = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);
        livro2 = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);
        itensCompraRequest1 = new ItensCompraRequest("1", 2);
        itensCompraRequest2 = new ItensCompraRequest("2", 2);
        itensCompraRequest = Set.of(itensCompraRequest1, itensCompraRequest2);
        carrinhoCompraRequestSucesso = new CarrinhoCompraRequest(new BigDecimal(100),itensCompraRequest);
        carrinhoCompraRequestComErro = new CarrinhoCompraRequest(new BigDecimal(200),itensCompraRequest);

        compraResource = new CompraResource(manager,compraRepository,estadoRepository, cupomDescontoRepository);

    }

    @Test
    @DisplayName("Deve criar compra com sucesso")
    void deveCriarCompraComSucessoComStatus_201(){
        CompraRequest compraRequest = new CompraRequest(dadosClienteRequest, carrinhoCompraRequestSucesso, null);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        when(manager.find(Pais.class, "1")).thenReturn(pais);
        when(manager.find(Livro.class,"1")).thenReturn(livro1);
        when(manager.find(Livro.class,"2")).thenReturn(livro2);

        CompraResource compraResource = new CompraResource(manager,compraRepository,estadoRepository, cupomDescontoRepository);

        ResponseEntity<?> responseEntity = compraResource.compra(compraRequest, uriComponentsBuilder);

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        verify(compraRepository,times(1)).save(any(Compra.class));
    }

    @Test
    @DisplayName("Não deve criar compra com sucesso")
    void deveRetornarErroAoTentarCriarCompra(){
        CompraRequest compraRequest = new CompraRequest(dadosClienteRequest, carrinhoCompraRequestComErro, null);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        when(manager.find(Pais.class, "1")).thenReturn(pais);
        when(manager.find(Livro.class,"1")).thenReturn(livro1);
        when(manager.find(Livro.class,"2")).thenReturn(livro2);

        CompraResource compraResource = new CompraResource(manager,compraRepository,estadoRepository, cupomDescontoRepository);

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> compraResource.compra(compraRequest, uriComponentsBuilder));
        Assertions.assertEquals("Valor total esta diferente do total real do carrinho!!",illegalArgumentException.getMessage());
    }

}