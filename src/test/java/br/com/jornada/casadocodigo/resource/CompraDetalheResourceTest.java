package br.com.jornada.casadocodigo.resource;

import br.com.jornada.casadocodigo.domain.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompraDetalheResourceTest {

    @Test
    @DisplayName("Deve retornar os detalhes da compra com sucesso. Status 200")
    void deveCriarDetalheDaCompraComSucesso(){
        EntityManager manager = mock(EntityManager.class);

        CompraDetalheResource compraDetalheResource = new CompraDetalheResource(manager);
        DadosCliente dadosCliente = new DadosCliente("comprador@gmail","Comprador","Fulano", "documento", "Rua qualuqer", "complemento","Cidade",new Pais("Brasil"),"telefone", "cep");
        Autor autor = new Autor();
        Categoria categoria = new Categoria();
        Livro livro1 = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);
        Livro livro2 = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);
        ItensCompra itensCompra1 = new ItensCompra(livro1,2);
        ItensCompra itensCompra2 = new ItensCompra(livro2,2);
        Set<ItensCompra> itensCompras = Set.of(itensCompra1, itensCompra2);
        CarrinhoCompra carrinhoCompra = new CarrinhoCompra(new BigDecimal(100),itensCompras);
        Compra compra = new Compra(dadosCliente,carrinhoCompra );

        when(manager.find(Compra.class, "1")).thenReturn(compra);
        ResponseEntity<?> responseEntity = compraDetalheResource.compraDetalhe("1");

        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());

    }

    @Test
    @DisplayName("Deve retornar os detalhes da compra com sucesso. Status 200")
    void deveRetornarErroComValorTotalDiferente(){
        EntityManager manager = mock(EntityManager.class);

        CompraDetalheResource compraDetalheResource = new CompraDetalheResource(manager);
        DadosCliente dadosCliente = new DadosCliente("comprador@gmail","Comprador","Fulano", "documento", "Rua qualuqer", "complemento","Cidade",new Pais("Brasil"),"telefone", "cep");
        Autor autor = new Autor();
        Categoria categoria = new Categoria();
        Livro livro1 = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);
        Livro livro2 = new Livro("Livro","Resumo do livro","Sumario do Livro",new BigDecimal(25),100,"abcdefg", LocalDate.of(2020,11,8),categoria,autor);
        ItensCompra itensCompra1 = new ItensCompra(livro1,2);
        ItensCompra itensCompra2 = new ItensCompra(livro2,2);
        Set<ItensCompra> itensCompras = Set.of(itensCompra1, itensCompra2);
        CarrinhoCompra carrinhoCompra = new CarrinhoCompra(new BigDecimal(100),itensCompras);
        Compra compra = new Compra(dadosCliente,carrinhoCompra );

        when(manager.find(Compra.class, "1")).thenReturn(null);

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> compraDetalheResource.compraDetalhe("1"));

        Assertions.assertEquals("Compra não encontrada com o id: ".concat("1"), illegalArgumentException.getMessage());

    }


}