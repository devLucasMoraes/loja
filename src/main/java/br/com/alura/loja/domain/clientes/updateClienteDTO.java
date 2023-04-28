package br.com.alura.loja.domain.clientes;

import jakarta.validation.constraints.NotNull;

public record updateClienteDTO(@NotNull Long id, String nome, String cpf) {
}
