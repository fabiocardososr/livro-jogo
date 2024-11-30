package livro.jogo.criarLivro.cadastro.DAO;

import livro.jogo.criarLivro.cadastro.entidades.Livro;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Livro> buscarTodos(){
        String jpql = "delete livro from Livro livro";
        return this.entidade.createQuery(jpql,Livro.class).getResultList();
    }

    public List<Livro> buscarPorNome(String nome){
        //String jpql = "select p from Produto p where p.nome like ?1"; //tem essa maneira de passar parÂmetro sem ser por nome coloca "?" e "1" onde 1 é a posição da variável
        //.setParameter(1, "%"+nome+"%")
        String jpql = "select l from livro l where l.nome like :nome";
        return this.entidade.createQuery(jpql,Livro.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
    }

    public void apagarTodos(){
        String jpql = "delete from Livro";
        this.entidade.createQuery(jpql).executeUpdate();
    }
}
