package livro.jogo.entidades;

import javax.persistence.*;

@Entity
@Table(name = "livro")
public class Livro {
    @Id //Infoma que o atributo "id" é a chave primaria que vem da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLivro;

    private String nome;
    private String descricao;
    private int secaoInicial;

    //Hibernate exige para consultas
    public Livro() {
    }

    public Livro(String nome, String descricao, int secaoInicial) {
        this.nome = nome;
        this.descricao = descricao;
        this.secaoInicial = secaoInicial;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getSecaoInicial() {
        return secaoInicial;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "idLivro=" + idLivro +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", secaoInicial=" + secaoInicial +
                '}';
    }
}
