package livro.jogo.telas.desktop.personalizados.util;

/*
* Classe que representa a lista de itens para escolha, como exemplo na
* seção 12 onde é necessário listar itens para serem escolhidos
* */

import javax.swing.*;

public class ListItem {
    private int idItem;
    private String nomeItem;
    private Icon imagemItem;
    private int valorCusto;

    public ListItem(int idItem, String nomeItem, Icon imagemItem) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.imagemItem = imagemItem;
    }

    public ListItem(int idItem, String nomeItem, Icon imagemItem, int valorCusto) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.imagemItem = imagemItem;
        this.valorCusto = valorCusto;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public Icon getImagemItem() {
        return imagemItem;
    }

    public int getValorCusto() {
        return valorCusto;
    }
}
