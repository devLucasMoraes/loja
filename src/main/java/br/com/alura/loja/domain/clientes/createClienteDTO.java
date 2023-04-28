package br.com.alura.loja.domain.clientes;

import jakarta.validation.constraints.NotBlank;

public record createClienteDTO(
        @NotBlank
        String nome,
        @NotBlank
        String cpf
) {
}
