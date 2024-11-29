package livro.jogo.criarLivro.cadastro;

import livro.jogo.criarLivro.entidades.Livro;
import livro.jogo.criarLivro.entidades.ProximaSecao;
import livro.jogo.criarLivro.entidades.Secao;
import livro.jogo.criarLivro.entidades.TipoEfeito;
import livro.jogo.criarLivro.utils.InserirNoBd;
import livro.jogo.criarLivro.utils.ManipularArquivos;

public class CadastrarLivroFlorestaDaDestruicao {

    public void carregarLivroFlorestaDestruicao(){

        /********** Apaga tudo para refazer **********/
        InserirNoBd.apagarTudo();


        /********** Inserção dos dados ***********/
        //TipoEfeito (Se HABILIDADE, SORTE e ENERGIA)
        inserirTiposEfeito();

        Livro livro = carregaLivro();
        inserirSecoes(livro);
    }

    /********************** CARREGAR TIPOS DE EFEITOS ***************************/
    private void inserirTiposEfeito(){
        TipoEfeito tipoEfeito1 = new TipoEfeito(1,"HABILIDADE");
        TipoEfeito tipoEfeito2 = new TipoEfeito(2,"ENERGIA");
        TipoEfeito tipoEfeito3 = new TipoEfeito(3,"SORTE");

        InserirNoBd.gravarNoBd(tipoEfeito1);
        InserirNoBd.gravarNoBd(tipoEfeito2);
        InserirNoBd.gravarNoBd(tipoEfeito3);
    }

    /********************** CARREGAR LIVROS ***************************/

    private Livro carregaLivro(){
        final int IDLIVRO = 1; //O campo id não vai ser autoincrement, vou declarar para cada livro.
        final int SECAO_INCIAL = 1; //Corresponde a primeira seção do livro. I início da história interativa
        //Descrição do livro
        StringBuilder descricaoLivro = ManipularArquivos.lerTexto("textosflorestaestruicao/descricaoLivro.liv");

        //Descrição dos índices de HABILIDADE, ENERGIA E SORTE
        StringBuilder regraCalculoIndicesIniciais = ManipularArquivos.lerTexto("textosflorestaestruicao/regraCalcularIndicesIniciais.liv");

        //Regras de como é feita a batalha
        StringBuilder regraBatalha = ManipularArquivos.lerTexto("textosflorestaestruicao/regraBatalha.liv");

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
        String imagens = "imagens/capalivro.png;imagens/mapaInicial.png";
        Livro livro = new Livro(IDLIVRO, nomeLivro,descricaoLivro.toString(),SECAO_INCIAL,regraCalculoIndicesIniciais.toString(),
                regraBatalha.toString(),regraUsoSorte.toString(),regraReposicaoIndice.toString(),
                regraEquipamentos.toString(),dica.toString(),historia.toString(), imagens);

        //Grava no banco
        InserirNoBd.gravarNoBd(livro);

        return livro;
    }

    private void inserirSecoes(Livro livro){

        secao1(livro);
        secao2(livro);
        secao3(livro);

    }


    /* ********************** MÉTODOS DAS SEÇÕES ************************ */


    private void secao1(Livro livro){
        final int SECAO_LIVRO                   = 1; //Referencia a secao real no livro
        final String nomeArquivoContemTexto     = "textosflorestaestruicao/secao_1.liv";
        final String IMG                        = "imagens/secao1.png";
        final String OPCAO_DESCRICAO_1          = "Subirá as escadas atrás dele?";
        final String OPCAO_DESCRICAO_2          = "Desembainhará sua espada para atacá-lo?";
        final int PROXIMA_OPCAO_SECAO_1         = 261;
        final int PROXIMA_OPCAO_SECAO_2         = 54;

        //Carrega o texto da seção
        var texto = ManipularArquivos.lerTexto(nomeArquivoContemTexto).toString();

        //Carregar seção com os dados
        Secao secao = new Secao(livro, texto, SECAO_LIVRO,IMG);

        //Inserir secao no BD
        InserirNoBd.gravarNoBd(secao);

        //Próximas seções
        ProximaSecao proximaSecao1 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_1,OPCAO_DESCRICAO_1);
        ProximaSecao proximaSecao2 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_2, OPCAO_DESCRICAO_2);
        InserirNoBd.gravarNoBd(proximaSecao1);
        InserirNoBd.gravarNoBd(proximaSecao2);
    }

    private void secao2(Livro livro){
        final String IMG = "";
        final int SECAO_LIVRO = 2;
        final String nomeArquivoContemTexto = "textosflorestaestruicao/secao_2.liv";

        //Carrega o texto da seção
        var texto = ManipularArquivos.lerTexto(nomeArquivoContemTexto).toString();

        //Carregar seção com os dados
        Secao secao = new Secao(livro, texto, SECAO_LIVRO,IMG);

        //Inserir secao no BD
        InserirNoBd.gravarNoBd(secao);
    }

    private void secao3(Livro livro){
        final int SECAO_LIVRO = 3;
        final String IMG = "";
        final String nomeArquivoContemTexto = "textosflorestaestruicao/secao_3.liv";
        final String OPCAO_DESCRICAO_1          = "Você tem um Anel de Luz?";
        final String OPCAO_DESCRICAO_2          = "Não possui este anel";
        final int PROXIMA_OPCAO_SECAO_1         = 322;
        final int PROXIMA_OPCAO_SECAO_2         = 120;

        //Carrega o texto da seção
        var texto = ManipularArquivos.lerTexto(nomeArquivoContemTexto).toString();

        //Carregar seção com os dados
        Secao secao = new Secao(livro, texto, SECAO_LIVRO,IMG);

        //Inserir secao no BD
        InserirNoBd.gravarNoBd(secao);

        //Próximas sessões
        ProximaSecao proximaSecao1 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_1,OPCAO_DESCRICAO_1);
        ProximaSecao proximaSecao2 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_2, OPCAO_DESCRICAO_2);
        InserirNoBd.gravarNoBd(proximaSecao1);
        InserirNoBd.gravarNoBd(proximaSecao2);

        //Cadastrar item

    }


}