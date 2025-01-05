package livro.jogo.telas.desktop.personalizados;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.utils.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JLabelOpcoesTelaSecao extends JLabel {
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private int largura;
    private int altura;

    public JLabelOpcoesTelaSecao(String texto, int largura, int altura, String enderecoImagem) {
        super(texto);
        this.largura = largura;
        this.altura = altura;
        setForeground(Color.WHITE);
        //setBackground(Color.BLACK);
        setFont(new Font(Font.SERIF,Font.BOLD,23));
        setIcon(Util.dimensionarImagem(largura,altura, enderecoImagem));
        setForeground(new Color(139,0,0));
        setBackground(new Color(210,180,140));
        setVerticalTextPosition(SwingConstants.CENTER);
        setCursor(cursor);
    }

    public JLabelOpcoesTelaSecao(String texto, int largura, int altura, ImagensDoLivroFlorestaDaDestruicao enderecoImagem) {
        super(texto);
        this.largura = largura;
        this.altura = altura;
        setForeground(Color.WHITE);
        //setBackground(Color.BLACK);
        setFont(new Font(Font.SERIF,Font.BOLD,23));
        setIcon(Util.dimensionarImagem(largura,altura, enderecoImagem.getEnderecoImagem()));
        setForeground(new Color(139,0,0));
        setBackground(new Color(210,180,140));
        setVerticalTextPosition(SwingConstants.CENTER);
        setCursor(cursor);
    }

    public void setImagem(ImagensDoLivroFlorestaDaDestruicao enderecoImagem){
        setIcon(Util.dimensionarImagem(largura,altura, enderecoImagem.getEnderecoImagem()));
    }

    public JLabelOpcoesTelaSecao(String texto, ImagensDoLivroFlorestaDaDestruicao enderecoImagem) {
        super(texto);
        setForeground(Color.WHITE);
        //setBackground(Color.BLACK);
        setFont(new Font(Font.SERIF,Font.BOLD,22));
        setIcon(Util.dimensionarImagem(80,85, enderecoImagem.getEnderecoImagem()));
        setForeground(new Color(160,0,0));
        setBackground(new Color(210,180,140));
        setVerticalTextPosition(SwingConstants.CENTER);
        setCursor(cursor);
    }

    public JLabelOpcoesTelaSecao(String texto) {
        super(texto);
        setForeground(Color.WHITE);
        //setBackground(Color.BLACK);
        setFont(new Font(Font.SERIF,Font.BOLD,25));
        setForeground(new Color(160,0,0));
        setBackground(new Color(210,180,140));
        setVerticalTextPosition(SwingConstants.CENTER);
        setCursor(cursor);
    }



}
