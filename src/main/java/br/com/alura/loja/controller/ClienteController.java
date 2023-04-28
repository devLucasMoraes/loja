package br.com.alura.loja.controller;


import br.com.alura.loja.domain.clientes.*;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create (@RequestBody @Valid createClienteDTO dados, UriComponentsBuilder componentsBuilder) {
        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);
        var uri = componentsBuilder.path("/categorias/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new detailsClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<listingClienteDTO>> read (@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao) {
        var page = clienteRepository.findByAtivoTrue(paginacao).map(listingClienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid updateClienteDTO dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.update(dados);
        return ResponseEntity.ok(new detailsClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        cliente.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public  ResponseEntity details (@PathVariable Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new detailsClienteDTO(cliente));
    }



}
