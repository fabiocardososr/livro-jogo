package livro.jogo.cadastros;

import livro.jogo.DAO.LivroDAO;
import livro.jogo.utils.JPAUtil;
import livro.jogo.utils.ManipularArquivos;

import javax.persistence.EntityManager;

public class CadastrarLivroFlorestaDaDestruicao {
    private final EntityManager entity = JPAUtil.getEntityManager(); //Entidade de persistência
    private final LivroDAO livroDao = new LivroDAO(entity); //Entidade de persistência
    private final ManipularArquivos manipularArquivos = new ManipularArquivos();

    public void carregarLivro(){

        //Carregar Livro
        tabelaLivro(manipularArquivos);

    }

    public void tabelaLivro(ManipularArquivos manipularArquivos){

        //Descrição do livro
        StringBuilder descricaoLivro = manipularArquivos.lerTexto("textos/descricaoLivro.liv");
        System.out.println("\n\nDESCRIÇÃO DO LIVRO: \n\n"+descricaoLivro);

        //Descrição dos índices de HABILIDADE, ENERGIA E SORTE
        StringBuilder regraCalculoIndicesIniciais = manipularArquivos.lerTexto("textos/descricaoLivro.liv");
        System.out.println("\n\nREGRA DE CÁLCULO DOS ÍNDICES DE HABILIDADE, ENERGIA E SORTE: \n\n"+regraCalculoIndicesIniciais);

        //Regras de como é feita a batalha
        StringBuilder regraLuta = manipularArquivos.lerTexto("textos/regraLuta.liv");
        System.out.println("\n\nREGRAS DE BATALHA: \n\n"+regraLuta);

        //Regras de como é feita a batalha
        StringBuilder regraUsoSorte = manipularArquivos.lerTexto("textos/regraUsoSorte.liv");
        System.out.println("\n\nREGRAS DE USO DA SORTE: \n\n"+regraUsoSorte);

        //Regras reposicao dos índices HABILIDADE, ENERGIA E SORTE
        StringBuilder regraReposicaoIndice = manipularArquivos.lerTexto("textos/regraReposicaoIndice.liv");
        System.out.println("\n\nREGRAS DE USO DA SORTE: \n\n"+regraReposicaoIndice);

        //Regras de equipamentos
        StringBuilder regraEquipamentos = manipularArquivos.lerTexto("textos/regraEquipamentos.liv");
        System.out.println("\n\nREGRAS DE EQUIPAMENTOS: \n\n"+regraEquipamentos);

        //Regras de equipamentos
        StringBuilder dica = manipularArquivos.lerTexto("textos/dica.liv");
        System.out.println("\n\n\nDICAS: \n\n"+dica);


    }


}