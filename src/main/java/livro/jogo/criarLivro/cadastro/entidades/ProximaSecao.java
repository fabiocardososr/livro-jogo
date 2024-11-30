package livro.jogo.criarLivro.cadastro.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="proximasecao")
public class ProximaSecao {

    @Id //Infoma que o atributo "id" é a chave primaria que vem da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProximaSecao;

    @ManyToOne
    @JoinColumn(name="idSecao")
    private Secao secao;

    private int codProximaSecao;
    private String textoOpcao; //Aqui descreve a opção para ir a outra página

    public ProximaSecao(Secao secao, int codProximaSecao, String textoOpcao) {
        this.secao              = secao;
        this.codProximaSecao    = codProximaSecao;
        this.textoOpcao         = textoOpcao;
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
                "id=" + idProximaSecao +
                ", idSecao=" + secao.toString() +
                ", codProximaSecao=" + codProximaSecao +
                ", textoOpcao='" + textoOpcao + '\'' +
                '}';
    }
}
