package br.com.alura.loja.domain.produtos;

import br.com.alura.loja.domain.categorias.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Produto")
@Table(name = "produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDate dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private Boolean ativo;

    public Produto(Long id, String nome, String descricao, BigDecimal preco, LocalDate dataCadastro, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.dataCadastro = dataCadastro;
        this.categoria = categoria;
        this.ativo = true;
    }

    public void update(updateProdutoDTO dados, Categoria categoria) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.dataCadastro() != null) {
            this.dataCadastro = dados.dataCadastro();
        }
        if(dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if(dados.preco() != null) {
            this.preco = dados.preco();
        }
        if(dados.idCategoria() != null) {
            this.categoria = categoria;
        }
    }

    public void delete() {
        this.ativo = false;
    }

}
