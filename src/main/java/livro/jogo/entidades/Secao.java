package livro.jogo.entidades;

import java.util.ArrayList;


public class Secao {
    private Integer codSecaoLivro;
    private String texto;
    private String enderecoImagem;
    private String enderecoAudio;
    private final ArrayList<ProximaSecao> proximasSecoes = new ArrayList<>();
    private final ArrayList<Item> itens = new ArrayList<>();
    private final ArrayList<Inimigo> inimigos = new ArrayList<>();


    public Secao() {
    }

    public String getEnderecoAudio() {
        return enderecoAudio;
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
                "codSecaoLivro=" + codSecaoLivro +
                ", texto='" + texto + '\'' +
                ", enderecoImagem='" + enderecoImagem + '\'' +
                ", proximasSecoes= " + proximasSecoes+
                ", itens=" + itens +
                ", Inimigos=["+inimigos+"]"+
                '}';
    }
}
