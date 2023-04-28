package br.com.alura.loja.domain.clientes;

import br.com.alura.loja.domain.clientes.Cliente;

public record listingClienteDTO(
        String nome,
        String cpf
) {
    public listingClienteDTO(Cliente cliente) {
        this(cliente.getNome(), cliente.getCpf());
    }
}
