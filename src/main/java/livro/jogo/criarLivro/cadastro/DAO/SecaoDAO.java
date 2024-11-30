package livro.jogo.criarLivro.cadastro.DAO;

import livro.jogo.criarLivro.cadastro.entidades.Secao;
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

    public void apagarTodos(){
        String jpql = "delete from Secao";
        this.entidade.createQuery(jpql).executeUpdate();
    }
}
