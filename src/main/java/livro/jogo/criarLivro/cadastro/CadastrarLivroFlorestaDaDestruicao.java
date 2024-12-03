package livro.jogo.criarLivro.cadastro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.cadastro.entidades.*;
import livro.jogo.criarLivro.cadastro.json.SecaoJson;
import livro.jogo.criarLivro.cadastro.utils.InserirNoBd;
import livro.jogo.criarLivro.cadastro.utils.ManipularArquivos;

import java.util.List;

public class CadastrarLivroFlorestaDaDestruicao {

    public void carregarLivroFlorestaDestruicao(){
        ObjectMapper objMapper = new ObjectMapper();


        /********** Apaga tudo para refazer **********/
        InserirNoBd.apagarTudo();

        /********** Inserção dos dados nas tabelas ***********/

        //TipoEfeito (Se HABILIDADE, SORTE, ENERGIA ou NENHUM)
        carregaTiposEfeito();

        //Carregar dados do livro
        Livro livro = carregaLivro(objMapper);

        //Carregar seções
        inserirSecoes(objMapper, livro );
    }

    /********************** CARREGAR TIPOS DE EFEITOS ***************************/
    private void carregaTiposEfeito(){
        TipoEfeito tipoEfeito1 = new TipoEfeito(1,"HABILIDADE");
        TipoEfeito tipoEfeito2 = new TipoEfeito(2,"ENERGIA");
        TipoEfeito tipoEfeito3 = new TipoEfeito(3,"SORTE");
        TipoEfeito tipoEfeito4 = new TipoEfeito(4,"NENHUM");

        InserirNoBd.gravarNoBd(tipoEfeito1);
        InserirNoBd.gravarNoBd(tipoEfeito2);
        InserirNoBd.gravarNoBd(tipoEfeito3);
        InserirNoBd.gravarNoBd(tipoEfeito4);
    }

    /********************** CARREGAR LIVROS ***************************/

    private Livro carregaLivro(ObjectMapper objMapper)  {

        try {
            var json = ManipularArquivos.lerTexto("textosflorestaestruicao/livro/livro.Json").toString();
            var livro = objMapper.readValue(json, Livro.class); //Transformando JSDON em objeto (API Jackson)
            InserirNoBd.gravarNoBd(livro);
            return livro;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void inserirSecoes(ObjectMapper objMapper, Livro livro){

        //Seção 1
        secao(objMapper, livro,"textosflorestaestruicao/secoes/secao_1.json");

        //Seção 2
        secao(objMapper, livro,"textosflorestaestruicao/secoes/secao_2.json");

        //Seção 3
        secao(objMapper, livro,"textosflorestaestruicao/secoes/secao_3.json");

//        secao2(livro);
//        secao3(livro);
//        secao4(livro);
//        secao5(livro);

    }


    /* ********************** MÉTODOS DAS SEÇÕES ************************ */

    private void secao(ObjectMapper objMapper, Livro livro, String enderecoDoArquivoDaSecao){

        try {
            var json = ManipularArquivos.lerTexto(enderecoDoArquivoDaSecao).toString();
            var  secaoJson = objMapper.readValue(json, SecaoJson.class);

            //Seção
            Secao secao = new Secao(livro,secaoJson.getTexto(), secaoJson.getCodSecaoLivro(),secaoJson.getEnderecoImagem());
            InserirNoBd.gravarNoBd(secao);

            //Próxima seção
            List proximasSecoes = secaoJson.getProximasSecoes();
            for (int i = 0; i < proximasSecoes.size(); i++) {

                ProximaSecao proximaSecao = (ProximaSecao) proximasSecoes.get(i);
                proximaSecao.setSecao(secao);
                InserirNoBd.gravarNoBd(proximaSecao);
                System.out.println(proximaSecao);
            }

            //Cadastrar itens da seção, se existir
            List itens = secaoJson.getItens();
            for (int i = 0; i < itens.size(); i++) {

                Item item = (Item) itens.get(i);
                item.setTipoEfeito(new TipoEfeito(4, "NENHUM"));

                InserirNoBd.gravarNoBd(item);
            }

            //CADASTRAR NA TABELA livros.secaoItens SIGA O EXEMPLO DO OBJETO Item

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void secao3(Livro livro){
        final int SECAO_LIVRO = 3;
        final String IMG = "";
        final String nomeArquivoContemTexto = "textosflorestaestruicao/secao_3.liv";
        final String OPCAO_DESCRICAO_1          = "Você tem um Anel da Luz?";
        final int PROXIMA_OPCAO_SECAO_1         = 322;
        final String OPCAO_DESCRICAO_2          = "Não possui este anel";
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
        var descricao = "Anel da Luz";
        TipoEfeito tipoEfeito = new TipoEfeito(4, "NENHUM");
        Item item = new Item(tipoEfeito,descricao,3,0,0,
                "N","N","N","N",
                "N","N");

        //Gravando o item no BD
        InserirNoBd.gravarNoBd(item);

        //Gravando o item encontrado na seção (livros.SecaoItens)
        InserirNoBd.gravarNoBd(new SecaoItens(secao,item,1));
    }

    private void secao4(Livro livro){
        final int SECAO_LIVRO = 4;
        final String IMG = "";
        final String nomeArquivoContemTexto = "textosflorestaestruicao/secao_4.liv";
        final String OPCAO_DESCRICAO_1          = "Se ainda desejar entrar na caverna";
        final int PROXIMA_OPCAO_SECAO_1         = 49;
        final String OPCAO_DESCRICAO_2          = "Se preferir se arrastar de volta para a encruzilhada";
        final int PROXIMA_OPCAO_SECAO_2         = 93;

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
    }

    private void secao5(Livro livro){
        final int SECAO_LIVRO = 5;
        final String IMG = "";
        final String nomeArquivoContemTexto = "textosflorestaestruicao/secao_5.liv";
        final String OPCAO_DESCRICAO_1          = "Se você quiser colocar a coroa de ouro sobre sua cabeça";
        final int PROXIMA_OPCAO_SECAO_1         = 333;
        final String OPCAO_DESCRICAO_2          = "Se preferir sair do nicho e subir o restante dos degraus até o teto da caverna";
        final int PROXIMA_OPCAO_SECAO_2         = 249;

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
        var descricao = "Coroa de ouro";
        TipoEfeito tipoEfeito = new TipoEfeito(4, "NENHUM");
        Item item = new Item(tipoEfeito,descricao,0,0,0,
                "N","N","N","N",
                "N","N");

        //Gravando o item no BD
        InserirNoBd.gravarNoBd(item);

        //Gravando o item encontrado na seção (livros.SecaoItens)
        InserirNoBd.gravarNoBd(new SecaoItens(secao,item,1));
    }

    public void teste() throws JsonProcessingException {
//        String nomeArquivoContemTexto = "textosflorestaestruicao/secoes/secao_1.json";
//        var json = ManipularArquivos.lerTexto(nomeArquivoContemTexto).toString();
//
//        final var objMapper = new ObjectMapper();
//        final var  secaoJson = objMapper.readValue(json, SecaoJson.class);
//
//       //System.out.println(livro.getIdLivro());
//        System.out.println("\n\n\n\n=================\n\n\n\n");
//        System.out.println("SEÇÃO INCIAL: "+secaoJson.toString());

    }


}