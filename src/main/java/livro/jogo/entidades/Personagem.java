package livro.jogo.entidades;

import java.util.ArrayList;

public class Personagem {
    private String nome;
    private int idLivro;
    private int habilidadeMax;
    private int energiaMax;
    private int sorteMax;
    private int habilidadeAtual;
    private int energiaAtual;
    private int sorteAtual;
    private String anotacoes;
    private final ArrayList<Item> bolsa = new ArrayList<>();
    private final ArrayList<Item> ItensEquipados = new ArrayList<>();

    /*Preciso manter este objeto "CondicaoEspecialPersonagem" porque tem itens que são temporários e somem
      E para facilitar a organização e adminstração como por exemplo poções que dão 1 de habilidade por duas batalhas
      preciso excluir da bolsa já que não existe mais por ter sido consumido, mas o efeito continua e fica aqui neste
      objeto até o efeito acabar*/
    private final ArrayList<CondicaoEspecialPersonagem> condicaoEspecial = new ArrayList<>();

    public Personagem(String nome, int idLivro, int habilidadeMax, int energiaMax, int sorteMax, int habilidadeAtual, int energiaAtual, int sorteAtual, String anotacoes) {
        this.nome = nome;
        this.idLivro = idLivro;
        this.habilidadeMax = habilidadeMax;
        this.energiaMax = energiaMax;
        this.sorteMax = sorteMax;
        this.habilidadeAtual = habilidadeAtual;
        this.energiaAtual = energiaAtual;
        this.sorteAtual = sorteAtual;
        this.anotacoes = anotacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getHabilidadeMax() {
        return habilidadeMax;
    }

    public int getEnergiaMax() {
        return energiaMax;
    }

    public int getSorteMax() {
        return sorteMax;
    }

    public int getHabilidadeAtual() {
        return habilidadeAtual;
    }

    public int getEnergiaAtual() {
        return energiaAtual;
    }

    public int getSorteAtual() {
        return sorteAtual;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public ArrayList<Item> getBolsa() {
        return bolsa;
    }

    public ArrayList<Item> getItensEquipados() {
        return ItensEquipados;
    }

    public ArrayList<CondicaoEspecialPersonagem> getCondicaoEspecial() {
        return condicaoEspecial;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public void setHabilidadeMax(int habilidadeMax) {
        this.habilidadeMax = habilidadeMax;
    }

    public void setEnergiaMax(int energiaMax) {
        this.energiaMax = energiaMax;
    }

    public void setSorteMax(int sorteMax) {
        this.sorteMax = sorteMax;
    }

    public void setHabilidadeAtual(int habilidadeAtual) {
        this.habilidadeAtual = habilidadeAtual;
    }

    public void setEnergiaAtual(int energiaAtual) {
        this.energiaAtual = energiaAtual;
    }

    public void setSorteAtual(int sorteAtual) {
        this.sorteAtual = sorteAtual;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }
}
