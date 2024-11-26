package livro.jogo.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Secao")
public class Secao {
    @Id //Infoma que o atributo "id" é a chave primaria que vem da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idLivro;
    private String texto;
    private Integer codSecaoLivro;
    private String enderecoImagem;

    public Secao(int idLivro, String texto, Integer codSessaoLivro, String enderecoImagem) {
        this.idLivro = idLivro;
        this.texto = texto;
        this.codSecaoLivro = codSessaoLivro;
        this.enderecoImagem = enderecoImagem;
    }

    @Override
    public String toString() {
        return "Secao{" +
                "idSecao=" + id +
                ", idLivro=" + idLivro +
                ", texto='" + texto + '\'' +
                ", codSessaoLivro=" + codSecaoLivro +
                ", enderecoImagem='" + enderecoImagem + '\'' +
                '}';
    }
}
