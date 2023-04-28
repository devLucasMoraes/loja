package br.com.alura.loja.domain.categorias;

import jakarta.validation.constraints.NotNull;

public record updateCategoriaDTO(@NotNull Long id, String nome) {
}
