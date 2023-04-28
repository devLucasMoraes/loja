package br.com.alura.loja.domain.pedidos;

import br.com.alura.loja.domain.itens.ItemPedido;
import br.com.alura.loja.domain.itens.itemPedidoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record detailsPedidoDTO(
        Long id,
        Long idCliente,
        BigDecimal valorTotal,
        LocalDate dataPedido,
        List<itemPedidoDTO> itens

) {
    public detailsPedidoDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getCliente().getId(), pedido.getValorTotal(), pedido.getData(), toDTO(pedido.getItens()));
    }

    private static List<itemPedidoDTO> toDTO(List<ItemPedido> itens) {
        return itens.stream().map(item -> new itemPedidoDTO(item.getProduto().getId(), item.getQuantidade())).collect(Collectors.toList());
    }
}
