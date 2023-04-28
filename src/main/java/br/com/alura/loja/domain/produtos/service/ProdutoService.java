package br.com.alura.loja.domain.produtos.service;

import br.com.alura.loja.domain.categorias.CategoriaRepository;
import br.com.alura.loja.domain.produtos.*;
import br.com.alura.loja.exeptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    public detailsProdutoDTO crate(createProdutoDTO dados) {
        if(!categoriaRepository.existsById(dados.idCategoria())) {
            System.out.println("passou por aqui");
            throw new ValidacaoException("Id da categoria informada não existe");
        }

        var categoria = categoriaRepository.getReferenceById(dados.idCategoria());
        System.out.println(categoria);
        var produto = new Produto(null, dados.nome(), dados.descricao(), dados.preco(), dados.dataCadastro(), categoria);
        produtoRepository.save(produto);

        return new detailsProdutoDTO(produto);
    }

    public Page<listingProdutoDTO> read(Pageable paginacao) {
        return produtoRepository.findByAtivoTrue(paginacao).map(listingProdutoDTO::new);
    }


    public detailsProdutoDTO update(updateProdutoDTO dados) {
        var produto = produtoRepository.getReferenceById(dados.id());
        var categoria = categoriaRepository.getReferenceById((dados.idCategoria()));
        produto.update(dados, categoria);
        return new detailsProdutoDTO(produto);
    }

    public void delete(Long id) {
        var produto = produtoRepository.getReferenceById(id);
        produto.delete();
    }

    public detailsProdutoDTO details(Long id) {
        var produto = produtoRepository.getReferenceById(id);
        return new detailsProdutoDTO(produto);
    }
}
