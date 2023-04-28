package br.com.alura.loja.controller;

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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Transactional
    public ResponseEntity create (@RequestBody @Valid createProdutoDTO dados, UriComponentsBuilder componentsBuilder) {
        var dto = produtoService.crate(dados);
        var uri = componentsBuilder.path("/produtos/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<listingProdutoDTO>> read (@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao) {
        var page = produtoService.read(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid updateProdutoDTO dados) {
        var dto = produtoService.update(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public  ResponseEntity details (@PathVariable Long id) {
        var dto = produtoService.details(id);
        return ResponseEntity.ok(dto);
    }



}
