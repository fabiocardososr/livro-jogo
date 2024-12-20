package livro.jogo.entidades;

import java.util.ArrayList;

public class Personagem {
    private int idPersonagem;
    private String nome;
    private int idLivro;
    private int habilidadeMax;
    private int energiaMax;
    private int sorteMax;
    private int habilidadeAtual;
    private int energiaAtual;
    private int sorteAtual;
    private String anotacoes;
    private final ArrayList<Bolsa> bolsa = new ArrayList<>();
    private final ArrayList<ItensEquipado> ItensEquipados = new ArrayList<>();
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

    public int getIdPersonagem() {
        return idPersonagem;
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

    public ArrayList<Bolsa> getBolsa() {
        return bolsa;
    }

    public ArrayList<ItensEquipado> getItensEquipados() {
        return ItensEquipados;
    }

    public ArrayList<CondicaoEspecialPersonagem> getCondicaoEspecial() {
        return condicaoEspecial;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
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
