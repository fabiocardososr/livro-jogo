package livro.jogo.telas.desktop;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        Container principal = getContentPane();
        setLayout(null);
        setResizable(false);
        setFocusable(false);
        principal.setBackground(Color.BLACK);
        setTitle("Livro Jogo - Floresta da Destruição");
        carregarIconTela();
        configurandoTextoCapaLivro();
    }

    private void carregarIconTela(){
        BufferedImage img;
        try {
            img = ImageIO.read(new File("imagens/capalivro.png"));
            setIconImage(img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private void configurandoTextoCapaLivro(){


        JLabel labelImgCapaLivro = new JLabel();
        labelImgCapaLivro.setIcon( new ImageIcon("imagens/capalivro.png"));



        //Configurando o "tituloCapaLivro"
        JLabel tituloCapaLivro = new JLabel("A FLORESTA DA DESTRUIÇÃO");
        tituloCapaLivro.setForeground(Color.WHITE);
        tituloCapaLivro.setFont(new Font(Font.SERIF,Font.PLAIN,25));

        //Configuração do estilo "textoCapaLivro"
        JTextPane textoCapaLivro = new JTextPane();
        textoCapaLivro.setBackground(Color.BLACK);
        StyledDocument textoCapaLivroStyle = textoCapaLivro.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(configTexto,25);
        StyleConstants.setForeground(configTexto,Color.WHITE);
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoCapaLivro.setEditable(false);
        textoCapaLivro.setText("\t\t\t\tSomente os imprudentes ou os muito corajosos"+
                " se arriscariam de livre e espontânea vontade a uma jornada pela Floresta de Darkwood, onde trilhas"+
                " estranhas e tortuosas serpenteiam, penetrando em sinistras profundezas. Quem sabe que criaturas"+
                " monstruosas espreitam em meio às sombras ameaçadoras, ou que aventuras fatais esperam o viajante"+
                " desavisado? VOCÊ se atreve a entrar?\n\n\t\t\t\tNuma corrida desesperada contra o tempo, sua missão é"+
                " encontrar, no interior de Darkwood, os pedaços perdidos do lendário Martelo de Stonebridge, o qual"+
                " foi fabricado pelos Anões para proteger a pacífica Stonebridge de uma antiga maldição.\n\n\t\t\t\tHá muitos"+
                " perigos à sua frente, e seu sucesso não está de modo nenhum garantido. Adversários poderosos estão"+
                " mobilizados contra você e, muitas vezes, sua única escolha será matar ou morrer!");




        //Posicionanado
        labelImgCapaLivro.setBounds(0,0,490,760);
        tituloCapaLivro.setBounds(850,10,800,100);
        textoCapaLivro.setBounds(500,100,950,600);

        //Adicionando a tela
        add(tituloCapaLivro);
        add(textoCapaLivro);
        add(labelImgCapaLivro);
    }

}
