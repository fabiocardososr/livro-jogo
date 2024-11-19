package livro.jogo.DAO;

import livro.jogo.entidades.Livro;

import javax.persistence.EntityManager;

public class LivroDAO {
    private final EntityManager entidade;

    public LivroDAO(EntityManager entidade) {
        this.entidade = entidade;
    }

    public void inserirRegistro(Livro livro){

        this.entidade.persist(livro);
    }

    public void atualizar(Livro livro){

        this.entidade.merge(livro);
    }

    public void remover(Livro livro){
        /*  É necessáriofazer isso porque preciso garantir que esteja no modo "Managed".
         Só que o comando merge ele cria uma cópia do objeto deixando-o em modo "Managed". No caso faço a atribuição
         ao objeto que veio como parâmetro para que ele fique neste modo que quero.*/
        livro = this.entidade.merge(livro);
        this.entidade.remove(livro);
    }
}
