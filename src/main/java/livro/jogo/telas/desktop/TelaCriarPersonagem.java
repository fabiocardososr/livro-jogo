package livro.jogo.telas.desktop;

import livro.jogo.Personagens.CriacaoPersonagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.ImagePanel;
import livro.jogo.telas.desktop.personalizados.JButtonEscolhaPocao;
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
    private JButton botaoGravar;
    private JButton botaoResetar;
    private JButtonEscolhaPocao botaoPocaoSorte;
    private JButtonEscolhaPocao botaoPocaoEnergia;
    private JButtonEscolhaPocao botaoPocaoHabilidade;
    private JLabel labelIndiceHabilidade;
    private JLabel labelIndiceEnergia;
    private JLabel labelIndiceSorte;
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
    private final TelaBasica telaMae; //Usada para que esconda a tela mãe(principal) até que esta seja fechada e ai: telaMae.setVisible(true)


    public TelaCriarPersonagem(int largura, int altura, TelaBasica telaMae) {
        super(largura, altura);
        this.telaMae = telaMae;
        this.telaMae.setVisible(false); //Lembrar de enviar esta referência para a tela do início do jogo.

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
    }

    private void carregarPainelInferior() {

        //Painel inferior (imagem de pergaminho maior)
        ImagePanel painelInferior = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FUNDO_CAD_PERSONAGEM);
        painelInferior.setBounds(2,305,1130,470);

        //Opção de escolha de poções inicial
        carregaEscolhaPocao();

        //Visualiza escolha do personagem
        carregaEscolhasPersonagem();

        //Último a ser adicionado o painel inferior, senão ele fica por cima e cobre os outros componentes
        add(painelInferior);

    }

    private void carregaEscolhasPersonagem() {
        JLabel labelEscolhaPersonagem = new JLabel("<html><center>Itens Iniciais<center></html>");
        labelEscolhaPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,26));
        labelEscolhaPersonagem.setForeground(new Color(139,0,0));
        labelEscolhaPersonagem.setBounds(315, 380,730,70);
        labelEscolhaPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        //labelEscolhaPersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabel labelFlecha = new JLabel("<html><center>==><center></html>");
        labelFlecha.setFont(new Font(Font.SERIF,Font.BOLD,80));
        labelFlecha.setForeground(new Color(139,0,0));
        labelFlecha.setBounds(560, 500,200,70);
        labelFlecha.setHorizontalAlignment(SwingConstants.CENTER);

        ImagePanel painelImgBolsa = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.BOLSA);
        painelImgBolsa.setBackground(new Color(210,180,140));
        painelImgBolsa.setForeground(new Color(139,0,0));
        painelImgBolsa.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelImgBolsa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelImgBolsa.setToolTipText("Carrega todos os itens que não estejam equipados");
        painelImgBolsa.setBounds(780,440,250,230);

        //Itens iniciais
        ImagePanel imgPanelEspada = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.ESPADA_INICIAL);
        imgPanelEspada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imgPanelEspada.setToolTipText("Espada simples usada nos combates.");
        imgPanelEspada.setBounds(430,440,90,90);
        //imgPanelEspada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        ImagePanel imgPanelArmadura = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.ARMADURA_DE_COURO_INICIAL);
        imgPanelArmadura.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imgPanelArmadura.setToolTipText("Armadura de couro que o(a) protegerá durante a aventura.");
        imgPanelArmadura.setBounds(430,520,90,90);

        //imgPanelArmadura.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        ImagePanel imgPanelProvisoes = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PROVISOES);
        imgPanelProvisoes.setBounds(430,610,90,90);
        imgPanelProvisoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imgPanelProvisoes.setToolTipText("<html>10 Provisões(refeições). Recompõe sua ENERGIA em 4 pontos.<br>Pode ser consumida em qualquer momento, exceto em combate.</html>");
        //imgPanelProvisões.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        add(labelEscolhaPersonagem);
        add(imgPanelEspada);
        add(imgPanelArmadura);
        add(imgPanelProvisoes);
        add(labelFlecha);

        //add(painelEscolhaPersonagem);
        add(painelImgBolsa);
    }

    private void carregaEscolhaPocao() {


        JLabel labelEscolhaPocao = new JLabel("<html><center>Escolha uma Poção<center></html>");
        labelEscolhaPocao.setFont(new Font(Font.SERIF,Font.BOLD,23));
        labelEscolhaPocao.setForeground(new Color(139,0,0));
        labelEscolhaPocao.setBounds(120, 380,200,70);
        labelEscolhaPocao.setHorizontalAlignment(SwingConstants.CENTER);
        //labelEscolhaPocao.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(labelEscolhaPocao);


        /********    POCAO HABILIDADE    ********/
        botaoPocaoHabilidade = new JButtonEscolhaPocao("Habilidade",
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_HABILIDADE);
        botaoPocaoHabilidade.setBounds(130, 440,180,72);
        botaoPocaoHabilidade.setVerticalTextPosition(SwingConstants.NORTH);

        botaoPocaoHabilidade.addActionListener(e -> {
            pocaoEscolhida = 45;  //Corresponde ao código da poção de Habilidade
            botaoPocaoEnergia.setEnabled(false);
            botaoPocaoSorte.setEnabled(false);
            botaoPocaoHabilidade.setEnabled(false);
            habilitarBotaoGravar();
        });
        add(botaoPocaoHabilidade);


        /********    POCAO ENERGIA    ********/
        botaoPocaoEnergia = new JButtonEscolhaPocao("Energia",
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_ENERGIA);
        botaoPocaoEnergia.setBounds(130, 517,180,72);
        botaoPocaoEnergia.addActionListener(e -> {
            pocaoEscolhida = 46;  //Corresponde ao código da poção de Energia
            botaoPocaoEnergia.setEnabled(false);
            botaoPocaoSorte.setEnabled(false);
            botaoPocaoHabilidade.setEnabled(false);
            //JOptionPane.showMessageDialog(null,"Energia: "+pocaoEscolhida);
            habilitarBotaoGravar();
        });
        add(botaoPocaoEnergia);

        /********    POCAO SORTE    ********/
        botaoPocaoSorte = new JButtonEscolhaPocao("Sorte",
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_SORTE);
        botaoPocaoSorte.setBounds(130, 594,180,72);
        botaoPocaoSorte.addActionListener(e -> {
            pocaoEscolhida = 47;  //Corresponde ao código da poção da sorte
            botaoPocaoEnergia.setEnabled(false);
            botaoPocaoSorte.setEnabled(false);
            botaoPocaoHabilidade.setEnabled(false);
            //JOptionPane.showMessageDialog(null,"Sorte: "+pocaoEscolhida);
            habilitarBotaoGravar();
        });
        add(botaoPocaoSorte);
    }

    private void carregarTxtNomeGenero() {
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(new Font(Font.SERIF,Font.PLAIN,20));
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
            TelaBasica.mostrarDadosRolando(this,4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
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
            TelaBasica.mostrarDadosRolando(this,4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
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
            TelaBasica.mostrarDadosRolando(this,4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
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
        botaoGravar = new JButton("Gerar Personagem");
        botaoGravar.setForeground(new Color(139,0,0));
        botaoGravar. setBackground(new Color(210,180,140));
        botaoGravar.setFont(new Font(Font.SERIF,Font.BOLD,20));
        botaoGravar.setBounds(130, 720,270,50);
        botaoGravar.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
        botaoGravar.setFocusable(false);
        botaoGravar.setEnabled(false);
        botaoGravar.setCursor(cursor);
        botaoGravar.addActionListener(e -> { carregarPersonagem(); });
        add(botaoGravar);

        botaoResetar = new JButton("Refazer Personagem");
        botaoResetar.setForeground(new Color(139,0,0));
        botaoResetar. setBackground(new Color(210,180,140));
        botaoResetar.setFont(new Font(Font.SERIF,Font.BOLD,20));
        botaoResetar.setBounds(440, 720,270,50);
        botaoResetar.setFocusable(false);
        botaoResetar.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
        botaoResetar.setCursor(cursor);
        botaoResetar.addActionListener(e -> {
            resetarCriacaoPersonagem();
        });
        add(botaoResetar);

        JButton botaoFechar = new JButton("Sair");
        botaoFechar.setForeground(new Color(139,0,0));
        botaoFechar. setBackground(new Color(210,180,140));
        botaoFechar.setFont(new Font(Font.SERIF,Font.BOLD,20));
        botaoFechar.setBounds(750, 720,270,50);
        botaoFechar.setFocusable(false);
        botaoFechar.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
        botaoFechar.setCursor(cursor);
        botaoFechar.addActionListener(e -> {
            setVisible(false);
            telaMae.setVisible(true);
        });
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
            txtNome.setFocusable(true);
        }
        else
            botaoGravar.setEnabled(false);
    }

    private void carregarPersonagem(){
        var nome = txtNome.getText();
        var idLivro = ManipularDadosLivro.getLivro().getIdLivro();

        //Cria o personagem e já joga na variável estática da classe ManipularDadosLivro
        new CriacaoPersonagem(nome, idLivro, habilidadeInicial,energiaInicial,sorteInicial,pocaoEscolhida,generoPersonagem);
    }

}
