package livro.jogo.criarLivro.DAO;

import livro.jogo.criarLivro.entidades.Secao;
import javax.persistence.EntityManager;

public class SecaoDAO {

    private final EntityManager entidade;

    public SecaoDAO(EntityManager entidade) {
        this.entidade = entidade;
    }

    public void inserirRegistro(Secao secao){

        this.entidade.persist(secao);
    }

    public void atualizar(Secao secao){

        this.entidade.merge(secao);
    }

    public void remover(Secao secao){
        /*  É necessáriofazer isso porque preciso garantir que esteja no modo "Managed".
         Só que o comando merge ele cria uma cópia do objeto deixando-o em modo "Managed". No caso faço a atribuição
         ao objeto que veio como parâmetro para que ele fique neste modo que quero.*/
        secao = this.entidade.merge(secao);
        this.entidade.remove(secao);
    }
}
