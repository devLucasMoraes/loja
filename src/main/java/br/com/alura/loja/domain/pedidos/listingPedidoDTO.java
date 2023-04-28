package br.com.alura.loja.domain.pedidos;

import br.com.alura.loja.domain.clientes.Cliente;
import br.com.alura.loja.domain.itens.ItemPedido;
import br.com.alura.loja.domain.itens.itemPedidoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record listingPedidoDTO(
        LocalDate data,
        BigDecimal valorTotal,
        Cliente cliente,
        List<itemPedidoDTO> itens
) {
    public listingPedidoDTO(Pedido pedido) {
        this(pedido.getData(), pedido.getValorTotal(), pedido.getCliente(), toDTO(pedido.getItens()));
    }
    private static List<itemPedidoDTO> toDTO(List<ItemPedido> itens) {
        return itens.stream().map(item -> new itemPedidoDTO(item.getProduto().getId(), item.getQuantidade())).collect(Collectors.toList());
    }

}
