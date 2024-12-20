package livro.jogo.entidades;

public class ItensEquipado {
    private int idItem;
    private int idPersonagem;
    private int quantidadeItem;

    public ItensEquipado(int idItem, int idPersonagem, int quantidadeItem) {
        this.idItem = idItem;
        this.idPersonagem = idPersonagem;
        this.quantidadeItem = quantidadeItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }
}
