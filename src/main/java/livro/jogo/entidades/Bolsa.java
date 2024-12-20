package livro.jogo.entidades;

public class Bolsa {
    private int idPersonagem;
    private int idItem;
    private int quantidadeItem;

    public Bolsa(int idPersonagem, int idItem, int quantidadeItem) {
        this.idPersonagem = idPersonagem;
        this.idItem = idItem;
        this.quantidadeItem = quantidadeItem;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }
}
