package livro.jogo.telas.desktop;

import livro.jogo.utils.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TelaCriarPersonagem extends Tela {

    public TelaCriarPersonagem(int largura, int altura) {
        super(largura, altura);

        carregarComponentesDaTela();
    }

    private void carregarComponentesDaTela() {
        carregarPainelHabilidade(10,10,370,300);
        carregarPainelEnergia(390,10,370,300);
        carregarPainelSorte(770,10,370,300);

    }

    private void carregarPainelHabilidade(int horizontal, int vertical, int largura, int altura) {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeHabilidade = new ImagePanel();
        painelDeHabilidade.setBackground(Color.BLACK);
        painelDeHabilidade.setBounds(horizontal,vertical,largura,altura);

        //Título
        JLabel labelTitulo = new JLabel("Habilidade");
        labelTitulo.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setBounds(1,1,370,20);
        painelDeHabilidade.add(labelTitulo);

        add(painelDeHabilidade);
    }

    private void carregarPainelEnergia(int horizontal, int vertical, int largura, int altura) {

        JPanel painelDeEnergia = new JPanel();
        painelDeEnergia.setBackground(Color.darkGray);
        painelDeEnergia.setBounds(horizontal,vertical,largura,altura);
        add(painelDeEnergia);
    }

    private void carregarPainelSorte(int horizontal, int vertical, int largura, int altura) {

        JPanel painelDeSorte = new JPanel();
        painelDeSorte.setBackground(Color.darkGray);
        painelDeSorte.setBounds(horizontal,vertical,largura,altura);
        add(painelDeSorte);
    }

}
