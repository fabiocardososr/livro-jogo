package livro.jogo.telas.desktop.personalizados.util;

import livro.jogo.utils.ManipularArquivos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RedimensionarImagem {
    private ImageIcon imageIcon;

    public RedimensionarImagem(String enderecoImagem, int largura, int altura) {
        try { //Imagem da seção no label redimensionada, pois existem imagens maiores que a dimensão do label

            // Lê a imagem como recurso do JAR
//            URL url = getClass().getResource(enderecoImagem);
//            System.out.println("URL da imagem: " + url + " endereco: "+ enderecoImagem);
            InputStream input = RedimensionarImagem.class.getClassLoader()
                    .getResourceAsStream(enderecoImagem);
            if (input == null) {
                throw new RuntimeException("Recurso não encontrado: " + enderecoImagem);
            }

            BufferedImage img = ImageIO.read(input);
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
