package livro.jogo.entidades;

public class Inimigo {
    private int idInimigo;
    private String nome;
    private int habilidade;
    private int energia;

    public Inimigo() {
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

    @Override
    public String toString() {
        return "\nInimigo{" +
                "idInimigo=" + idInimigo +
                ", nome='" + nome + '\'' +
                ", habilidade=" + habilidade +
                ", energia=" + energia +
                '}';
    }
}
