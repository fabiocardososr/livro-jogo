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
        //carregarPainelEnergia(390,10,370,300);
        //carregarPainelSorte(770,10,370,300);

    }

    private void carregarPainelHabilidade(int horizontal, int vertical, int largura, int altura) {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeHabilidade = new ImagePanel();
        painelDeHabilidade.setBounds(horizontal,vertical,largura,altura);

        //Título
        JLabel labelTitulo = new JLabel("Habilidade");
        labelTitulo.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelTitulo.setForeground(new Color(139,69,19));
        labelTitulo.setBounds(135,13,150,50);

        //Info
        JLabel labelInfo = new JLabel("<html><center>Será rolado um dado e somado 6.<br>Este será o seu índice de HABILIDADE</center></html>");
        labelInfo.setFont(new Font(Font.SERIF,Font.BOLD,17));
        labelInfo.setForeground(new Color(165,42,42));
        labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfo.setBounds(20,50,370,100);

        //Índice de Habilidade
        JLabel labelIndiceHabilidade = new JLabel("0");
        labelIndiceHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceHabilidade.setForeground(new Color(160,82,45));
        labelIndiceHabilidade.setBounds(13,130,370,50);
        labelIndiceHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        JButton botaoRolarDadoHabilidade = new JButton("Rolar");
        botaoRolarDadoHabilidade.setBackground(new Color(210,180,140));
        botaoRolarDadoHabilidade.setForeground(new Color(139,69,19));
        botaoRolarDadoHabilidade.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadoHabilidade.setBounds(138,200,120,40);
        botaoRolarDadoHabilidade.setFocusable(false);
        botaoRolarDadoHabilidade.setBorder(BorderFactory.createLineBorder(new Color(210,105,30)));


        add(labelTitulo);
        add(labelIndiceHabilidade);
        add(labelInfo);
        add(botaoRolarDadoHabilidade);
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
