package br.com.alura.loja.controller;

import br.com.alura.loja.domain.categorias.*;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create (@RequestBody @Valid createCategoriaDTO dados, UriComponentsBuilder componentsBuilder) {
        var categoria = new Categoria(dados);
        categoriaRepository.save(categoria);
        var uri = componentsBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new detailsCategoriaDTO(categoria));
    }

    @GetMapping
    public ResponseEntity<Page<listingCategoriaDTO>> read (@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao) {
        var page = categoriaRepository.findByAtivoTrue(paginacao).map(listingCategoriaDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid updateCategoriaDTO dados) {
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.update(dados);
        return ResponseEntity.ok(new detailsCategoriaDTO(categoria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        categoria.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public  ResponseEntity details (@PathVariable Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        return ResponseEntity.ok(new detailsCategoriaDTO(categoria));
    }



}
