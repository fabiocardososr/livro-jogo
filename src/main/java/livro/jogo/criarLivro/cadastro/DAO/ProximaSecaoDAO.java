package livro.jogo.criarLivro.cadastro.DAO;

import livro.jogo.criarLivro.cadastro.entidades.ProximaSecao;
import javax.persistence.EntityManager;

public class ProximaSecaoDAO {
    private final EntityManager entidade;

    public ProximaSecaoDAO(EntityManager entidade) {
        this.entidade = entidade;
    }

    public void inserirRegistro(ProximaSecao proximaSecao){

        this.entidade.persist(proximaSecao);
    }

    public void atualizar(ProximaSecao proximaSecao){

        this.entidade.merge(proximaSecao);
    }

    public void remover(ProximaSecao proximaSecao){
        /*  É necessáriofazer isso porque preciso garantir que esteja no modo "Managed".
         Só que o comando merge ele cria uma cópia do objeto deixando-o em modo "Managed". No caso faço a atribuição
         ao objeto que veio como parâmetro para que ele fique neste modo que quero.*/
        proximaSecao = this.entidade.merge(proximaSecao);
        this.entidade.remove(proximaSecao);
    }

    public void apagarTodos(){
        String jpql = "delete from ProximaSecao";
        this.entidade.createQuery(jpql).executeUpdate();
    }
}
