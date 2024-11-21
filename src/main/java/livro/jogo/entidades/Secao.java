package livro.jogo.entidades;

import javax.persistence.*;

@Entity
@Table(name = "Secao")
public class Secao {
    @Id //Infoma que o atributo "id" é a chave primaria que vem da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSecao;
    private int idLivro;
    private String texto;
    private Integer codSessaoLivro;
    String enderecoImagem;

    public Secao(int idLivro, String texto, Integer codSessaoLivro, String enderecoImagem) {
        this.idLivro = idLivro;
        this.texto = texto;
        this.codSessaoLivro = codSessaoLivro;
        this.enderecoImagem = enderecoImagem;
    }

    @Override
    public String toString() {
        return "Secao{" +
                "idSecao=" + idSecao +
                ", idLivro=" + idLivro +
                ", texto='" + texto + '\'' +
                ", codSessaoLivro=" + codSessaoLivro +
                ", enderecoImagem='" + enderecoImagem + '\'' +
                '}';
    }
}
