package livro.jogo.telas.desktop;

import livro.jogo.Personagens.CriacaoPersonagem;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.utils.ImagePanel;
import livro.jogo.utils.ManipularDados;
import livro.jogo.utils.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;


public class TelaCriarPersonagem extends Tela {
    private JButton botaoRolarDadoHabilidade;
    private JButton botaoRolarDadosEnergia;
    private JButton botaoRolarDadosSorte;
    private JButton botaoGravar;
    private JButton botaoResetar;
    private JLabel labelIndiceHabilidade;
    private JLabel labelIndiceEnergia;
    private JLabel labelIndiceSorte;
    private JTextField txtNome;
    private int habilidadeInicial = 0;
    private int energiaInicial = 0;
    private int sorteInicial = 0;
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private JList<String> listaPocoes;
    private Item pocaoEscolhida;

    public TelaCriarPersonagem(int largura, int altura) {
        super(largura, altura);

        carregarComponentesDaTela();
    }

    private void carregarComponentesDaTela() {
        carregarPainelHabilidade();
        carregarPainelEnergia();
        carregarPainelSorte();
        carregarBotoesGravarResetar();
        carregarTxtNome();
        carregarListaDePocoes();
    }

    private void carregarListaDePocoes() {
        String[] pocoes = {"Poção da Habilidade","Poção de Força", "Poção da Fortuna"};
        int[] codPocoes = {45,46,47};
        listaPocoes = new JList<String>(pocoes);
        listaPocoes.setBackground(Color.RED);
        listaPocoes.setForeground(Color.WHITE);
        listaPocoes.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        listaPocoes.setBounds(30, 420,300,100);
        listaPocoes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                var pocao = codPocoes[listaPocoes.getSelectedIndex()];
                JOptionPane.showMessageDialog(null, "pocao: "+pocao);
            }
        });
        add(listaPocoes);


        //Continuar depois que criar a lista de itens Json

    }

    private void carregarTxtNome() {
        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        labelNome.setBounds(30, 300,100,60);
        labelNome.setForeground(Color.WHITE);

        txtNome = new JTextField();
        txtNome.setBounds(30, 350,250,60);
        txtNome.setBackground(Color.darkGray);
        txtNome.setForeground(Color.WHITE);
        txtNome.setFont(new Font(Font.SERIF,Font.PLAIN,25));
        txtNome.setCursor(cursor);
        add(txtNome);
        add(labelNome);
    }

    private void carregarBotoesGravarResetar() {
        botaoGravar = new JButton("Gerar Personagem");
        botaoGravar.setBackground(Color.BLACK);
        botaoGravar.setForeground(Color.WHITE);
        botaoGravar.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoGravar.setBounds(250, 700,300,50);
        botaoGravar.setFocusable(false);
        botaoGravar.setEnabled(false);
        botaoGravar.setCursor(cursor);
        botaoGravar.addActionListener(e -> {
            if (txtNome.getText().length()<3){
                JOptionPane.showMessageDialog(null,"Por favor digite um nome com ao menos 3 caracteres.");
                return;
            }
            carregarPersonagem();
        });
        add(botaoGravar);

        botaoResetar = new JButton("Refazer Personagem");
        botaoResetar.setBackground(Color.BLACK);
        botaoResetar.setForeground(Color.WHITE);
        botaoResetar.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoResetar.setBounds(650, 700,300,50);
        botaoResetar.setFocusable(false);
        botaoResetar.setCursor(cursor);
        botaoResetar.addActionListener(e -> {
            resetarCriacaoPersonagem();
        });
        add(botaoResetar);
    }

    private void carregarPainelHabilidade() {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeHabilidade = new ImagePanel();
        painelDeHabilidade.setBounds(10,10,370,300);

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
        labelIndiceHabilidade = new JLabel("0");
        labelIndiceHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceHabilidade.setForeground(new Color(160,82,45));
        labelIndiceHabilidade.setBounds(13,130,370,50);
        labelIndiceHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadoHabilidade = new JButton("Rolar Dado");
        botaoRolarDadoHabilidade.setBackground(new Color(210,180,140));
        botaoRolarDadoHabilidade.setForeground(new Color(139,69,19));
        botaoRolarDadoHabilidade.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadoHabilidade.setBounds(138,200,120,40);
        botaoRolarDadoHabilidade.setFocusable(false);
        botaoRolarDadoHabilidade.setCursor(cursor);
        botaoRolarDadoHabilidade.setBorder(BorderFactory.createLineBorder(new Color(210,105,30)));
        botaoRolarDadoHabilidade.addActionListener(e -> {
            habilidadeInicial = Util.obterIndiceHabilidadeOuSorteInicial();
            labelIndiceHabilidade.setText(String.valueOf(habilidadeInicial));
            desabilitarBotoes(botaoRolarDadoHabilidade);
            habilitarBotaoGravar();
        });

        add(labelTituloHabilidade);
        add(labelIndiceHabilidade);
        add(labelInfoHabilidade);
        add(botaoRolarDadoHabilidade);
        add(painelDeHabilidade);
    }

    private void carregarPainelEnergia() {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeEnergia = new ImagePanel();
        painelDeEnergia.setBounds(390,10,370,300);

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
        labelIndiceEnergia = new JLabel("0");
        labelIndiceEnergia.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceEnergia.setForeground(new Color(160,82,45));
        labelIndiceEnergia.setBounds(403,130,370,50);
        labelIndiceEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadosEnergia = new JButton("Rolar Dados");
        botaoRolarDadosEnergia.setBackground(new Color(210,180,140));
        botaoRolarDadosEnergia.setForeground(new Color(139,69,19));
        botaoRolarDadosEnergia.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadosEnergia.setBounds(530,200,120,40);
        botaoRolarDadosEnergia.setFocusable(false);
        botaoRolarDadosEnergia.setCursor(cursor);
        botaoRolarDadosEnergia.setBorder(BorderFactory.createLineBorder(new Color(210,105,30)));
        botaoRolarDadosEnergia.addActionListener(e -> {
            energiaInicial = Util.obterIndiceEnergiaInicial();
            labelIndiceEnergia.setText(String.valueOf(energiaInicial));
            desabilitarBotoes(botaoRolarDadosEnergia);
            habilitarBotaoGravar();
        });

        add(labelTituloEnergia);
        add(labelIndiceEnergia);
        add(labelInfoEnergia);
        add(botaoRolarDadosEnergia);
        add(painelDeEnergia);
    }

    private void carregarPainelSorte() {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeSorte = new ImagePanel();
        painelDeSorte.setBounds(770,10,370,300);

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
        labelIndiceSorte = new JLabel("0");
        labelIndiceSorte.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceSorte.setForeground(new Color(160,82,45));
        labelIndiceSorte.setBounds(790,130,370,50);
        labelIndiceSorte.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadosSorte = new JButton("Rolar Dado");
        botaoRolarDadosSorte.setBackground(new Color(210,180,140));
        botaoRolarDadosSorte.setForeground(new Color(139,69,19));
        botaoRolarDadosSorte.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadosSorte.setBounds(915,200,120,40);
        botaoRolarDadosSorte.setFocusable(false);
        botaoRolarDadosSorte.setCursor(cursor);
        botaoRolarDadosSorte.setBorder(BorderFactory.createLineBorder(new Color(210,105,30)));
        botaoRolarDadosSorte.addActionListener(e -> {
            sorteInicial = Util.obterIndiceHabilidadeOuSorteInicial();
            labelIndiceSorte.setText(String.valueOf(sorteInicial));
            desabilitarBotoes(botaoRolarDadosSorte);
            habilitarBotaoGravar();
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

    private void resetarCriacaoPersonagem(){
        habilidadeInicial = 0;
        energiaInicial    = 0;
        sorteInicial      = 0;
        labelIndiceHabilidade.setText(String.valueOf(habilidadeInicial));
        labelIndiceEnergia.setText(String.valueOf(energiaInicial));
        labelIndiceSorte.setText(String.valueOf(sorteInicial));
        txtNome.setText("");

        botaoRolarDadoHabilidade.setEnabled(true);
        botaoRolarDadosEnergia.setEnabled(true);
        botaoRolarDadosSorte.setEnabled(true);
        botaoGravar.setEnabled(false);
    }

    private void habilitarBotaoGravar(){
        if ( (habilidadeInicial != 0) && (energiaInicial != 0) && (sorteInicial != 0) ) {
            botaoGravar.setEnabled(true);
            txtNome.setFocusable(true);
        }
        else
            botaoGravar.setEnabled(false);
    }

    private void carregarPersonagem(){
        var nome = txtNome.getText();
        var idLivro = ManipularDados.getLivro().getIdLivro();
        CriacaoPersonagem criacaoPersonagem = new CriacaoPersonagem(nome, idLivro, habilidadeInicial,energiaInicial,sorteInicial,pocaoEscolhida);
        Personagem personagem = criacaoPersonagem.criarPersonagem();

        System.out.println(personagem);

    }

}
