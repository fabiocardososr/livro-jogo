package livro.jogo.criarLivro.cadastro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.entidades.*;
import livro.jogo.criarLivro.utils.ManipularArquivos;
import livro.jogo.criarLivro.utils.ManipularDados;

import java.util.HashMap;

public class CarregarLivroFlorestaDaDestruicao {
    public void carregarLivroFlorestaDestruicao(){

        ObjectMapper objMapper = new ObjectMapper();

        //Carregar dados do livro
        Livro livro = carregaLivro(objMapper);

        //Guarda as informações do livro para usar quando necessário.
        ManipularDados.setLivro(livro);

        //Carregar seções
        inserirSecoes(objMapper);
    }

    /********************** CARREGAR LIVROS ***************************/

    private Livro carregaLivro(ObjectMapper objMapper)  {

        try {
            var json = ManipularArquivos.lerTexto("textosflorestaestruicao/livro/livroFlorestaDaDestruicao.Json").toString();
            return objMapper.readValue(json, Livro.class); //Transformando JSDON em objeto (API Jackson)
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void inserirSecoes(ObjectMapper objMapper) {

        //Seções
        secao(objMapper, "textosflorestaestruicao/secoes/secao_1.json");
        secao(objMapper,  "textosflorestaestruicao/secoes/secao_2.json");
        secao(objMapper,  "textosflorestaestruicao/secoes/secao_3.json");
        secao(objMapper,  "textosflorestaestruicao/secoes/secao_4.json");
        secao(objMapper,  "textosflorestaestruicao/secoes/secao_5.json");
        secao(objMapper, "textosflorestaestruicao/secoes/secao_6.json");
        secao(objMapper, "textosflorestaestruicao/secoes/secao_7.json");
        secao(objMapper, "textosflorestaestruicao/secoes/secao_8.json");
        secao(objMapper, "textosflorestaestruicao/secoes/secao_9.json");
        secao(objMapper, "textosflorestaestruicao/secoes/secao_10.json");
        secao(objMapper, "textosflorestaestruicao/secoes/secao_11.json");

        //Apenas para verificar se tudo ocorreu bem(depois pode remover)
        ManipularDados.imprimirInfoSecoes();

    }


    /* ********************** MÉTODOS DAS SEÇÕES ************************ */

    private void secao(ObjectMapper objMapper, String enderecoDoArquivoDaSecao){

        try {

            var json = ManipularArquivos.lerTexto(enderecoDoArquivoDaSecao).toString();
            var  secao = objMapper.readValue(json, Secao.class);
            ManipularDados.getMapSecao().put(secao.getCodSecaoLivro(),secao);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}