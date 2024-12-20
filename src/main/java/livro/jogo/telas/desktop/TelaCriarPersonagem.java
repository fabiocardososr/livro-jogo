package livro.jogo.telas.desktop;

import livro.jogo.utils.ImagePanel;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaCriarPersonagem extends Tela {
    private JButton botaoRolarDadoHabilidade;
    private JButton botaoRolarDadosEnergia;
    private JButton botaoRolarDadosSorte;
    private int habilidadeInicial;
    private int energiaInicial;
    private int sorteInicial;

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
        painelDeHabilidade.setBounds(horizontal,vertical,largura,altura);

        //Título
        JLabel labelTituloHabilidade = new JLabel("Habilidade");
        labelTituloHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelTituloHabilidade.setForeground(new Color(139,69,19));
        labelTituloHabilidade.setBounds(135,13,150,50);

        //Info
        JLabel labelInfoHabilidade = new JLabel("<html><center>Será rolado um dado e somado 6.<br>Este será o seu índice de HABILIDADE</center></html>");
        labelInfoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,17));
        labelInfoHabilidade.setForeground(new Color(165,42,42));
        labelInfoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoHabilidade.setBounds(20,50,370,100);

        //Índice de Habilidade
        JLabel labelIndiceHabilidade = new JLabel("0");
        labelIndiceHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceHabilidade.setForeground(new Color(160,82,45));
        labelIndiceHabilidade.setBounds(13,130,370,50);
        labelIndiceHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadoHabilidade = new JButton("Rolar");
        botaoRolarDadoHabilidade.setBackground(new Color(210,180,140));
        botaoRolarDadoHabilidade.setForeground(new Color(139,69,19));
        botaoRolarDadoHabilidade.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadoHabilidade.setBounds(138,200,120,40);
        botaoRolarDadoHabilidade.setFocusable(false);
        botaoRolarDadoHabilidade.setBorder(BorderFactory.createLineBorder(new Color(210,105,30)));
        botaoRolarDadoHabilidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habilidadeInicial = Util.obterIndiceHabilidadeOuSorteInicial();
                labelIndiceHabilidade.setText(String.valueOf(habilidadeInicial));
                desabilitarBotoes(botaoRolarDadoHabilidade);
            }
        });

        add(labelTituloHabilidade);
        add(labelIndiceHabilidade);
        add(labelInfoHabilidade);
        add(botaoRolarDadoHabilidade);
        add(painelDeHabilidade);
    }

    private void carregarPainelEnergia(int horizontal, int vertical, int largura, int altura) {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeEnergia = new ImagePanel();
        painelDeEnergia.setBounds(horizontal,vertical,largura,altura);

        //Título
        JLabel labelTituloEnergia = new JLabel("Energia");
        labelTituloEnergia.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelTituloEnergia.setForeground(new Color(139,69,19));
        labelTituloEnergia.setBounds(525,13,150,50);

        //Info
        JLabel labelInfoEnergia = new JLabel("<html><center>Será rolado dois dado e somado 12.<br>Este será o seu índice de ENERGIA</center></html>");
        labelInfoEnergia.setFont(new Font(Font.SERIF,Font.BOLD,17));
        labelInfoEnergia.setForeground(new Color(165,42,42));
        labelInfoEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoEnergia.setBounds(390,50,370,100);

        //Índice de Habilidade
        JLabel labelIndiceEnergia = new JLabel("0");
        labelIndiceEnergia.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceEnergia.setForeground(new Color(160,82,45));
        labelIndiceEnergia.setBounds(403,130,370,50);
        labelIndiceEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadosEnergia = new JButton("Rolar");
        botaoRolarDadosEnergia.setBackground(new Color(210,180,140));
        botaoRolarDadosEnergia.setForeground(new Color(139,69,19));
        botaoRolarDadosEnergia.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadosEnergia.setBounds(530,200,120,40);
        botaoRolarDadosEnergia.setFocusable(false);
        botaoRolarDadosEnergia.setBorder(BorderFactory.createLineBorder(new Color(210,105,30)));
        botaoRolarDadosEnergia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                energiaInicial = Util.obterIndiceEnergiaInicial();
                labelIndiceEnergia.setText(String.valueOf(energiaInicial));
                desabilitarBotoes(botaoRolarDadosEnergia);
            }
        });

        add(labelTituloEnergia);
        add(labelIndiceEnergia);
        add(labelInfoEnergia);
        add(botaoRolarDadosEnergia);
        add(painelDeEnergia);
    }

    private void carregarPainelSorte(int horizontal, int vertical, int largura, int altura) {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeSorte = new ImagePanel();
        painelDeSorte.setBounds(horizontal,vertical,largura,altura);

        //Título
        JLabel labelTituloSorte = new JLabel("Sorte");
        labelTituloSorte.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelTituloSorte.setForeground(new Color(139,69,19));
        labelTituloSorte.setBounds(925,13,150,50);

        //Info
        JLabel labelInfoSorte = new JLabel("<html><center>Será rolado um dado e somado 6.<br>Este será o seu índice de SORTE</center></html>");
        labelInfoSorte.setFont(new Font(Font.SERIF,Font.BOLD,17));
        labelInfoSorte.setForeground(new Color(165,42,42));
        labelInfoSorte.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoSorte.setBounds(780,50,370,100);

        //Índice de Habilidade
        JLabel labelIndiceSorte = new JLabel("0");
        labelIndiceSorte.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceSorte.setForeground(new Color(160,82,45));
        labelIndiceSorte.setBounds(790,130,370,50);
        labelIndiceSorte.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadosSorte = new JButton("Rolar");
        botaoRolarDadosSorte.setBackground(new Color(210,180,140));
        botaoRolarDadosSorte.setForeground(new Color(139,69,19));
        botaoRolarDadosSorte.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadosSorte.setBounds(915,200,120,40);
        botaoRolarDadosSorte.setFocusable(false);
        botaoRolarDadosSorte.setBorder(BorderFactory.createLineBorder(new Color(210,105,30)));
        botaoRolarDadosSorte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorteInicial = Util.obterIndiceHabilidadeOuSorteInicial();
                labelIndiceSorte.setText(String.valueOf(sorteInicial));
                desabilitarBotoes(botaoRolarDadosSorte);
            }
        });

        add(labelTituloSorte);
        add(labelIndiceSorte);
        add(labelInfoSorte);
        add(botaoRolarDadosSorte);
        add(painelDeSorte);
    }

    private void desabilitarBotoes(JButton botao){
        botao.setEnabled(false);
    }

    private void habilitarBotoes(){
        botaoRolarDadoHabilidade.setEnabled(true);
    }

}
