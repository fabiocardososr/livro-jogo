/*Esta classe indica os itens que foram consumidos
  ou em uso que mantém efeito permanente ou temporário*/
package livro.jogo.entidades;

public class CondicaoEspecialPersonagem {
    private Item item;

    public CondicaoEspecialPersonagem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
