package livro.jogo.criarLivro.utils;

import livro.jogo.criarLivro.DAO.LivroDAO;
import livro.jogo.criarLivro.DAO.ProximaSecaoDAO;
import livro.jogo.criarLivro.DAO.SecaoDAO;
import livro.jogo.criarLivro.entidades.Livro;
import livro.jogo.criarLivro.entidades.ProximaSecao;
import livro.jogo.criarLivro.entidades.Secao;

import javax.persistence.EntityManager;

public class InserirNoBd {

    public static <T> void gravarNoBd(T classe){
        EntityManager entity  = JPAUtil.getEntityManager();
        entity.getTransaction().begin();

        if (classe instanceof Livro) {
            LivroDAO livroDAO = new LivroDAO(entity);
            livroDAO.inserirRegistro((Livro) classe);
        }
        else if (classe instanceof Secao) {
            SecaoDAO secaoDAO = new SecaoDAO(entity);
            secaoDAO.inserirRegistro((Secao) classe);
        }
        else if (classe instanceof ProximaSecao) {
            ProximaSecaoDAO proximaSecaoDAO = new ProximaSecaoDAO(entity);
            proximaSecaoDAO.inserirRegistro((ProximaSecao) classe);
        }


        //Comitar
        entity.getTransaction().commit();
        entity.close();
    }

    //Feito para limpar a base enquanto vou criando o código para inserção dos registros
    public static void apagarTudo(){
        EntityManager entity  = JPAUtil.getEntityManager();
        entity.getTransaction().begin();
        ProximaSecaoDAO proximaSecaoDAO = new ProximaSecaoDAO(entity);
        SecaoDAO secaoDAO               = new SecaoDAO(entity);
        LivroDAO livroDAO               = new LivroDAO(entity);

        proximaSecaoDAO.apagarTodos();
        secaoDAO.apagarTodos();
        livroDAO.apagarTodos();

        //Comitar
        entity.getTransaction().commit();
        entity.close();
    }
}
