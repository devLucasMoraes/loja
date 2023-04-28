package br.com.alura.loja.domain.produtos;

import br.com.alura.loja.domain.categorias.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record createProdutoDTO(
        @NotBlank
        String nome,
        String descricao,
        @NotNull
        BigDecimal preco,

        LocalDate dataCadastro,
        @NotNull
        Long idCategoria
) {
}
