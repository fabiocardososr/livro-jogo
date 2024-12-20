package livro.jogo.entidades;

public class CondicaoEspecialPersonagem {
    private int idPersonagem;
    private int idItemAtivado;
    private int quantidadeUso;//Se efeito temporário, é o número de ativações do efeito. Caso permanente fica -1.

    public CondicaoEspecialPersonagem(int idPersonagem, int idItemAtivado, int quantidadeUso) {
        this.idPersonagem = idPersonagem;
        this.idItemAtivado = idItemAtivado;
        this.quantidadeUso = quantidadeUso;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public int getIdItemAtivado() {
        return idItemAtivado;
    }

    public void setIdItemAtivado(int idItemAtivado) {
        this.idItemAtivado = idItemAtivado;
    }

    public int getQuantidadeUso() {
        return quantidadeUso;
    }

    public void setQuantidadeUso(int quantidadeUso) {
        this.quantidadeUso = quantidadeUso;
    }
}
