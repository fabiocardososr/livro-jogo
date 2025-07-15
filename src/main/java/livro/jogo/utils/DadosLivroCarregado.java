//Classe responsável por manter os dados do livro após carregamento do Json via classe ManipularArquivos
package livro.jogo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Livro;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.secoes.InfoSecoes;

import java.util.ArrayList;
import java.util.HashMap;

public class DadosLivroCarregado {
    private static Livro livro;
    private static Personagem personagem;
    private static int secaoAtual;
    private static final ObjectMapper objMapper = new ObjectMapper();
    private static InfoSecoes infoSecoes = new InfoSecoes();

    public static Personagem getPersonagem() {
        return personagem;
    }

    public static void setPersonagem(Personagem personagem) {
        DadosLivroCarregado.personagem = personagem;
    }

    public static Livro getLivro() {
        return livro;
    }

    public static void setLivro(Livro livro) {
        DadosLivroCarregado.livro = livro;
    }

    public static HashMap<Integer, Secao> getMapSecao(){
        return livro.getMapSecao();
    }

    public static HashMap<Integer, Item> getMapItem(){
        return livro.getMapItens();
    }

    public static InfoSecoes getInfoSecoes() {
        return infoSecoes;
    }

    public static void setInfoSecoes(InfoSecoes infoSecoes) {
        DadosLivroCarregado.infoSecoes = infoSecoes;
    }

    public static ArrayList<Item> getBolsa(){
        return personagem.getBolsa();
    }

    public static ArrayList<Item> getItensEquipados(){
        return personagem.getItensEquipados();
    }

    public static Item recuperaItemDoJson(String enderecoDoArquivoDoItem){
        Item item = null;

        try {
            var json = ManipularArquivos.lerTexto(enderecoDoArquivoDoItem).toString();
            item = objMapper.readValue(json, Item.class);
        } catch (JsonProcessingException e) {
            System.out.println("Ocorreu problema no arquivo: "+enderecoDoArquivoDoItem);
            //throw new RuntimeException(e);
        }

        return item;
    }

    public static void removeItemEquipado(Item item){
        var itens = getItensEquipados();

        for (int i=0; i < getItensEquipados().size(); i++){

            if (itens.get(i).getIdItem() == item.getIdItem())
                itens.remove(i);
        }
    }

    public static void removeItemBolsa(Item item){
        var itens = getBolsa();

        for (int i=0; i < getBolsa().size(); i++){

            if (itens.get(i).getIdItem() == item.getIdItem())
                itens.remove(i);
        }
    }

    public static int getSecaoAtual() {
        return secaoAtual;
    }

    public static void setSecaoAtual(int secaoAtual) {
        DadosLivroCarregado.secaoAtual = secaoAtual;
    }
}
