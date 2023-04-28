package br.com.alura.loja.domain.categorias;

public record detailsCategoriaDTO(
    Long id,
    String nome,
    Boolean ativo
) {
    public detailsCategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getAtivo());
    }
}
