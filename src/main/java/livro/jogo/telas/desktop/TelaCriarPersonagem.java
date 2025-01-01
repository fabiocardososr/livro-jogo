package livro.jogo.telas.desktop;

import livro.jogo.Personagens.CriacaoPersonagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.ImagePanel;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.utils.LimitarCampoJTextField;
import livro.jogo.utils.ManipularDadosLivro;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TelaCriarPersonagem extends TelaBasica {
    private JButton botaoRolarDadoHabilidade;
    private JButton botaoRolarDadosEnergia;
    private JButton botaoRolarDadosSorte;
    private JLabelOpcoesTelaSecao botaoGravar;
    private JLabelOpcoesTelaSecao botaoResetar;
    private JLabelOpcoesTelaSecao botaoFechar;
    private JLabelOpcoesTelaSecao botaoPocaoSorte;
    private JLabelOpcoesTelaSecao botaoPocaoEnergia;
    private JLabelOpcoesTelaSecao botaoPocaoHabilidade;
    private JLabel labelIndiceHabilidade;
    private JLabel labelIndiceEnergia;
    private JLabel labelIndiceSorte;
    private JLabel labelGravar;
    private JTextField txtNome;
    private JRadioButton rbMasculino;
    private JRadioButton rbFeminino;
    private ButtonGroup radioGrupoGenero;
    private int habilidadeInicial = 0;
    private int energiaInicial = 0;
    private int sorteInicial = 0;
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private int pocaoEscolhida; //Escolha da poção
    private int generoPersonagem; //1 = Masculino; 2 = Feminino
    private final TelaBasica telaPrincipal; //Usada para que esconda a tela mãe(principal) até que esta seja fechada e ai: telaMae.setVisible(true)
    private final TelaAcaoDosLabels acao = new TelaAcaoDosLabels();


    public TelaCriarPersonagem(int largura, int altura, TelaBasica telaPrincipal) {
        super(largura, altura);
        setUndecorated(true);
        this.telaPrincipal = telaPrincipal;
        this.telaPrincipal.setVisible(false); //Lembrar de enviar esta referência para a tela do início do jogo.
        getContentPane().setBackground(new Color(210,180,140));
        carregarComponentesDaTela();
    }

    private void carregarComponentesDaTela() {
        carregarPainelHabilidade();
        carregarPainelEnergia();
        carregarPainelSorte();
        carregarBotoesGravarResetarFechar();
        carregarTxtNomeGenero();
        carregarPainelInferior();
        carregarFaixasDasExtremidades(); //Faixas que ficam nas extremidades da janela
    }

    private void carregarFaixasDasExtremidades() {
        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-115,-100,300,250);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(958,-108,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(970,682,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-130,675,300,250);

        add(labelFaixaInferiorEsquerda);
        add(labelFaixaInferiorDireita);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaSuperiorEsquerda);
    }

    private void carregarPainelInferior() {

        //Painel inferior (imagem de pergaminho maior)
        ImagePanel painelInferior = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FUNDO_CAD_PERSONAGEM);
        painelInferior.setBounds(2,305,1130,470);

        //Opção de escolha de poções inicial
        carregaEscolhaPocao();

        //Mostra os itens iniciais que o personagem possui
        carregaItensIniciaisPersonagem();

        //Último a ser adicionado o painel inferior, senão ele fica por cima e cobre os outros componentes
        add(painelInferior);
    }

    private void carregaItensIniciaisPersonagem() {

        //Mão apontando
        JLabelOpcoesTelaSecao labelMao = new JLabelOpcoesTelaSecao(null,150,100,
                ImagensDoLivroFlorestaDaDestruicao.SETA_APONTA_DIREITA);
        labelMao.setFont(new Font(Font.SERIF,Font.BOLD,80));
        labelMao.setForeground(new Color(139,0,0));
        labelMao.setBounds(630, 530,120,50);
        labelMao.setHorizontalAlignment(SwingConstants.CENTER);
        //labelFlecha.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Imagem da Bolsa
        ImagePanel painelImgBolsa = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.BOLSA);
        painelImgBolsa.setBackground(new Color(210,180,140));
        painelImgBolsa.setForeground(new Color(139,0,0));
        painelImgBolsa.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelImgBolsa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelImgBolsa.setToolTipText("Carrega todos os itens que não estejam equipados");
        painelImgBolsa.setBounds(755,480,120,120);

        /*** Itens iniciais ***/

        //Fundo
        JLabelOpcoesTelaSecao labelFundoItensItensIniciais = new JLabelOpcoesTelaSecao(null,800,380,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_4);
        labelFundoItensItensIniciais.setBounds(270, 340, 800,390);
        labelFundoItensItensIniciais.setCursor(null);
        labelFundoItensItensIniciais.setHorizontalAlignment(SwingConstants.CENTER);
