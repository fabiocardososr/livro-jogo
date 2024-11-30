package livro.jogo.criarLivro.cadastro.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoefeito")
public class TipoEfeito {
    @Id
    private int idTipoEfeito;
    private String descricao;

    public TipoEfeito(int idTipoEfeito, String descricao) {
        this.idTipoEfeito = idTipoEfeito;
        this.descricao = descricao;
    }

    public int getIdTipoEfeito() {
        return idTipoEfeito;
    }

    public String getDescricao() {
        return descricao;
    }
}


