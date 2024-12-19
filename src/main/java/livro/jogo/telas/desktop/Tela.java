package livro.jogo.telas.desktop;


import javax.swing.*;
import java.awt.*;


public class Tela extends JFrame {

    public Tela(int largura, int altura)  {
        Container principal = getContentPane();
        setLayout(null);
        setResizable(false);
        principal.setBackground(Color.BLACK);
        setSize(largura,altura);
        setLocationRelativeTo(null);
    }
}
