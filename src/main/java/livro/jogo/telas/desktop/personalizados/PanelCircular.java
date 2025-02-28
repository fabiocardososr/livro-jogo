package livro.jogo.telas.desktop.personalizados;

import javax.swing.*;
import java.awt.*;

public class PanelCircular extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        // Define a cor de fundo
        g.setColor(getBackground());
        // Preenche um círculo com a cor de fundo
        g.fillOval(0, 0, getWidth(), getHeight());
    }

    @Override
    public Dimension getPreferredSize() {
        // Define o tamanho preferencial do painel
        return new Dimension(100, 100);
    }

    @Override
    public boolean contains(int x, int y) {
        // Define a área clicável como um círculo
        int radius = Math.min(getWidth(), getHeight()) / 2;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= radius * radius;
    }
}
