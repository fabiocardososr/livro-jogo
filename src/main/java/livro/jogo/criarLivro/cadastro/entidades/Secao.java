package livro.jogo.criarLivro.cadastro.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "secao")
public class Secao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSecao;

    @ManyToOne
    @JoinColumn(name="idLivro")
    private Livro livro;
    private String texto;
    private Integer codSecaoLivro;
    private String enderecoImagem;

    public Secao(Livro livro, String texto, Integer codSessaoLivro, String enderecoImagem) {
        this.livro = livro;
        this.texto = texto;
        this.codSecaoLivro = codSessaoLivro;
        this.enderecoImagem = enderecoImagem;
    }

    public int getIdSecao() {
        return idSecao;
    }

    public Livro getLivro() {
        return livro;
    }

    public String getTexto() {
        return texto;
    }

    public Integer getCodSecaoLivro() {
        return codSecaoLivro;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    @Override
    public String toString() {
        return "Secao{" +
                "idSecao=" + idSecao +
                ", Livro=" + livro.getDescricao() +
                ", texto='" + texto + '\'' +
                ", codSessaoLivro=" + codSecaoLivro +
                ", enderecoImagem='" + enderecoImagem + '\'' +
                '}';
    }
}
