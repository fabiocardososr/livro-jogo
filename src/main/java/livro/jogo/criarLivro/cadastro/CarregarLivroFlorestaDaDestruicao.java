package livro.jogo.criarLivro.cadastro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Livro;
import livro.jogo.entidades.Secao;
import livro.jogo.utils.ManipularArquivos;
import livro.jogo.utils.ManipularDados;

import java.io.File;

public class CarregarLivroFlorestaDaDestruicao {
    public void carregarLivroFlorestaDestruicao(){

        ObjectMapper objMapper = new ObjectMapper();

        //Guarda as informações do livro para usar quando necessário.
        ManipularDados.setLivro( carregaLivro(objMapper) );

        //Carregar seções
        inserirSecoes(objMapper);
    }

    /********************** CARREGAR LIVROS ***************************/

    private Livro carregaLivro(ObjectMapper objMapper)  {

        try {
            var json = ManipularArquivos.lerTexto("livros/florestadadestruicao/livro/livroFlorestaDaDestruicao.Json").toString();
            return objMapper.readValue(json, Livro.class); //Transformando JSDON em objeto (API Jackson)
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void inserirSecoes(ObjectMapper objMapper) {

        //São exatas 400 seções
        for (int i=1; i<=400; i++) {
            var endereco = "livros/florestadadestruicao/secoes/secao_"+i+".json";
            var existe = (new File(endereco)).exists();

            if (existe)
                secao(objMapper, endereco);
            else
                System.out.println("INEXISTENTE o Arquivo: "+endereco);
        }

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
            System.out.println("Acabou o carregamento ou ocorreu problema no arquivo: "+enderecoDoArquivoDaSecao);
            //throw new RuntimeException(e);
        }
    }



}