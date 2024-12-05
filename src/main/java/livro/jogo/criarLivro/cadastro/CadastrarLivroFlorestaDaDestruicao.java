package livro.jogo.criarLivro.cadastro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.entidades.*;
import livro.jogo.criarLivro.json.SecaoJson;
import livro.jogo.criarLivro.utils.ManipularArquivos;
import livro.jogo.criarLivro.utils.ManipularDados;

import java.util.HashMap;
import java.util.Map;

public class CadastrarLivroFlorestaDaDestruicao {
    private ObjectMapper objMapper = new ObjectMapper();
    private ManipularDados manipularDados;
    private HashMap<Integer, Secao> manipularDadosSecao;

    public void carregarLivroFlorestaDestruicao(){

        //Carregar dados do livro
        Livro livro = carregaLivro(objMapper);

        //Guarda as informações do livro para usar quando necessário.
        manipularDados = new ManipularDados(livro);

        //System.out.println(livro.getDescricao());

        //Carregar seções
        inserirSecoes(objMapper, livro );
    }

    /********************** CARREGAR LIVROS ***************************/

    private Livro carregaLivro(ObjectMapper objMapper)  {

        try {
            var json = ManipularArquivos.lerTexto("textosflorestaestruicao/livro/livro.Json").toString();
            return objMapper.readValue(json, Livro.class); //Transformando JSDON em objeto (API Jackson)
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void inserirSecoes(ObjectMapper objMapper, Livro livro) {

        //Seção 1
        secao(objMapper, livro, "textosflorestaestruicao/secoes/secao_1.json");

    }


    /* ********************** MÉTODOS DAS SEÇÕES ************************ */

    private void secao(ObjectMapper objMapper, Livro livro, String enderecoDoArquivoDaSecao){

        try {
            var json = ManipularArquivos.lerTexto(enderecoDoArquivoDaSecao).toString();
            var  secao = objMapper.readValue(json, Secao.class);
            ManipularDados.getMapSecao().put(secao.getCodSecaoLivro(),secao);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    private void secao3(Livro livro){
//        final int SECAO_LIVRO = 3;
//        final String IMG = "";
//        final String nomeArquivoContemTexto = "textosflorestaestruicao/secao_3.liv";
//        final String OPCAO_DESCRICAO_1          = "Você tem um Anel da Luz?";
//        final int PROXIMA_OPCAO_SECAO_1         = 322;
//        final String OPCAO_DESCRICAO_2          = "Não possui este anel";
//        final int PROXIMA_OPCAO_SECAO_2         = 120;
//
//        //Carrega o texto da seção
//        var texto = ManipularArquivos.lerTexto(nomeArquivoContemTexto).toString();
//
//        //Carregar seção com os dados
//        Secao secao = new Secao(livro, texto, SECAO_LIVRO,IMG);
//
////        //Inserir secao no BD
////        InserirNoBd.gravarNoBd(secao);
////
////        //Próximas sessões
////        ProximaSecao proximaSecao1 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_1,OPCAO_DESCRICAO_1);
////        ProximaSecao proximaSecao2 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_2, OPCAO_DESCRICAO_2);
////        InserirNoBd.gravarNoBd(proximaSecao1);
////        InserirNoBd.gravarNoBd(proximaSecao2);
////
////        //Cadastrar item
////        var descricao = "Anel da Luz";
////        TipoEfeito tipoEfeito = new TipoEfeito(4, "NENHUM");
////        Item item = new Item(tipoEfeito,descricao,3,0,0,
////                "N","N","N","N",
////                "N","N");
////
////        //Gravando o item no BD
////        InserirNoBd.gravarNoBd(item);
////
////        //Gravando o item encontrado na seção (livros.SecaoItens)
////        InserirNoBd.gravarNoBd(new SecaoItens(secao,item,1));
//    }

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
        //Secao secao = new Secao(livro, texto, SECAO_LIVRO,IMG);

//        //Inserir secao no BD
//        InserirNoBd.gravarNoBd(secao);
//
//        //Próximas sessões
//        ProximaSecao proximaSecao1 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_1,OPCAO_DESCRICAO_1);
//        ProximaSecao proximaSecao2 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_2, OPCAO_DESCRICAO_2);
//        InserirNoBd.gravarNoBd(proximaSecao1);
//        InserirNoBd.gravarNoBd(proximaSecao2);
    }

    private void secao5(Livro livro){
//        final int SECAO_LIVRO = 5;
//        final String IMG = "";
//        final String nomeArquivoContemTexto = "textosflorestaestruicao/secao_5.liv";
//        final String OPCAO_DESCRICAO_1          = "Se você quiser colocar a coroa de ouro sobre sua cabeça";
//        final int PROXIMA_OPCAO_SECAO_1         = 333;
//        final String OPCAO_DESCRICAO_2          = "Se preferir sair do nicho e subir o restante dos degraus até o teto da caverna";
//        final int PROXIMA_OPCAO_SECAO_2         = 249;
//
//        //Carrega o texto da seção
//        var texto = ManipularArquivos.lerTexto(nomeArquivoContemTexto).toString();
//
//        //Carregar seção com os dados
//        Secao secao = new Secao(livro, texto, SECAO_LIVRO,IMG);
//
//        //Inserir secao no BD
//        InserirNoBd.gravarNoBd(secao);
//
//        //Próximas sessões
//        ProximaSecao proximaSecao1 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_1,OPCAO_DESCRICAO_1);
//        ProximaSecao proximaSecao2 = new ProximaSecao(secao,PROXIMA_OPCAO_SECAO_2, OPCAO_DESCRICAO_2);
//        InserirNoBd.gravarNoBd(proximaSecao1);
//        InserirNoBd.gravarNoBd(proximaSecao2);
//
//        //Cadastrar item
//        var descricao = "Coroa de ouro";
//        TipoEfeito tipoEfeito = new TipoEfeito(4, "NENHUM");
//        Item item = new Item(tipoEfeito,descricao,0,0,0,
//                "N","N","N","N",
//                "N","N");
//
//        //Gravando o item no BD
//        InserirNoBd.gravarNoBd(item);
//
//        //Gravando o item encontrado na seção (livros.SecaoItens)
//        InserirNoBd.gravarNoBd(new SecaoItens(secao,item,1));
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