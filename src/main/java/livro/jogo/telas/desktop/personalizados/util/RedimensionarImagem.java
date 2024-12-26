package livro.jogo.telas.desktop.personalizados.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RedimensionarImagem {
    private ImageIcon imageIcon;

    public RedimensionarImagem(String enderecoImagem, int largura, int altura) {
        try { //Imagem da seção no label redimensionada, pois existem imagens maiores que a dimensão do label
            BufferedImage img = ImageIO.read(new File(enderecoImagem));
            Image imgDimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            this.imageIcon = new ImageIcon(imgDimensionada);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Icon getImageIcon() {
        return imageIcon;
    }
}
