package livro.jogo.entidades;

import livro.jogo.enums.TipoEfeito;

import java.io.Serializable;

public class Item implements Serializable {
    private int idItem;
    private TipoEfeito tipoEfeito;                  //indica onde o artefato vai atuar se na HABILIDADE, ENERGIA OU SORTE
    private  String nome;
    private String descricao;                       //Descreve o efeito do item
    private  int valorCusto;                         //Como se trata de moedas de ouro pode ficar com tipo inteiro
    private  int modificador;                        //Altera o valor dos efeitos. Seja ele bom ou ruim, temporário ou permanente (ver os flags).
    private  int quantidadeUso;                      //Este campo é setado caso o flgUsoTemporario = "S". Pois este campo indica que o efeito tem limitação de uso, já que é temporário.
    private  String flgUsoTemporario;                //O item tem efeito temporário, seja uma arma ou poção. Exemplo somar mais 1 nas rolagem de dados por duas batalhas. (usado em conjunto com quantidadeUso e modificador)
    private  String flgUsoPermanente;               //Indica se o efeito do item afeta permanentemente o personagem enquanto usar o item. Exemplo, um item que causa perda de -2 dois pontos nas rolagem de ataques.
    private  String flgAfetaRolagemDados;           //Indica que o efeito deste item afeta a rolagem de dados tanto para o bem(flgEfeitoPositivo) ou para o mal(flgEfeitoNegativo) decrementando/somando o que consta no campo modificador.
    private  String flgAfetaNivelAtualPersonagem;   //Diz se o efeito do item afeta os níveis atuais de HABILIDADE, ENERGIA ou SORTE. A depender do campo "idEfeito".
    private  String flgAfetaNivelMaxPersonagem;      //Diz se o efeito do item afeta os níveis MAX (aumentando o valor que foi definido na rolagem de dados na criação do personagem) de HABILIDADE, ENERGIA ou SORTE. A depender do campo "idEfeito".
    private String enderecoImagem;


    public Item() {}

    public Item(int idItem, TipoEfeito tipoEfeito, String nome,String descricao, int valorCusto, int modificador, int quantidadeUso, String flgUsoTemporario, String flgUsoPermanente, String flgAfetaRolagemDados, String flgAfetaNivelAtualPersonagem, String flgAfetaNivelMaxPersonagem,String enderecoImagem) {
        this.idItem = idItem;
        this.tipoEfeito = tipoEfeito;
        this.nome = nome;
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.modificador = modificador;
        this.quantidadeUso= quantidadeUso;
        this.flgUsoTemporario = flgUsoTemporario;
        this.flgUsoPermanente = flgUsoPermanente;
        this.flgAfetaRolagemDados = flgAfetaRolagemDados;
        this.flgAfetaNivelAtualPersonagem = flgAfetaNivelAtualPersonagem;
        this.flgAfetaNivelMaxPersonagem = flgAfetaNivelMaxPersonagem;
        this.enderecoImagem = enderecoImagem;
    }

    public void setQuantidadeUso(int quantidadeUso) {
        this.quantidadeUso = quantidadeUso;
    }

    public String getNome() {
        return nome;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    public int getIdItem() {
        return idItem;
    }

    public TipoEfeito getTipoEfeito() {
        return tipoEfeito;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValorCusto() {
        return valorCusto;
    }

    public int getModificador() {
        return modificador;
    }

    public int getQuantidadeUso() {
        return quantidadeUso;
    }

    public String getFlgUsoTemporario() {
        return flgUsoTemporario;
    }

    public String getFlgUsoPermanente() {
        return flgUsoPermanente;
    }

    public String getFlgAfetaRolagemDados() {
        return flgAfetaRolagemDados;
    }

    public String getFlgAfetaNivelAtualPersonagem() {
        return flgAfetaNivelAtualPersonagem;
    }

    public String getFlgAfetaNivelMaxPersonagem() {
        return flgAfetaNivelMaxPersonagem;
    }

    @Override
    public String toString() {
        return "\nItem{" +
                "idItem=" + idItem +
                ", Nome=" +nome +
                ", tipoEfeito=" + tipoEfeito +
                ", descricao='" + descricao + '\'' +
                ", valorCusto=" + valorCusto +
                ", modificador=" + modificador +
                ", quantidadeUsoTemporario=" + quantidadeUso +
                ", flgUsoTemporario='" + flgUsoTemporario + '\'' +
                ", flgUsoPermanente='" + flgUsoPermanente + '\'' +
                ", flgAfetaRolagemDados='" + flgAfetaRolagemDados + '\'' +
                ", flgAfetaNivelAtualPersonagem='" + flgAfetaNivelAtualPersonagem + '\'' +
                ", flgAfetaNivelMaxPersonagem='" + flgAfetaNivelMaxPersonagem + '\'' +
                '}';
    }
}
