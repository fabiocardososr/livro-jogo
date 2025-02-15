package livro.jogo.entidades;

import java.io.Serializable;

public class Inimigo implements Serializable {
    private int idInimigo;
    private String nome;
    private int habilidade;
    private int energia;
    private String enderecoImagem;

    public Inimigo() {
    }

    public Inimigo(int idInimigo, String nome, int habilidade, int energia, String enderecoImagem) {
        this.idInimigo = idInimigo;
        this.nome = nome;
        this.habilidade = habilidade;
        this.energia = energia;
        this.enderecoImagem = enderecoImagem;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    public int getIdInimigo() {
        return idInimigo;
    }

    public String getNome() {
        return nome;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    @Override
    public String toString() {
        return "\nInimigo{" +
                "idInimigo=" + idInimigo +
                ", nome='" + nome + '\'' +
                ", habilidade=" + habilidade +
                ", energia=" + energia +
                " enderecoImagem="+enderecoImagem +
                '}';
    }
}
