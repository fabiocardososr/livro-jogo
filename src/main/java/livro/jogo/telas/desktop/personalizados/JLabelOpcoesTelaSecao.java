package livro.jogo.telas.desktop.personalizados;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JLabelOpcoesTelaSecao extends JLabel {
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);


    public JLabelOpcoesTelaSecao(String texto, ImagensDoLivroFlorestaDaDestruicao enderecoImagem) {
        super(texto);
        setForeground(Color.WHITE);
        //setBackground(Color.BLACK);
        setFont(new Font(Font.SERIF,Font.BOLD,25));
        setIcon(dimensionarImagem(80,85, enderecoImagem.getEnderecoImagem()));
        setForeground(new Color(160,0,0));
        setBackground(new Color(210,180,140));
        setVerticalTextPosition(SwingConstants.CENTER);
        setCursor(cursor);
    }

    //Caso a imagem seja maior que o label (por exemplo) redimensionar de modo caber no componente
    private ImageIcon dimensionarImagem(int largura, int altura, String enderecoImagem){
        ImageIcon imageIcon;
        try {
            BufferedImage img = ImageIO.read(new File(enderecoImagem));
            Image imgDimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(imgDimensionada);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imageIcon;
    }

}
