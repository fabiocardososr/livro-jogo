package livro.jogo.criarLivro.json;

import livro.jogo.criarLivro.entidades.Item;
import livro.jogo.criarLivro.entidades.ProximaSecao;

import java.util.ArrayList;

public class SecaoJson {
    private String texto;
    private int codSecaoLivro;
    private String enderecoImagem;
    private ArrayList<ProximaSecao> proximasSecoes = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();

    public ArrayList<Item> getItens() {
        return itens;
    }

    public SecaoJson() {
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    public int getCodSecaoLivro() {
        return codSecaoLivro;
    }

    public ArrayList<ProximaSecao> getProximasSecoes() {
        return proximasSecoes;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return "SecaoJson{" +
                "texto='" + texto + '\'' +
                ", proximaSecoes 1= "+proximasSecoes.get(0).getCodProximaSecao()+ " - " + proximasSecoes.get(0).getTextoOpcao() +
                ", proximaSecoes 2= "+proximasSecoes.get(1).getCodProximaSecao()+ " - " + proximasSecoes.get(1).getTextoOpcao() +
                '}';
    }
}
