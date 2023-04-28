package br.com.alura.loja.domain.produtos;

import br.com.alura.loja.domain.categorias.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;

public record listingProdutoDTO(
        String nome,
        String descricao,
        Categoria categoria,
        BigDecimal preco,
        LocalDate dataCadastro

) {
    public listingProdutoDTO(Produto produto) {
        this(produto.getNome(), produto.getDescricao(), produto.getCategoria(), produto.getPreco(), produto.getDataCadastro());
    }
}
