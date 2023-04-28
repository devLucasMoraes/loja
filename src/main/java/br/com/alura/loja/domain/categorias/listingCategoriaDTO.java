package br.com.alura.loja.domain.categorias;

public record listingCategoriaDTO(Long id, String nome) {
    public listingCategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome());
    }
}
