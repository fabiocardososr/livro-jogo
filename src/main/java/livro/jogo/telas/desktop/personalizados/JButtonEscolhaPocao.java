package livro.jogo.telas.desktop.personalizados;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class JButtonEscolhaPocao extends JButton {
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);


    public JButtonEscolhaPocao(String texto, ImagensDoLivroFlorestaDaDestruicao enderecoImagem) {
        super(texto);
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setFont(new Font(Font.SERIF,Font.BOLD,20));
        setIcon(dimensionarImagem(40,45, enderecoImagem.getEnderecoImagem()));
        setForeground(new Color(139,0,0));
        setBackground(new Color(210,180,140));
        setVerticalTextPosition(SwingConstants.CENTER);
        setCursor(cursor);
    }

    //Caso a imagem seja maior que o label (por exemplo) redimensionar de modo caber no componente
    private ImageIcon dimensionarImagem(int largura, int altura, String enderecoImagem){
        ImageIcon imageIcon;
        try {
            InputStream inputStream = JButtonEscolhaPocao.class.getClassLoader().getResourceAsStream(enderecoImagem);
            BufferedImage img = ImageIO.read(inputStream);
            Image imgDimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(imgDimensionada);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imageIcon;
    }
}
