package br.com.alura.loja.domain.categorias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Categoria")
@Table(name = "categorias")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Boolean ativo;

    public Categoria(createCategoriaDTO dados) {
        this.nome = dados.nome();
        this.ativo = true;
    }

    public void update(updateCategoriaDTO dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
    }

    public void delete() {
        this.ativo = false;
    }
}
