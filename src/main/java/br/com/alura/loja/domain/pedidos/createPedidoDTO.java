package br.com.alura.loja.domain.pedidos;


import java.util.ArrayList;
import br.com.alura.loja.domain.itens.itemPedidoDTO;

public record createPedidoDTO(
        Long idCliente,
        ArrayList<itemPedidoDTO> itens
) {
}
