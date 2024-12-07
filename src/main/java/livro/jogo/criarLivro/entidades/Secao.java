package livro.jogo.criarLivro.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;


public class Secao {
    private Integer codSecaoLivro;
    private Integer idLivro;
    private String texto;
    private String enderecoImagem;
    private ArrayList<ProximaSecao> proximasSecoes = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<Inimigo> inimigos = new ArrayList<>();

    public Secao() {
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public Integer getCodSecaoLivro() {
        return codSecaoLivro;
    }

    public String getTexto() {
        return texto;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    public ArrayList<ProximaSecao> getProximasSecoes() {
        return proximasSecoes;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public ArrayList<Inimigo> getInimigos() {
        return inimigos;
    }

    @Override
    public String toString() {
        return "Secao{" +
                " idLivro="+idLivro+
                "codSecaoLivro=" + codSecaoLivro +
                ", texto='" + texto + '\'' +
                ", enderecoImagem='" + enderecoImagem + '\'' +
                ", proximasSecoes= " + proximasSecoes+
                ", itens=" + itens +
                ", Inimigos=["+inimigos+"]"+
                '}';
    }
}
