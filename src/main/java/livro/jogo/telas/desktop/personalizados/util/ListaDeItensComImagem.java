package livro.jogo.telas.desktop.personalizados.util;

import javax.swing.*;
import java.awt.*;

public class ListaDeItensComImagem extends JLabel implements ListCellRenderer<ListItem> {
    public ListaDeItensComImagem() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ListItem> list, ListItem value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {



        // Define o texto e o ícone
        setText(value.getNomeItem()+ "      "+value.getValorCusto()+" peças de ouro");
        setIcon(value.getImagemItem());
        setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));

        // Define a cor de fundo e de texto com base na seleção
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
