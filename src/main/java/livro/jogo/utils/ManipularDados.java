//Classe responsável por manter os dados do livro após carregamento do Json via classe ManipularArquivos
package livro.jogo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Livro;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;

import java.util.ArrayList;
import java.util.HashMap;

public class ManipularDados {
    private static Livro livro;
    private static Personagem personagem;

    public static Personagem getPersonagem() {
        return personagem;
    }

    public static void setPersonagem(Personagem personagem) {
        ManipularDados.personagem = personagem;
    }

    public static Livro getLivro() {
        return livro;
    }

    public static void setLivro(Livro livro) {
        ManipularDados.livro = livro;
    }

    public static HashMap<Integer, Secao> getMapSecao(){
        return livro.getMapSecao();
    }

    public static ArrayList<Item> getBolsa(){
        return personagem.getBolsa();
    }

    public static ArrayList<Item> getItensEquipados(){
        return personagem.getItensEquipados();
    }

    public static void imprimirInfoSecoes() {
        Secao secao;

        //Dados do livro
        System.out.print("\n\nIdLivro: "+ livro.getIdLivro());
        System.out.println("\nNome: "+ livro.getNome());
        System.out.println("Descrição: "+ livro.getDescricao());
        System.out.println("\n======================================\n\n");


        for (Integer key : livro.getMapSecao().keySet()) {

            //Capturamos o valor a partir da chave
            secao = (Secao) livro.getMapSecao()                                                                .get(key);
            System.out.println("\n\nCod. Seção: "+ secao.getCodSecaoLivro());
            System.out.println("Desc. Seção: "+ secao.getTexto());
            System.out.println("Imagem da Seção: "+ secao.getEnderecoImagem());
            System.out.println("Proximas seções: "+ secao.getProximasSecoes().size());
            System.out.println("Proximas seções: "+ secao.getProximasSecoes());
            System.out.println("Itens: "+ secao.getItens().toString());
            System.out.println("Inimigos: "+ secao.getInimigos().toString());
        }
    }

    public static void imprimirInfoItens() {

        //Dados do livro
        System.out.print("\n\nItens: "+ livro.getIdLivro());

        ArrayList<Item> bolsas = personagem.getBolsa();

        bolsas.forEach(item -> System.out.println("\n\nITEM: "+ item));

    }

    public static Item recuperaItemDoJsonEGuardaNaBolsa(ObjectMapper objMapper, String enderecoDoArquivoDoItem){
        Item item = null;

        try {
            var json = ManipularArquivos.lerTexto(enderecoDoArquivoDoItem).toString();
            item = objMapper.readValue(json, Item.class);
        } catch (JsonProcessingException e) {
            System.out.println("Acabou o carregamento ou ocorreu problema no arquivo: "+enderecoDoArquivoDoItem);
            //throw new RuntimeException(e);
        }

        return item;
    }

    public static void recuperaItemDoJsonEEquipaItem(ObjectMapper objMapper, String enderecoDoArquivoDoItem){
        try {
            var json = ManipularArquivos.lerTexto(enderecoDoArquivoDoItem).toString();
            var  item = objMapper.readValue(json, Item.class);
            ManipularDados.getBolsa().add(item);
        } catch (JsonProcessingException e) {
            System.out.println("Acabou o carregamento ou ocorreu problema no arquivo: "+enderecoDoArquivoDoItem);
            //throw new RuntimeException(e);
        }
    }
}
