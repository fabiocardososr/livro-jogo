package livro.jogo.telas.desktop.principal;

import livro.jogo.Personagens.CriacaoPersonagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.ImagePanel;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.LimitarCampoJTextField;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TelaCriarPersonagem extends TelaBasica {
    private JLabelOpcoesTelaSecao botaoRolarDadosHabilidade;
    private JLabelOpcoesTelaSecao botaoRolarDadosEnergia;
    private JLabelOpcoesTelaSecao botaoRolarDadosSorte;
    private JLabelOpcoesTelaSecao botaoGravar;
    private JLabelOpcoesTelaSecao botaoResetar;
    private JLabelOpcoesTelaSecao botaoFechar;
    private JLabelOpcoesTelaSecao botaoPocaoSorte;
    private JLabelOpcoesTelaSecao botaoPocaoEnergia;
    private JLabelOpcoesTelaSecao botaoPocaoHabilidade;
    private JLabelOpcoesTelaSecao umDadoSorte;
    private JLabelOpcoesTelaSecao doisDadosEnergia;
    private JLabelOpcoesTelaSecao umDadoHabilidade;
    private JLabelOpcoesTelaSecao imgInterrogacao; //Quando escolher a poção inicial será alterada a imagem.
    private JLabel labelIndiceHabilidade;
    private JLabel labelIndiceEnergia;
    private JLabel labelIndiceSorte;
    private JLabel labelGravar;
    private JLabel labelResetar;
    private JLabel labelFechar;
    private JTextField txtNome;
    private JRadioButton rbMasculino;
    private JRadioButton rbFeminino;
    private ButtonGroup radioGrupoGenero;
    private int habilidadeInicial = 0;
    private int energiaInicial = 0;
    private int sorteInicial = 0;
    private int pocaoEscolhida = 0; //Escolha da poção
    private int generoPersonagem = 0;
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
     //1 = Masculino; 2 = Feminino
    private final TelaBasica telaPrincipal; //Usada para que esconda a tela mãe(principal) até que esta seja fechada e ai: telaMae.setVisible(true)
    private final TelaAcaoDosLabels acao = new TelaAcaoDosLabels();
    private Util util = new Util();


    public TelaCriarPersonagem(int largura, int altura, TelaBasica telaPrincipal) {
        super(largura, altura);
        setUndecorated(true);
        setLayout(null);
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
        painelInferior.setLayout(null);
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
        painelImgBolsa.setLayout(null);
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

        imgInterrogacao = new JLabelOpcoesTelaSecao(null,55, 55,
                ImagensDoLivroFlorestaDaDestruicao.INTERROGACAO);
        imgInterrogacao.setBounds(570,470,55,55);
        imgInterrogacao.setHorizontalAlignment(SwingConstants.CENTER);
        imgInterrogacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imgInterrogacao.setToolTipText("Aqui ficará a poção inicial escolhida.");

        //Espada
        ImagePanel imgPanelEspada = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.ESPADA_INICIAL);
        imgPanelEspada.setLayout(null);
        imgPanelEspada.setToolTipText("Espada simples usada nos combates.");
        imgPanelEspada.setBounds(510,460,75,75);
        imgPanelEspada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //imgPanelEspada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Armadura
        ImagePanel imgPanelArmadura = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.ARMADURA_DE_COURO_INICIAL);
        imgPanelArmadura.setLayout(null);
        imgPanelArmadura.setToolTipText("Armadura de couro que o(a) protegerá durante a aventura.");
        imgPanelArmadura.setBounds(460,530,75,75);
        imgPanelArmadura.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Provisões (refeições)
        ImagePanel imgPanelProvisoes = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PROVISOES);
        imgPanelProvisoes.setLayout(null);
        imgPanelProvisoes.setBounds(530,530,75,75);
        imgPanelProvisoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imgPanelProvisoes.setToolTipText("<html>10 Provisões(refeições). Recompõe sua ENERGIA em 4 pontos.<br>Pode ser consumida em qualquer momento, exceto em combate.</html>");
        //imgPanelProvisões.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(imgInterrogacao);
        add(imgPanelEspada);
        add(imgPanelArmadura);
        add(imgPanelProvisoes);
        add(labelMao);
        add(painelImgBolsa);
        add(labelFundoItensItensIniciais);
    }

    private void carregaEscolhaPocao() {
        JLabelOpcoesTelaSecao labelImgEscolhaPocao = new JLabelOpcoesTelaSecao(null,250,
                250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        labelImgEscolhaPocao.setBounds(100, 355,240,110);
        labelImgEscolhaPocao.setHorizontalAlignment(SwingConstants.CENTER);
        //labelImgEscolhaPocao.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabel labelEscolhaPocao = new JLabel("Escolha");
        labelEscolhaPocao.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelEscolhaPocao.setForeground(new Color(139,0,0));
        labelEscolhaPocao.setBounds(118, 367,200,70);
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
        JLabelOpcoesTelaSecao labelImgEnergia = new JLabelOpcoesTelaSecao("",30,35,
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_ENERGIA);
        labelImgEnergia.setCursor(cursor);
        labelImgEnergia.setBounds(160,550,30,35);
        JLabel labelRotuloEnergia = new JLabel("Força");
        labelRotuloEnergia.setBounds(190,540,150,50);
        labelRotuloEnergia.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelRotuloEnergia.setVerticalTextPosition(SwingConstants.CENTER);
        labelRotuloEnergia.setCursor(cursor);
        labelRotuloEnergia.setForeground(new Color(139,0,0));
        botaoPocaoEnergia = new JLabelOpcoesTelaSecao("Força",200,100,
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
        labelNome.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelNome.setBounds(0, 287,1150,60);
        labelNome.setForeground(new Color(139,0,0));
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);

        JLabelOpcoesTelaSecao labelImgFaixaTitulo = new JLabelOpcoesTelaSecao(null,120,
                120,ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        labelImgFaixaTitulo.setBounds(515, 295,120,50);
        labelImgFaixaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelImgFaixaTitulo.setCursor(null);
        //labelImgFaixaTitulo.setBorder(BorderFactory.createLineBorder(Color.RED));

        txtNome = new JTextField();
        txtNome.setDocument(new LimitarCampoJTextField(10));
        txtNome.setBounds(486, 345,180,25);
        txtNome.setBackground(new Color(210,180,140));
        txtNome.setForeground(new Color(139,0,0));
        txtNome.setFont(new Font(Font.SERIF,Font.BOLD,25));
        txtNome.setCursor(cursor);
        txtNome.setBorder(BorderFactory.createLineBorder(new Color(139,0,0)));
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
        rbMasculino.setBounds(670,340, 120,20);
        rbMasculino.setFont(new Font(Font.SERIF,Font.BOLD,17));
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
        rbFeminino.setBounds(670,356, 120,20);
        rbFeminino.setCursor(cursor);
        rbFeminino.setFont(new Font(Font.SERIF,Font.BOLD,17));
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
        add(labelImgFaixaTitulo);
    }

    private void carregarPainelHabilidade() {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeHabilidade = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA_6);
        painelDeHabilidade.setLayout(null);
        painelDeHabilidade.setBounds(10,10,370,290);
        painelDeHabilidade.setToolTipText("O resultado da rolagem do dado vai ser somado a 6. Este resultado será sua habilidade.");

        //Fundo do título
        JLabelOpcoesTelaSecao labelImgFaixaTitulo = new JLabelOpcoesTelaSecao(null,250,
                250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        labelImgFaixaTitulo.setBounds(80, -10,240,100);
        labelImgFaixaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelImgFaixaTitulo.setCursor(null);
        //labelImgFaixaTitulo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Título
        JLabel labelTituloHabilidade = new JLabel("Habilidade");
        labelTituloHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,22));
        labelTituloHabilidade.setForeground(new Color(139,0,0));
        labelTituloHabilidade.setBounds(150,7,150,50);

        //Índice de Habilidade
        labelIndiceHabilidade = new JLabel("0");
        labelIndiceHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,50));
        //labelIndiceHabilidade.setForeground(new Color(139,0,0));
        labelIndiceHabilidade.setForeground(Color.WHITE);
        labelIndiceHabilidade.setBounds(13,110,370,50);
        labelIndiceHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Fundo do índice
        JLabelOpcoesTelaSecao labelImgFundoIndice = new JLabelOpcoesTelaSecao(null,100,
                125,ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        labelImgFundoIndice.setBounds(120, 70,150,165);
        labelImgFundoIndice.setHorizontalAlignment(SwingConstants.CENTER);
        //labelImgFundoIndice.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Botão rolar dado
        botaoRolarDadosHabilidade = new JLabelOpcoesTelaSecao(null,150,100,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRolarDadosHabilidade.setBounds(122,202,150,100);
        botaoRolarDadosHabilidade.setCursor(cursor);
        botaoRolarDadosHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        botaoRolarDadosHabilidade.addMouseListener(acao);
        umDadoHabilidade = new JLabelOpcoesTelaSecao(null,45,40,
                ImagensDoLivroFlorestaDaDestruicao.UM_DADO);
        umDadoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        umDadoHabilidade.setBounds(174,225,50,45);

        //Fazer aparecer na tela
        add(umDadoHabilidade);
        add(labelTituloHabilidade);
        add(labelImgFaixaTitulo);
        add(labelIndiceHabilidade);
        add(labelImgFundoIndice);
        add(botaoRolarDadosHabilidade);
        add(painelDeHabilidade);
    }

    private void carregarPainelEnergia() {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeEnergia = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA_6);
        painelDeEnergia.setLayout(null);
        painelDeEnergia.setBounds(392,10,370,290);
        painelDeEnergia.setToolTipText("O resultado da rolagem de dois dados será somado a 12. Este resultado será seu índice de energia.");

        //Fundo do título
        JLabelOpcoesTelaSecao labelImgFaixaTitulo = new JLabelOpcoesTelaSecao(null,250,
                250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        labelImgFaixaTitulo.setBounds(460, -10,240,100);
        labelImgFaixaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelImgFaixaTitulo.setCursor(null);
        //labelImgFaixaTitulo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Título
        JLabel labelTituloEnergia = new JLabel("Energia");
        labelTituloEnergia.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelTituloEnergia.setForeground(new Color(139,0,0));
        labelTituloEnergia.setBounds(545,7,150,50);

        //Índice de Habilidade
        labelIndiceEnergia = new JLabel("0");
        labelIndiceEnergia.setFont(new Font(Font.SERIF,Font.BOLD,50));
        labelIndiceEnergia.setForeground(Color.WHITE);
        labelIndiceEnergia.setBounds(395,110,370,50);
        labelIndiceEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        //Fundo do índice
        JLabelOpcoesTelaSecao labelImgFundoIndice = new JLabelOpcoesTelaSecao(null,100,
                125,ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        labelImgFundoIndice.setBounds(500, 70,150,165);
        labelImgFundoIndice.setHorizontalAlignment(SwingConstants.CENTER);
        //labelImgFundoIndice.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoRolarDadosEnergia = new JLabelOpcoesTelaSecao(null,150,100,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRolarDadosEnergia.setBounds(503,202,150,100);
        botaoRolarDadosEnergia.setCursor(cursor);
        botaoRolarDadosEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        botaoRolarDadosEnergia.addMouseListener(acao);

        doisDadosEnergia = new JLabelOpcoesTelaSecao(null,45,40,
                ImagensDoLivroFlorestaDaDestruicao.DOIS_DADOS);
        doisDadosEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        doisDadosEnergia.setBounds(550,220,55,55);

        add(doisDadosEnergia);
        add(labelTituloEnergia);
        add(labelIndiceEnergia);
        add(labelImgFundoIndice);
        add(labelImgFaixaTitulo);
        add(botaoRolarDadosEnergia);
        add(painelDeEnergia);
    }

    private void carregarPainelSorte() {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeSorte = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA_6);
        painelDeSorte.setLayout(null);
        painelDeSorte.setBounds(770,10,370,290);
        painelDeSorte.setToolTipText("O resultado da rolagem de um dado será somado a 6. Este resultado será seu índice de sorte.");

        //Fundo do título
        JLabelOpcoesTelaSecao labelImgFaixaTitulo = new JLabelOpcoesTelaSecao(null,250,
                250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        labelImgFaixaTitulo.setBounds(837, -10,240,100);
        labelImgFaixaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelImgFaixaTitulo.setCursor(null);
        //labelImgFaixaTitulo.setBorder(BorderFactory.createLineBorder(Color.RED));

        JLabel labelTituloSorte = new JLabel("Sorte");
        labelTituloSorte.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelTituloSorte.setForeground(new Color(139,0,0));
        labelTituloSorte.setBounds(935,7,150,50);

        //Índice de sorte
        labelIndiceSorte = new JLabel("0");
        labelIndiceSorte.setFont(new Font(Font.SERIF,Font.BOLD,50));
        labelIndiceSorte.setForeground(Color.WHITE);
        labelIndiceSorte.setBounds(775,110,370,50);
        labelIndiceSorte.setHorizontalAlignment(SwingConstants.CENTER);
        //labelIndiceHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Fundo do índice
        JLabelOpcoesTelaSecao labelImgFundoIndice = new JLabelOpcoesTelaSecao(null,100,
                125,ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        labelImgFundoIndice.setBounds(880, 70,150,165);
        labelImgFundoIndice.setHorizontalAlignment(SwingConstants.CENTER);
        //labelImgFundoIndice.setBorder(BorderFactory.createLineBorder(Color.RED));

        umDadoSorte = new JLabelOpcoesTelaSecao(null,45,40,
                ImagensDoLivroFlorestaDaDestruicao.UM_DADO);
        umDadoSorte.setHorizontalAlignment(SwingConstants.CENTER);
        umDadoSorte.setBounds(934,225,50,45);

        botaoRolarDadosSorte = new JLabelOpcoesTelaSecao(null,150,100,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRolarDadosSorte.setBounds(885,202,150,100);

        botaoRolarDadosSorte.setCursor(cursor);
        botaoRolarDadosSorte.setHorizontalAlignment(SwingConstants.CENTER);
        botaoRolarDadosSorte.addMouseListener(acao);

        add(umDadoSorte);
        add(labelTituloSorte);
        add(labelImgFaixaTitulo);
        add(labelIndiceSorte);
        add(labelImgFundoIndice);
        add(botaoRolarDadosSorte);
        add(painelDeSorte);
    }

    private void carregarBotoesGravarResetarFechar() {

        //Botao Gravar
        labelGravar = new JLabel("Gravar");
        labelGravar.setBounds(245, 700,100,100);
        labelGravar.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelGravar.setVerticalTextPosition(SwingConstants.CENTER);
        labelGravar.setCursor(cursor);
        labelGravar.setForeground(new Color(139,0,0));
        botaoGravar = new JLabelOpcoesTelaSecao(null,280,110,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoGravar.setBounds(150, 705,280,110);
        botaoGravar.addMouseListener(acao);
        botaoGravar.setCursor(cursor);
        botaoGravar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoGravar.setEnabled(false);
        labelGravar.setEnabled(false);
        add(labelGravar);
        add(botaoGravar);

        //Botao resetar
        labelResetar = new JLabel("Limpar Campos");
        labelResetar.setBounds(505, 705,200,100);
        labelResetar.setFont(new Font(Font.SERIF,Font.BOLD,22));
        labelResetar.setVerticalTextPosition(SwingConstants.CENTER);
        labelResetar.setCursor(cursor);
        labelResetar.setForeground(new Color(139,0,0));
        botaoResetar = new JLabelOpcoesTelaSecao("",280,110,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoResetar.setBounds(440, 705,280,110);
        botaoResetar.addMouseListener(acao);
        botaoResetar.setCursor(cursor);
        botaoResetar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoResetar.addMouseListener(acao);
        add(labelResetar);
        add(botaoResetar);

        //Fechar janela
        labelFechar = new JLabel("Sair");
        labelFechar.setBounds(845, 700,200,100);
        labelFechar.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelFechar.setVerticalTextPosition(SwingConstants.CENTER);
        labelFechar.setCursor(cursor);
        labelFechar.setForeground(new Color(139,0,0));
        botaoFechar = new JLabelOpcoesTelaSecao("",280,110,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoFechar.setBounds(730, 705,280,110);
        botaoFechar.addMouseListener(acao);
        botaoFechar.setCursor(cursor);
        botaoFechar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoFechar.addMouseListener(acao);
        add(labelFechar);
        add(botaoFechar);
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

        botaoGravar.setEnabled(false);
        labelGravar.setEnabled(false);
        imgInterrogacao.setImagem(ImagensDoLivroFlorestaDaDestruicao.INTERROGACAO);
        imgInterrogacao.setToolTipText("Aqui ficará a poção inicial escolhida");
        radioGrupoGenero.clearSelection();
    }

    private void habilitarBotaoGravar(){
        if ( (pocaoEscolhida != 0) && (habilidadeInicial != 0) &&
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
        CriacaoPersonagem criacaoPersonagem = new CriacaoPersonagem(nome, DadosLivroCarregado.getLivro().getIdLivro(),
                habilidadeInicial, energiaInicial,sorteInicial,pocaoEscolhida,generoPersonagem);

        //Cria o personagem setando a informação em DadosLivroCarregado
        criacaoPersonagem.criar();

        //Carrega tela seção inicial
        CarregarTelas.carregarSecao(null);
        dispose();
    }

    private class TelaAcaoDosLabels implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int largura = 300;
            int altura = 330;

            if ( (e.getSource() == botaoRolarDadosSorte) && (sorteInicial == 0)){
                TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
                sorteInicial = Util.obterIndiceHabilidadeOuSorteInicial();
                labelIndiceSorte.setText(String.valueOf(sorteInicial));
                habilitarBotaoGravar();
            }

            if ( (e.getSource() == botaoRolarDadosEnergia) && (energiaInicial == 0) ){
                TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
                energiaInicial = Util.obterIndiceEnergiaInicial();
                labelIndiceEnergia.setText(String.valueOf(energiaInicial));
                habilitarBotaoGravar();
            }

            if ( (e.getSource() == botaoRolarDadosHabilidade) && (habilidadeInicial == 0) ){
                TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
                habilidadeInicial = Util.obterIndiceHabilidadeOuSorteInicial();
                labelIndiceHabilidade.setText(String.valueOf(habilidadeInicial));
                habilitarBotaoGravar();

            }

            if (e.getSource() == botaoFechar){
                telaPrincipal.setVisible(true);
                dispose();
            }

            if (e.getSource() == botaoResetar){
                resetarCriacaoPersonagem();
            }

            if (e.getSource() == botaoGravar){
                carregarPersonagemEAbreTelaSecao();
            }

            if ( e.getSource() == botaoPocaoHabilidade ){
                pocaoEscolhida = ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem();  //Corresponde ao código da poção de Habilidade
                imgInterrogacao.setImagem(ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_HABILIDADE);
                imgInterrogacao.setToolTipText("Poção de Habilidade escolhida. Recupera sua habilidade.");
                habilitarBotaoGravar();
            }

            if (e.getSource() == botaoPocaoEnergia ){
                pocaoEscolhida = ItensMapeamento.POCAO_DE_ENERGIA.getIdItem();  //Corresponde ao código da poção de Energia
                imgInterrogacao.setImagem(ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_ENERGIA);
                imgInterrogacao.setToolTipText("Poção de Força escolhida. Recupera a energia.");
                habilitarBotaoGravar();
            }

            if (e.getSource() == botaoPocaoSorte ){
                pocaoEscolhida = ItensMapeamento.POCAO_DA_FORTUNA.getIdItem();  //Corresponde ao código da poção da sorte
                imgInterrogacao.setImagem(ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_SORTE);
                imgInterrogacao.setToolTipText("Poção de Fortuna escolhida. Recupera sua sorte e acrescenta +1 no valor máximo.");
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
            if (e.getSource() == botaoRolarDadosSorte) {
                botaoRolarDadosSorte.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoRolarDadosSorte.getWidth(), botaoRolarDadosSorte.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoRolarDadosEnergia) {
                botaoRolarDadosEnergia.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoRolarDadosEnergia.getWidth(), botaoRolarDadosEnergia.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoRolarDadosHabilidade) {
                botaoRolarDadosHabilidade.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoRolarDadosHabilidade.getWidth(), botaoRolarDadosHabilidade.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoPocaoHabilidade) {
                botaoPocaoHabilidade.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoPocaoHabilidade.getWidth(), botaoPocaoHabilidade.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoPocaoEnergia) {
                botaoPocaoEnergia.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoPocaoEnergia.getWidth(), botaoPocaoEnergia.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoPocaoSorte) {
                botaoPocaoSorte.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoPocaoSorte.getWidth(), botaoPocaoSorte.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoGravar) {
                botaoGravar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoGravar.getWidth(), botaoGravar.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoResetar) {
                botaoResetar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoResetar.getWidth(), botaoResetar.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoFechar) {
                botaoFechar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoFechar.getWidth(), botaoFechar.getHeight()).getImageIcon());
                repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == botaoRolarDadosSorte) {
                botaoRolarDadosSorte.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoRolarDadosSorte.getWidth(), botaoRolarDadosSorte.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoRolarDadosEnergia) {
                botaoRolarDadosEnergia.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoRolarDadosEnergia.getWidth(), botaoRolarDadosEnergia.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoRolarDadosHabilidade) {
                botaoRolarDadosHabilidade.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoRolarDadosHabilidade.getWidth(), botaoRolarDadosHabilidade.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoPocaoHabilidade) {
                botaoPocaoHabilidade.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoPocaoHabilidade.getWidth(), botaoPocaoHabilidade.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoPocaoEnergia) {
                botaoPocaoEnergia.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoPocaoEnergia.getWidth(), botaoPocaoEnergia.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoPocaoSorte) {
                botaoPocaoSorte.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoPocaoSorte.getWidth(), botaoPocaoSorte.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoGravar) {
                botaoGravar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoGravar.getWidth(), botaoGravar.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoResetar) {
                botaoResetar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoResetar.getWidth(), botaoResetar.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoFechar) {
                botaoFechar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoFechar.getWidth(), botaoFechar.getHeight()).getImageIcon());
                repaint();
            }

        }
    }

}
