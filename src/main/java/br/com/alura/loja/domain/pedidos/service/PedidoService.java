package br.com.alura.loja.domain.pedidos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.alura.loja.domain.pedidos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.loja.domain.clientes.ClienteRepository;
import br.com.alura.loja.domain.itens.ItemPedido;
import br.com.alura.loja.domain.produtos.ProdutoRepository;
import br.com.alura.loja.exeptions.ValidacaoException;

@Service
public class PedidoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    public detailsPedidoDTO create(createPedidoDTO dados) {
        if (!clienteRepository.existsById(dados.idCliente())) {
            throw new ValidacaoException("Id do cliente informado não existe");
        }

        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var pedido = new Pedido(null, LocalDate.now(), new BigDecimal(0), cliente, new ArrayList<>());


        for (var item : dados.itens()) {
            var produto = produtoRepository.getReferenceById(item.idProduto());

            if (!produto.getAtivo()) {
                throw new ValidacaoException("Produto " + produto.getNome() + " não está ativo.");
            }

            var itemPedido = new ItemPedido(produto, item.quantidade());
            itemPedido.setPedido(pedido);
            pedido.adicionarItem(itemPedido);

        }

        pedidoRepository.save(pedido);
        return new detailsPedidoDTO(pedido);
    }

    public Page<listingPedidoDTO> read(Pageable pageable) {
        return pedidoRepository.findAll(pageable).map(listingPedidoDTO::new);
    }
}
