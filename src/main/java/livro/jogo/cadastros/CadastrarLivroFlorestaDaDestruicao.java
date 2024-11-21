package livro.jogo.cadastros;

import livro.jogo.DAO.LivroDAO;
import livro.jogo.entidades.Livro;
import livro.jogo.entidades.Secao;
import livro.jogo.utils.JPAUtil;
import livro.jogo.utils.ManipularArquivos;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CadastrarLivroFlorestaDaDestruicao {
    private final EntityManager ENTITY = JPAUtil.getEntityManager(); //Entidade de persistência.
    private final LivroDAO LIVRODAO = new LivroDAO(ENTITY); //Entidade de persistência
    private final ManipularArquivos MANIPULARARQUIVOS = new ManipularArquivos();
    private final String NOMELIVO = "Floresta da Destruição (Darkwood)";
    private Livro livro;
    List listaSecoes;

    public void carregarLivro(){

        //Carregar Livro
        tabelaLivro(MANIPULARARQUIVOS);

        //carregar secoes
        this.listaSecoes = tabelaSecao(MANIPULARARQUIVOS);
    }

    public void tabelaLivro(ManipularArquivos manipularArquivos){

        //Descrição do livro
        StringBuilder descricaoLivro = manipularArquivos.lerTexto("textosflorestaestruicao/descricaoLivro.liv");

        //Descrição dos índices de HABILIDADE, ENERGIA E SORTE
        StringBuilder regraCalculoIndicesIniciais = manipularArquivos.lerTexto("textosflorestaestruicao/regraCalcularIndicesIniciais.liv");

        //Regras de como é feita a batalha
        StringBuilder regraLuta = manipularArquivos.lerTexto("textosflorestaestruicao/regraLuta.liv");

        //Regras de REGRAS DE USO DA SORTE
        StringBuilder regraUsoSorte = manipularArquivos.lerTexto("textosflorestaestruicao/regraUsoSorte.liv");


        //Regras reposicao dos índices HABILIDADE, ENERGIA E SORTE
        StringBuilder regraReposicaoIndice = manipularArquivos.lerTexto("textosflorestaestruicao/regraReposicaoIndice.liv");


        //Regras de equipamentos
        StringBuilder regraEquipamentos = manipularArquivos.lerTexto("textosflorestaestruicao/regraEquipamentos.liv");


        //Regras de equipamentos
        StringBuilder dica = manipularArquivos.lerTexto("textosflorestaestruicao/dica.liv");


        //Regras início da história
        StringBuilder historia = manipularArquivos.lerTexto("textosflorestaestruicao/historia.liv");

        int idLivro = 1; //O campo id não vai ser autoincrement, vou declarar para cada livro.
        this.livro = new Livro(1,this.NOMELIVO,descricaoLivro.toString(),regraCalculoIndicesIniciais.toString(),
                regraLuta.toString(),regraUsoSorte.toString(),regraReposicaoIndice.toString(),
                regraEquipamentos.toString(),dica.toString(),historia.toString());



    }

    public ArrayList<Secao> tabelaSecao(ManipularArquivos manipularArquivos){
        List listaSecao = new ArrayList();
        String textoSecao;

        //Secao 1 - Necessário atualizar livro que contém a primeira seção, o início.
        textoSecao = String.valueOf(manipularArquivos.lerTexto("textosflorestaestruicao/secao1.liv"));
        Secao secao1 = new Secao(this.livro.getIdLivro(), textoSecao, 1,"imagens/secao1.png");
        this.livro.setSecaoInicial(secao1);
        System.out.println(this.livro);
        return null;
    }


}