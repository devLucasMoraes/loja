package br.com.alura.loja.domain.produtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record updateProdutoDTO(
        @NotNull
        Long id,
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