//        labelFundoItensItensIniciais.setToolTipText("Itens iniciais que estarão na sua bolsa ou equipados com você.");
//        labelFundoItensItensIniciais.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelFundoItensItensIniciais.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Espada
        ImagePanel imgPanelEspada = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.ESPADA_INICIAL);
        imgPanelEspada.setToolTipText("Espada simples usada nos combates.");
        imgPanelEspada.setBounds(510,460,75,75);
        imgPanelEspada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //imgPanelEspada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Armadura
        ImagePanel imgPanelArmadura = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.ARMADURA_DE_COURO_INICIAL);
        imgPanelArmadura.setToolTipText("Armadura de couro que o(a) protegerá durante a aventura.");
        imgPanelArmadura.setBounds(460,530,75,75);
        imgPanelArmadura.setCursor(new Cursor(Cursor.HAND_CURSOR));


        //Provisões (refeições)
        ImagePanel imgPanelProvisoes = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PROVISOES);
        imgPanelProvisoes.setBounds(530,530,75,75);
        imgPanelProvisoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imgPanelProvisoes.setToolTipText("<html>10 Provisões(refeições). Recompõe sua ENERGIA em 4 pontos.<br>Pode ser consumida em qualquer momento, exceto em combate.</html>");
        //imgPanelProvisões.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(imgPanelEspada);
        add(imgPanelArmadura);
        add(imgPanelProvisoes);
        add(labelMao);
        add(painelImgBolsa);
        add(labelFundoItensItensIniciais);
    }

    private void carregaEscolhaPocao() {
        JLabelOpcoesTelaSecao labelImgEscolhaPocao = new JLabelOpcoesTelaSecao(null,240,120,ImagensDoLivroFlorestaDaDestruicao.FAIXA_2);
        labelImgEscolhaPocao.setBounds(100, 340,240,120);
        labelImgEscolhaPocao.setHorizontalAlignment(SwingConstants.CENTER);
        //labelEscolhaPocao.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabel labelEscolhaPocao = new JLabel("Escolha");
        labelEscolhaPocao.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelEscolhaPocao.setForeground(new Color(139,0,0));
        labelEscolhaPocao.setBounds(120, 360,200,70);
        labelEscolhaPocao.setHorizontalAlignment(SwingConstants.CENTER);

        add(labelEscolhaPocao);
        add(labelImgEscolhaPocao);


        /********    POÇÃO HABILIDADE    ********/
        JLabelOpcoesTelaSecao labelImgHabilidade = new JLabelOpcoesTelaSecao("",30,
                35,ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_HABILIDADE);
        labelImgHabilidade.setCursor(cursor);
        labelImgHabilidade.setBounds(160,460,30,35);
        JLabel labelRotuloHabilidade = new JLabel("Habilidade");
        labelRotuloHabilidade.setBounds(190,450,150,50);
        labelRotuloHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelRotuloHabilidade.setVerticalTextPosition(SwingConstants.CENTER);
        labelRotuloHabilidade.setCursor(cursor);
        labelRotuloHabilidade.setForeground(new Color(139,0,0));
        botaoPocaoHabilidade = new JLabelOpcoesTelaSecao("Habilidade",200,100,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoPocaoHabilidade.setBounds(120, 430,200,100);
        botaoPocaoHabilidade.setToolTipText("Repõe os pontos de HABILIDADE.");
        botaoPocaoHabilidade.setVerticalTextPosition(SwingConstants.NORTH);
        botaoPocaoHabilidade.addMouseListener(acao);
        botaoPocaoHabilidade.setCursor(cursor);
        botaoPocaoHabilidade.addMouseListener(acao);
        add(labelRotuloHabilidade);
        add(labelImgHabilidade);
        add(botaoPocaoHabilidade);
        //botaoPocaoHabilidade.setBorder(BorderFactory.createLineBorder(Color.RED));


        /********    POCAO ENERGIA    ********/
        JLabelOpcoesTelaSecao labelImgEnergia = new JLabelOpcoesTelaSecao("",30,35,ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_HABILIDADE);
        labelImgEnergia.setCursor(cursor);
        labelImgEnergia.setBounds(160,550,30,35);
        JLabel labelRotuloEnergia = new JLabel("Energia");
        labelRotuloEnergia.setBounds(190,540,150,50);
        labelRotuloEnergia.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelRotuloEnergia.setVerticalTextPosition(SwingConstants.CENTER);
        labelRotuloEnergia.setCursor(cursor);
        labelRotuloEnergia.setForeground(new Color(139,0,0));
        botaoPocaoEnergia = new JLabelOpcoesTelaSecao("Energia",200,100,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoPocaoEnergia.setBounds(120, 520,200,100);
        botaoPocaoEnergia.setToolTipText("Repõe os pontos de ENERGIA.");
        botaoPocaoEnergia.setVerticalTextPosition(SwingConstants.NORTH);
        botaoPocaoEnergia.addMouseListener(acao);
        botaoPocaoEnergia.setCursor(cursor);
        add(labelRotuloEnergia);
        add(labelImgEnergia);
        add(botaoPocaoEnergia);

        /********    POCAO SORTE    ********/
        JLabelOpcoesTelaSecao labelImgSorte = new JLabelOpcoesTelaSecao("",30,35,
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_SORTE);
        labelImgSorte.setCursor(cursor);
        labelImgSorte.setBounds(160,640,30,35);
        JLabel labelRotuloSorte = new JLabel("Fortuna");
        labelRotuloSorte.setBounds(190,630,150,50);
        labelRotuloSorte.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelRotuloSorte.setVerticalTextPosition(SwingConstants.CENTER);
        labelRotuloSorte.setCursor(cursor);
        labelRotuloSorte.setForeground(new Color(139,0,0));
        botaoPocaoSorte = new JLabelOpcoesTelaSecao("Fortuna",200,100,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoPocaoSorte.setBounds(120, 610,200,100);
        botaoPocaoSorte.setToolTipText("Repõe os pontos de SORTE e acrescenta 1 à SORTE Inicial.");
        botaoPocaoSorte.setVerticalTextPosition(SwingConstants.NORTH);
        botaoPocaoSorte.addMouseListener(acao);
        botaoPocaoSorte.setCursor(cursor);
        add(labelImgSorte);
        add(labelRotuloSorte);
        add(botaoPocaoSorte);
    }

    private void carregarTxtNomeGenero() {
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(new Font(Font.SERIF,Font.BOLD,20));
        labelNome.setBounds(0, 280,1150,60);
        labelNome.setForeground(new Color(139,0,0));
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);

        txtNome = new JTextField();
        txtNome.setDocument(new LimitarCampoJTextField(10));
        txtNome.setBounds(450, 330,250,40);
        txtNome.setBackground(new Color(210,180,140));
        txtNome.setForeground(new Color(139,0,0));
        txtNome.setFont(new Font(Font.SERIF,Font.BOLD,25));
        txtNome.setCursor(cursor);
        txtNome.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtNome.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                habilitarBotaoGravar();
            }
        });

        //Gênero
        radioGrupoGenero = new ButtonGroup();

        rbMasculino = new JRadioButton("Masculino",false);
        rbMasculino.setBounds(705,325, 120,20);
        rbMasculino.setFont(new Font(Font.SERIF,Font.BOLD,20));
        rbMasculino.setCursor(cursor);
        rbMasculino.setBackground(new Color(210,180,140));
        rbMasculino.setForeground(new Color(139,0,0));
        rbMasculino.setFocusable(false);
        rbMasculino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generoPersonagem = 1;
                habilitarBotaoGravar();
            }
        });

        rbFeminino = new JRadioButton("Feminino",false);
        rbFeminino.setBounds(705,350, 120,20);
        rbFeminino.setCursor(cursor);
        rbFeminino.setFont(new Font(Font.SERIF,Font.BOLD,20));
        rbFeminino.setBackground(new Color(210,180,140));
        rbFeminino.setForeground(new Color(139,0,0));
        rbFeminino.setFocusable(false);
        rbFeminino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generoPersonagem = 2;
                habilitarBotaoGravar();
            }
        });
        radioGrupoGenero.add(rbMasculino);
        radioGrupoGenero.add(rbFeminino);

        //Adicionando a tela
        add(rbMasculino);
        add(rbFeminino);
        add(txtNome);
        add(labelNome);
    }

    private void carregarPainelHabilidade() {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeHabilidade = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FUNDO_CAD_PERSONAGEM_INDICES);
        painelDeHabilidade.setBounds(10,10,370,290);

        //Título
        JLabel labelTituloHabilidade = new JLabel("Habilidade");
        labelTituloHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelTituloHabilidade.setForeground(new Color(139,0,0));
        labelTituloHabilidade.setBounds(135,13,150,50);

        //Info
        JLabel labelInfoHabilidade = new JLabel("<html><center>Rola um dado e soma 6.<br>Será sua Habilidade</center></html>");
        labelInfoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,20));
        labelInfoHabilidade.setForeground(new Color(139,0,0));
        labelInfoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoHabilidade.setBounds(20,50,370,100);

        //Índice de Habilidade
        labelIndiceHabilidade = new JLabel("0");
        labelIndiceHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceHabilidade.setForeground(new Color(139,0,0));
        labelIndiceHabilidade.setBounds(13,130,370,50);
        labelIndiceHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadoHabilidade = new JButton("Rolar Dado");
        botaoRolarDadoHabilidade.setBackground(new Color(210,180,140));
        botaoRolarDadoHabilidade.setForeground(new Color(139,0,0));
        botaoRolarDadoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,20));
        botaoRolarDadoHabilidade.setBounds(138,200,120,40);
        botaoRolarDadoHabilidade.setFocusable(false);
        botaoRolarDadoHabilidade.setCursor(cursor);
        botaoRolarDadoHabilidade.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
        botaoRolarDadoHabilidade.addActionListener(e -> {
            TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
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
        ImagePanel painelDeEnergia = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FUNDO_CAD_PERSONAGEM_INDICES);
        painelDeEnergia.setBounds(390,10,370,290);

        //Título
        JLabel labelTituloEnergia = new JLabel("Energia");
        labelTituloEnergia.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelTituloEnergia.setForeground(new Color(139,0,0));
        labelTituloEnergia.setBounds(525,13,150,50);

        //Info
        JLabel labelInfoEnergia = new JLabel("<html><center>Será rolado dois dado e somado 12.<br>Este será o seu índice de ENERGIA</center></html>");
        labelInfoEnergia.setFont(new Font(Font.SERIF,Font.BOLD,17));
        labelInfoEnergia.setForeground(new Color(139,0,0));
        labelInfoEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoEnergia.setBounds(390,50,370,100);

        //Índice de Habilidade
        labelIndiceEnergia = new JLabel("0");
        labelIndiceEnergia.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceEnergia.setForeground(new Color(139,0,0));
        labelIndiceEnergia.setBounds(403,130,370,50);
        labelIndiceEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadosEnergia = new JButton("Rolar Dados");
        botaoRolarDadosEnergia.setBackground(new Color(210,180,140));
        botaoRolarDadosEnergia.setForeground(new Color(139,0,0));
        botaoRolarDadosEnergia.setFont(new Font(Font.SERIF,Font.BOLD,20));
        botaoRolarDadosEnergia.setBounds(530,200,120,40);
        botaoRolarDadosEnergia.setFocusable(false);
        botaoRolarDadosEnergia.setCursor(cursor);
        botaoRolarDadosEnergia.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
        botaoRolarDadosEnergia.addActionListener(e -> {
            TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
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
        ImagePanel painelDeSorte = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FUNDO_CAD_PERSONAGEM_INDICES);
        painelDeSorte.setBounds(770,10,370,290);

        //Título
        JLabel labelTituloSorte = new JLabel("Sorte");
        labelTituloSorte.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelTituloSorte.setForeground(new Color(139,0,0));
        labelTituloSorte.setBounds(925,13,150,50);

        //Info
        JLabel labelInfoSorte = new JLabel("<html><center>Será rolado um dado e somado 6.<br>Este será o seu índice de SORTE</center></html>");
        labelInfoSorte.setFont(new Font(Font.SERIF,Font.BOLD,17));
        labelInfoSorte.setForeground(new Color(139,0,0));
        labelInfoSorte.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoSorte.setBounds(780,50,370,100);

        //Índice de Habilidade
        labelIndiceSorte = new JLabel("0");
        labelIndiceSorte.setFont(new Font(Font.SERIF,Font.BOLD,60));
        labelIndiceSorte.setForeground(new Color(139,0,0));
        labelIndiceSorte.setBounds(790,130,370,50);
        labelIndiceSorte.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão rolar dado
        botaoRolarDadosSorte = new JButton("Rolar Dado");
        botaoRolarDadosSorte.setBackground(new Color(210,180,140));
        botaoRolarDadosSorte.setForeground(new Color(139,0,0));
        botaoRolarDadosSorte.setFont(new Font(Font.SERIF,Font.BOLD,20));
        botaoRolarDadosSorte.setBounds(915,200,120,40);
        botaoRolarDadosSorte.setFocusable(false);
        botaoRolarDadosSorte.setCursor(cursor);
        botaoRolarDadosSorte.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
        botaoRolarDadosSorte.addActionListener(e -> {
            TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
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

    private void carregarBotoesGravarResetarFechar() {

        //Botao Gravar
        labelGravar = new JLabel("Gravar");
        labelGravar.setBounds(245, 705,100,100);
        labelGravar.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelGravar.setVerticalTextPosition(SwingConstants.CENTER);
        labelGravar.setCursor(cursor);
        labelGravar.setForeground(new Color(139,0,0));
        botaoGravar = new JLabelOpcoesTelaSecao(null,280,170,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoGravar.setBounds(150, 710,280,100);
        botaoGravar.addMouseListener(acao);
        botaoGravar.setCursor(cursor);
        botaoGravar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoGravar.setEnabled(false);
        labelGravar.setEnabled(false);
        //botaoGravar.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(labelGravar);
        add(botaoGravar);


        //Botao resetar
        botaoResetar = new JLabelOpcoesTelaSecao("",280,170,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoResetar.setBounds(440, 710,280,100);
        botaoResetar.addMouseListener(acao);
        botaoResetar.setCursor(cursor);
        botaoResetar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoResetar.addMouseListener(acao);
        add(botaoResetar);

        //Fechar janela
        botaoFechar = new JLabelOpcoesTelaSecao("",280,170,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoFechar.setBounds(730, 710,280,100);
        botaoFechar.addMouseListener(acao);
        botaoFechar.setCursor(cursor);
        botaoFechar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoFechar.addMouseListener(acao);



//        JButton botaoFechar = new JButton("Sair");
//        botaoFechar.setForeground(new Color(139,0,0));
//        botaoFechar. setBackground(new Color(210,180,140));
//        botaoFechar.setFont(new Font(Font.SERIF,Font.BOLD,20));
//        botaoFechar.setBounds(750, 720,270,50);
//        botaoFechar.setFocusable(false);
//        botaoFechar.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
//        botaoFechar.setCursor(cursor);
//        botaoFechar.addActionListener(e -> {
//            setVisible(false);
//            telaPrincipal.setVisible(true);
//        });
        add(botaoFechar);
    }

    private void desabilitarBotoes(JButton botao){
        botao.setEnabled(false);
    }

    private void resetarCriacaoPersonagem(){
        habilidadeInicial = 0;
        energiaInicial    = 0;
        sorteInicial      = 0;
        pocaoEscolhida    = 0;
        generoPersonagem  = 0;

        labelIndiceHabilidade.setText(String.valueOf(habilidadeInicial));
        labelIndiceEnergia.setText(String.valueOf(energiaInicial));
        labelIndiceSorte.setText(String.valueOf(sorteInicial));
        txtNome.setText("");

        botaoRolarDadoHabilidade.setEnabled(true);
        botaoRolarDadosEnergia.setEnabled(true);
        botaoRolarDadosSorte.setEnabled(true);
        botaoGravar.setEnabled(false);
        labelGravar.setEnabled(false);
        botaoPocaoEnergia.setEnabled(true);
        botaoPocaoSorte.setEnabled(true);
        botaoPocaoHabilidade.setEnabled(true);
        radioGrupoGenero.clearSelection();
    }

    private void habilitarBotaoGravar(){
        if ( (pocaoEscolhida > 0) && (habilidadeInicial != 0) &&
                (energiaInicial != 0) && (sorteInicial != 0) &&
                (!txtNome.getText().isEmpty()) && energiaInicial != 0 && generoPersonagem != 0) {
            botaoGravar.setEnabled(true);
            labelGravar.setEnabled(true);
            txtNome.setFocusable(true);
        }
        else {
            botaoGravar.setEnabled(false);
            labelGravar.setEnabled(false);
        }
    }

    private void carregarPersonagemEAbreTelaSecao(){
        var nome = txtNome.getText();

        //Cria o personagem e já joga na variável estática da classe ManipularDadosLivro
        CriacaoPersonagem criacaoPersonagem = new CriacaoPersonagem(nome, ManipularDadosLivro.getLivro().getIdLivro(),
                habilidadeInicial, energiaInicial,sorteInicial,pocaoEscolhida,generoPersonagem);

        CarregarTelas.telaSecaoHistoriaInicial(null,criacaoPersonagem.criar(), telaPrincipal);
        setVisible(false);
    }

    private class TelaAcaoDosLabels implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == botaoFechar){
                setVisible(false);
            }

            if (e.getSource() == botaoResetar){
                resetarCriacaoPersonagem();
            }

            if (e.getSource() == botaoGravar){
                carregarPersonagemEAbreTelaSecao();
            }

            if (e.getSource() == botaoPocaoHabilidade){
                pocaoEscolhida = ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem();  //Corresponde ao código da poção de Habilidade
                botaoPocaoEnergia.setEnabled(false);
                botaoPocaoSorte.setEnabled(false);
                botaoPocaoHabilidade.setEnabled(false);
                habilitarBotaoGravar();
            }

            if (e.getSource() == botaoPocaoEnergia){
                pocaoEscolhida = ItensMapeamento.POCAO_DE_ENERGIA.getIdItem();  //Corresponde ao código da poção de Energia
                botaoPocaoEnergia.setEnabled(false);
                botaoPocaoSorte.setEnabled(false);
                botaoPocaoHabilidade.setEnabled(false);
                //JOptionPane.showMessageDialog(null,"Energia: "+pocaoEscolhida);
                habilitarBotaoGravar();
            }

            if (e.getSource() == botaoPocaoSorte){
                pocaoEscolhida = ItensMapeamento.POCAO_DA_FORTUNA.getIdItem();  //Corresponde ao código da poção da sorte
                botaoPocaoEnergia.setEnabled(false);
                botaoPocaoSorte.setEnabled(false);
                botaoPocaoHabilidade.setEnabled(false);
            //JOptionPane.showMessageDialog(null,"Sorte: "+pocaoEscolhida);
                habilitarBotaoGravar();
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
