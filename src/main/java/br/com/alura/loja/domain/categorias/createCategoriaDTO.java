package br.com.alura.loja.domain.categorias;

import jakarta.validation.constraints.NotBlank;

public record createCategoriaDTO(
        @NotBlank
        String nome
) {
}
