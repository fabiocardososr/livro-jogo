/*Esta classe indica os itens que foram consumidos
  ou em uso que mantém efeito permanente ou temporário*/
package livro.jogo.Personagens;

import livro.jogo.entidades.Item;

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
