package livro.jogo.entidades;

import javax.persistence.*;

@Entity
@Table(name="ProximaSecao")
public class ProximaSecao {
    @Id //Infoma que o atributo "id" é a chave primaria que vem da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idLivro;
    @ManyToOne
    @JoinColumn(name="idSecao")
    private Secao secao;
    private int codProximaSecao;
    private String textoOpcao; //Aqui descreve a opção para ir a outra página

    public ProximaSecao(int idLivro, Secao secao, int codProximaSecao, String textoOpcao) {
        this.idLivro            = idLivro;
        this.secao              = secao;
        this.codProximaSecao    = codProximaSecao;
        this.textoOpcao         = textoOpcao;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public Secao getSecao() {
        return secao;
    }

    public int getCodProximaSecao() {
        return codProximaSecao;
    }

    public String getTextoOpcao() {
        return textoOpcao;
    }

    @Override
    public String toString() {
        return "ProximaSecao{" +
                "id=" + id +
                ", idLivro=" + idLivro +
                ", idSecao=" + secao.toString() +
                ", codProximaSecao=" + codProximaSecao +
                ", textoOpcao='" + textoOpcao + '\'' +
                '}';
    }
}
