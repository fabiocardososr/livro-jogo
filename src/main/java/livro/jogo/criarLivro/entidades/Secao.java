package livro.jogo.criarLivro.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;


public class Secao {
    private Integer codSecaoLivro;
    private String texto;
    private String enderecoImagem;
    private ArrayList<ProximaSecao> proximasSecoes = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();

    public Secao() {
    }

    public Secao(String texto, Integer codSessaoLivro, String enderecoImagem) {
        this.texto = texto;
        this.codSecaoLivro = codSessaoLivro;
        this.enderecoImagem = enderecoImagem;
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

    @Override
    public String toString() {
        return "Secao{" +
                "codSecaoLivro=" + codSecaoLivro +
                ", texto='" + texto + '\'' +
                ", enderecoImagem='" + enderecoImagem + '\'' +
                ", proximasSecoes= opcao 1: " + proximasSecoes.get(0).getCodProximaSecao()+
                " opcao 2: "+proximasSecoes.get(1).getCodProximaSecao() +
                ", itens=" + itens +
                '}';
    }
}
