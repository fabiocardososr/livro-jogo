package livro.jogo.criarLivro.cadastro.utils;

import livro.jogo.criarLivro.cadastro.DAO.*;
import livro.jogo.criarLivro.cadastro.entidades.*;

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
        else if (classe instanceof TipoEfeito) {
            TipoEfeitoDAO tipoEfeitoDAO = new TipoEfeitoDAO(entity);
            tipoEfeitoDAO.inserirRegistro((TipoEfeito) classe);
        }
        else if (classe instanceof Item) {
            ItemDAO itemDAO = new ItemDAO(entity);
            itemDAO.inserirRegistro((Item) classe);
        }
        else if (classe instanceof SecaoItens) {
            SecaoItensDAO secaoItensDAO = new SecaoItensDAO(entity);
            secaoItensDAO.inserirRegistro((SecaoItens) classe);
        }

        //Comitar
        entity.getTransaction().commit();
        entity.close();
    }

    //Feito para limpar a base enquanto vou criando o código para inserção dos registros
    public static void apagarTudo(){

        //Iniciar transação
        EntityManager entity  = JPAUtil.getEntityManager();
        entity.getTransaction().begin();

        new SecaoItensDAO(entity).apagarTodos();
        new ProximaSecaoDAO(entity).apagarTodos();
        new SecaoDAO(entity).apagarTodos();
        new ItemDAO(entity).apagarTodos();
        new LivroDAO(entity).apagarTodos();
        new TipoEfeitoDAO(entity).apagarTodos();

        //Comitar
        entity.getTransaction().commit();
        entity.close();
    }
}
