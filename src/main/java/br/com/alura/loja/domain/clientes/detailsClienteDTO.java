package br.com.alura.loja.domain.clientes;

public record detailsClienteDTO(
        Long id,
        String nome,
        String cpf
) {
    public detailsClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf());
    }
}
