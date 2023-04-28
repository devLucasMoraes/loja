package br.com.alura.loja.domain.produtos;

import br.com.alura.loja.domain.categorias.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;

public record detailsProdutoDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        LocalDate dataCadastro,
        Categoria categoria,
        Boolean ativo
) {
    public detailsProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getDataCadastro(), produto.getCategoria(), produto.getAtivo());
    }
}
