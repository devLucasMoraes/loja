package br.com.alura.loja.controller;

import br.com.alura.loja.domain.pedidos.createPedidoDTO;
import br.com.alura.loja.domain.pedidos.listingPedidoDTO;
import br.com.alura.loja.domain.pedidos.service.PedidoService;
import br.com.alura.loja.domain.produtos.createProdutoDTO;
import br.com.alura.loja.domain.produtos.listingProdutoDTO;
import br.com.alura.loja.domain.produtos.service.ProdutoService;
import br.com.alura.loja.domain.produtos.updateProdutoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Transactional
    public ResponseEntity create (@RequestBody @Valid createPedidoDTO dados, UriComponentsBuilder componentsBuilder) {
        var dto = pedidoService.create(dados);
        var uri = componentsBuilder.path("/pedidos/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<listingPedidoDTO>> read (Pageable pageable){
        var page = pedidoService.read(pageable);
        return ResponseEntity.ok(page);
    }


}
