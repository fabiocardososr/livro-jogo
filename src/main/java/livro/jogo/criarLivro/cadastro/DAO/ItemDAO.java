package livro.jogo.criarLivro.cadastro.DAO;

import livro.jogo.criarLivro.cadastro.entidades.Item;
import javax.persistence.EntityManager;

public class ItemDAO {
    private final EntityManager entidade;

    public ItemDAO(EntityManager entidade) {
        this.entidade = entidade;
    }

    public void inserirRegistro(Item item){

        this.entidade.persist(item);
    }

    public void atualizar(Item item){

        this.entidade.merge(item);
    }

    public void remover(Item item){
        /*  É necessáriofazer isso porque preciso garantir que esteja no modo "Managed".
         Só que o comando merge ele cria uma cópia do objeto deixando-o em modo "Managed". No caso faço a atribuição
         ao objeto que veio como parâmetro para que ele fique neste modo que quero.*/
        item = this.entidade.merge(item);
        this.entidade.remove(item);
    }

    public void apagarTodos(){
        String jpql = "delete from Item";
        this.entidade.createQuery(jpql).executeUpdate();
    }
}
