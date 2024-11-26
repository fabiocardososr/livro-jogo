package livro.jogo.cadastros;

import livro.jogo.DAO.LivroDAO;
import livro.jogo.entidades.Livro;
import livro.jogo.entidades.ProximaSecao;
import livro.jogo.entidades.Secao;
import livro.jogo.utils.JPAUtil;
import livro.jogo.utils.ManipularArquivos;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CadastrarLivroFlorestaDaDestruicao {
    private EntityManager ENTITY;//  = JPAUtil.getEntityManager(); //Entidade de persistência.
    //private final LivroDAO LIVRODAO     = new LivroDAO(ENTITY); //Entidade de persistência
    private final List<Secao> listaSecoes     = new ArrayList<>();
    private Livro livro;

    public void carregarLivroFlorestaDestruicao(){
        ENTITY  = JPAUtil.getEntityManager();
        //Carregar Livro
        carregaLivro();

        //carregar secoes
       carregaSecoes();

        //Criar método para gravar no banco
        System.out.println("teste");
    }

    public void carregaLivro(){
        final int IDLIVRO = 1; //O campo id não vai ser autoincrement, vou declarar para cada livro.
        final int SECAO_INCIAL = 1; //Corresponde a primeira seção do livro. I início da história interativa
        //Descrição do livro
        StringBuilder descricaoLivro = ManipularArquivos.lerTexto("textosflorestaestruicao/descricaoLivro.liv");

        //Descrição dos índices de HABILIDADE, ENERGIA E SORTE
        StringBuilder regraCalculoIndicesIniciais = ManipularArquivos.lerTexto("textosflorestaestruicao/regraCalcularIndicesIniciais.liv");

        //Regras de como é feita a batalha
        StringBuilder regraLuta = ManipularArquivos.lerTexto("textosflorestaestruicao/regraLuta.liv");

        //Regras de REGRAS DE USO DA SORTE
        StringBuilder regraUsoSorte = ManipularArquivos.lerTexto("textosflorestaestruicao/regraUsoSorte.liv");

        //Regras reposicao dos índices HABILIDADE, ENERGIA E SORTE
        StringBuilder regraReposicaoIndice = ManipularArquivos.lerTexto("textosflorestaestruicao/regraReposicaoIndice.liv");

        //Regras de equipamentos
        StringBuilder regraEquipamentos = ManipularArquivos.lerTexto("textosflorestaestruicao/regraEquipamentos.liv");

        //Regras de equipamentos
        StringBuilder dica = ManipularArquivos.lerTexto("textosflorestaestruicao/dica.liv");

        //Regras início da história
        StringBuilder historia = ManipularArquivos.lerTexto("textosflorestaestruicao/historia.liv");

        String nomeLivro = "Floresta da Destruição (Darkwood)";
        this.livro = new Livro(IDLIVRO, nomeLivro,descricaoLivro.toString(),SECAO_INCIAL,regraCalculoIndicesIniciais.toString(),
                regraLuta.toString(),regraUsoSorte.toString(),regraReposicaoIndice.toString(),
                regraEquipamentos.toString(),dica.toString(),historia.toString(), "imagens/capalivro.png");
    }

    public void carregaSecoes(){


        secao1("textosflorestaestruicao/secao1.liv");

       // System.out.println(this.listaSecoes.get(0));
        //System.out.println(this.livro);
    }

            /* *** MÉTODOS DAS SEÇÕES *** */

    public void secao1(String nomeArquivoContemTexto){
        final String OPCAO_DESCRICAO_1 ="Subirá as escadas atrás dele?";
        final String OPCAO_DESCRICAO_2 = "Desembainhará sua espada para atacá-lo?";
        final String IMG = "imagens/secao1.png";
        final int SECAO_LIVRO = 1; //Referencia a secao real no livro
        final int PROXIMA_OPCAO_SECAO_1 = 261;
        final int PROXIMA_OPCAO_SECAO_2 = 54;
        final List<ProximaSecao> proximaSecoes= new ArrayList<>();

        //Carregar seção com os dados
        Secao secao = new Secao(this.livro.getIdLivro(), ManipularArquivos.lerTexto(nomeArquivoContemTexto).toString(),
                SECAO_LIVRO,IMG);

        //Próximas seções (ProximaSecao)
        ProximaSecao proximaSecao1 = new ProximaSecao(this.livro.getIdLivro(),secao,PROXIMA_OPCAO_SECAO_1,OPCAO_DESCRICAO_1);
        ProximaSecao proximaSecao2 = new ProximaSecao(this.livro.getIdLivro(),secao,PROXIMA_OPCAO_SECAO_2, OPCAO_DESCRICAO_2);
        proximaSecoes.add(proximaSecao1);
        proximaSecoes.add(proximaSecao2);




        //Necessário atualizar o objeto livro, pois contém referência a seção inicial.
       // this.livro.setSecaoInicial(secao);

        this.listaSecoes.add(secao);

        //System.out.println("Seção: "+secao);
        System.out.println("Próximas Seções Opção 1: vai para: "+proximaSecao1.getCodProximaSecao() + " - Descrição: "+ proximaSecao1.getTextoOpcao());
        System.out.println("Próximas Seções Opção 2: "+proximaSecao2.getCodProximaSecao() + " - Descrição: "+ proximaSecao2.getTextoOpcao());
        //System.out.println("Livro: "+proximaSecao1);

        //Carregar ProximaSecao

    }




}