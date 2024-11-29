package livro.jogo.criarLivro.DAO;

import livro.jogo.criarLivro.entidades.TipoEfeito;
import javax.persistence.EntityManager;

public class TipoEfeitoDAO {
    private final EntityManager entidade;

    public TipoEfeitoDAO(EntityManager entidade) {
        this.entidade = entidade;
    }

    public void inserirRegistro(TipoEfeito tipoEfeito){

        this.entidade.persist(tipoEfeito);
    }

    public void atualizar(TipoEfeito tipoEfeito){

        this.entidade.merge(tipoEfeito);
    }

    public void remover(TipoEfeito tipoEfeito){
        /*  É necessáriofazer isso porque preciso garantir que esteja no modo "Managed".
         Só que o comando merge ele cria uma cópia do objeto deixando-o em modo "Managed". No caso faço a atribuição
         ao objeto que veio como parâmetro para que ele fique neste modo que quero.*/
        tipoEfeito = this.entidade.merge(tipoEfeito);
        this.entidade.remove(tipoEfeito);
    }

    public void apagarTodos(){
        String jpql = "delete from TipoEfeito";
        this.entidade.createQuery(jpql).executeUpdate();
    }

}
