package livro.jogo.criarLivro.cadastro.entidades;

import javax.persistence.*;

@Entity
public class SecaoItens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdSecaoItem;

    @ManyToOne
    @JoinColumn(name="idSecao")
    private Secao secao;

    @ManyToOne
    @JoinColumn(name="idItem")
    private Item item;

    private int quantidade;

    public SecaoItens(Secao secao, Item item, int quantidade) {
        this.secao = secao;
        this.item = item;
        this.quantidade = quantidade;
    }
}
