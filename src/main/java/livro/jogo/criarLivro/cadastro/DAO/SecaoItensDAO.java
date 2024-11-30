package livro.jogo.criarLivro.cadastro.DAO;

import livro.jogo.criarLivro.cadastro.entidades.SecaoItens;
import javax.persistence.EntityManager;

public class SecaoItensDAO {
    private final EntityManager entidade;

    public SecaoItensDAO(EntityManager entidade) {
        this.entidade = entidade;
    }

    public void inserirRegistro(SecaoItens secaoItens){

        this.entidade.persist(secaoItens);
    }

    public void atualizar(SecaoItens secaoItens){

        this.entidade.merge(secaoItens);
    }

    public void remover(SecaoItens secaoItens){
        /*  É necessário fazer isso porque preciso garantir que esteja no modo "Managed".
         Só que o comando merge ele cria uma cópia do objeto deixando-o em modo "Managed". No caso faço a atribuição
         ao objeto que veio como parâmetro para que ele fique neste modo que quero.*/
        secaoItens = this.entidade.merge(secaoItens);
        this.entidade.remove(secaoItens);
    }

    public void apagarTodos(){
        String jpql = "delete from SecaoItens";
        this.entidade.createQuery(jpql).executeUpdate();
    }
}
