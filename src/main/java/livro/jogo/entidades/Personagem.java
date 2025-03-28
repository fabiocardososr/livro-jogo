package livro.jogo.entidades;

import livro.jogo.Personagens.CondicaoEspecialPersonagem;

import java.io.Serializable;
import java.util.ArrayList;

public class Personagem implements Serializable {
    private String nome;
    private int genero; // Gênero que corresponde ao sexo biológico que lhes foi atribuído no nascimento
    private int idLivro;
    private int habilidadeMax;
    private int energiaMax;
    private int sorteMax;
    private int habilidadeAtual;
    private int energiaAtual;
    private int sorteAtual;
    private String anotacoes;
    private int quantidadeOuro;
    private ArrayList<Item> bolsa;
    private ArrayList<Item> itensEquipados;

    /*Preciso manter este objeto "CondicaoEspecialPersonagem" porque tem itens que são temporários e somem
      E para facilitar a organização e adminstração como por exemplo poções que dão 1 de habilidade por duas batalhas
      preciso excluir da bolsa já que não existe mais por ter sido consumido, mas o efeito continua e fica aqui neste
      objeto até o efeito acabar*/
    private final ArrayList<CondicaoEspecialPersonagem> condicaoEspecial = new ArrayList<>();

    public Personagem(String nome, int idLivro, int habilidadeMax, int energiaMax, int sorteMax,
                      ArrayList<Item> bolsa,ArrayList<Item> itensEquipados, int genero) {
        this.nome = nome;
        this.idLivro = idLivro;
        this.habilidadeMax = habilidadeMax;
        this.energiaMax = energiaMax;
        this.sorteMax = sorteMax;
        this.habilidadeAtual = habilidadeMax;
        this.energiaAtual = energiaMax;
        this.sorteAtual = sorteMax;
        this.bolsa = bolsa;
        this.itensEquipados = itensEquipados;
        this.genero = genero;
    }

    public int getGenero() {
        return genero;
    }

    public int getQuantidadeOuro() {
        return quantidadeOuro;
    }

    public void setQuantidadeOuro(int quantidadeOuro) {
        this.quantidadeOuro = quantidadeOuro;
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
        return itensEquipados;
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
        //Não segue a restrição do índice máximo ser igual ao da criação do personagem devido a seção 70
        //Que dá a espada magnífica(10) que aumenta em 2 pontos a habilidade atual
        this.habilidadeAtual = habilidadeAtual;
    }

    public void setEnergiaAtual(int energiaAtual) {

        //Regra: Personagem não pode ter índice maior que a calculada na sua criação
        if (energiaAtual >= energiaMax)
            this.energiaAtual = energiaMax;
        else
            this.energiaAtual = energiaAtual;
    }

    public void setSorteAtual(int sorteAtual) {

        //Regra: Personagem não pode ter índice maior que a calculada na sua criação
        if (sorteAtual >= sorteMax )
            this.sorteAtual = sorteMax;
        else
            this.sorteAtual = sorteAtual;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "nome='" + nome + '\'' +"\n\n"+
                ", idLivro=" + idLivro +"\n\n"+
                ", habilidadeMax=" + habilidadeMax +"\n\n"+
                ", energiaMax=" + energiaMax +"\n\n"+
                ", sorteMax=" + sorteMax +"\n\n"+
                ", habilidadeAtual=" + habilidadeAtual +"\n\n"+
                ", energiaAtual=" + energiaAtual +"\n\n"+
                ", sorteAtual=" + sorteAtual +"\n\n"+
                ", anotacoes='" + anotacoes + '\'' +"\n\n"+
                ", bolsa=" + bolsa +"\n"+"\n\n"+
                ", itensEquipados=" + itensEquipados +"\n"+"\n\n"+
                ", condicaoEspecial= " + condicaoEspecial +"\n\n"+
                ", Ouro carregado= " + quantidadeOuro +
                '}';
    }
}
