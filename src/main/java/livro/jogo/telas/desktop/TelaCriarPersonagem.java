package livro.jogo.telas.desktop;

import livro.jogo.Personagens.CriacaoPersonagem;
import livro.jogo.telas.desktop.personalizados.ImagePanel;
import livro.jogo.telas.desktop.personalizados.JButtonEscolhaPocao;
import livro.jogo.utils.ManipularDados;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaCriarPersonagem extends Tela {
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
    private int habilidadeInicial = 0;
    private int energiaInicial = 0;
    private int sorteInicial = 0;
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private JList<String> listaPocoes;
    private int pocaoEscolhida; //Escolha da poção

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
        carregarPainelInferior();
    }

    private void carregarPainelInferior() {

        //Painel inferior (imagem de pergaminho maior)
        ImagePanel painelInferior = new ImagePanel("livros/florestadadestruicao/imagens/pergaminho_panel_cadPersonagem.png");
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
        labelEscolhaPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,22));
        labelEscolhaPersonagem.setForeground(new Color(150,69,19));
        labelEscolhaPersonagem.setBounds(380, 380,200,70);
        labelEscolhaPersonagem.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelEscolhaPersonagem = new JPanel();
        painelEscolhaPersonagem.setBackground(new Color(210,180,140));
        painelEscolhaPersonagem.setForeground(new Color(139,0,0));
        painelEscolhaPersonagem.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelEscolhaPersonagem.setBounds(350,440,250,230);

        JLabel labelFlecha = new JLabel("<html><center>==><center></html>");
        labelFlecha.setFont(new Font(Font.SERIF,Font.BOLD,80));
        labelFlecha.setForeground(new Color(139,0,0));
        labelFlecha.setBounds(580, 500,200,70);
        labelFlecha.setHorizontalAlignment(SwingConstants.CENTER);

        ImagePanel painelImgBolsa = new ImagePanel("livros/florestadadestruicao/imagens/bolsa.png");
        painelImgBolsa.setBackground(new Color(210,180,140));
        painelImgBolsa.setForeground(new Color(139,0,0));
        painelImgBolsa.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelImgBolsa.setBounds(780,440,250,230);



        JLabel labelHabilidadePersonagem = new JLabel("<html>- Espada<br><br>- Armadura de Couro<br><br>- 10 Provisões</html>");
        labelHabilidadePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,22));
        labelHabilidadePersonagem.setForeground(new Color(139,0,0));
        labelHabilidadePersonagem.setBounds(360,450,250,200);
        //labelHabilidadePersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        add(labelEscolhaPersonagem);
        add(labelHabilidadePersonagem);
        add(labelFlecha);

        add(painelEscolhaPersonagem);
        add(painelImgBolsa);
    }

    private void carregaEscolhaPocao() {


        JLabel labelEscolhaPocao = new JLabel("<html><center>Escolha uma Poção<center></html>");
        labelEscolhaPocao.setFont(new Font(Font.SERIF,Font.BOLD,22));
        labelEscolhaPocao.setForeground(new Color(150,69,19));
        labelEscolhaPocao.setBounds(120, 380,200,70);
        labelEscolhaPocao.setHorizontalAlignment(SwingConstants.CENTER);
        //labelEscolhaPocao.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(labelEscolhaPocao);


        /********    POCAO HABILIDADE    ********/
        botaoPocaoHabilidade = new JButtonEscolhaPocao("Habilidade",
                "livros/florestadadestruicao/imagens/pocao_de_habilidade.png");
        botaoPocaoHabilidade.setBounds(130, 440,180,72);
        botaoPocaoHabilidade.setVerticalTextPosition(SwingConstants.NORTH);

        botaoPocaoHabilidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"HABILIADE");
                pocaoEscolhida = 45;  //Corresponde ao código da poção de Habilidade
                botaoPocaoEnergia.setEnabled(false);
                botaoPocaoSorte.setEnabled(false);
                botaoPocaoHabilidade.setEnabled(false);
            }
        });
        add(botaoPocaoHabilidade);


        /********    POCAO ENERGIA    ********/
        botaoPocaoEnergia = new JButtonEscolhaPocao("Energia",
                "livros/florestadadestruicao/imagens/pocao_de_energia.png");
        botaoPocaoEnergia.setBounds(130, 517,180,72);
        botaoPocaoEnergia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Energia");
                pocaoEscolhida = 46;  //Corresponde ao código da poção de Energia
                botaoPocaoEnergia.setEnabled(false);
                botaoPocaoSorte.setEnabled(false);
                botaoPocaoHabilidade.setEnabled(false);
            }
        });
        add(botaoPocaoEnergia);

        /********    POCAO SORTE    ********/
        botaoPocaoSorte = new JButtonEscolhaPocao("Sorte",
                "livros/florestadadestruicao/imagens/pocao_de_sorte.png");
        botaoPocaoSorte.setBounds(130, 594,180,72);
        botaoPocaoSorte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Sorte");
                pocaoEscolhida = 47;  //Corresponde ao código da poção da sorte
                botaoPocaoEnergia.setEnabled(false);
                botaoPocaoSorte.setEnabled(false);
                botaoPocaoHabilidade.setEnabled(false);
            }
        });
        add(botaoPocaoSorte);
    }

    private void carregarTxtNome() {
        JLabel labelNome = new JLabel("Nome do Personagem");
        labelNome.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        labelNome.setBounds(0, 280,1150,60);
        labelNome.setForeground(Color.WHITE);
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);

        txtNome = new JTextField();
        txtNome.setBounds(450, 330,250,40);
        txtNome.setBackground(Color.darkGray);
        txtNome.setForeground(Color.WHITE);
        txtNome.setFont(new Font(Font.SERIF,Font.BOLD,25));
        txtNome.setCursor(cursor);
        txtNome.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(txtNome);
        add(labelNome);
    }

    private void carregarPainelHabilidade() {

        //Criado um panel personalizado para incluir imagem de fundo
        ImagePanel painelDeHabilidade = new ImagePanel("livros/florestadadestruicao/imagens/pergaminho.png");
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
        botaoRolarDadoHabilidade.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadoHabilidade.setBounds(138,200,120,40);
        botaoRolarDadoHabilidade.setFocusable(false);
        botaoRolarDadoHabilidade.setCursor(cursor);
        botaoRolarDadoHabilidade.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
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
        ImagePanel painelDeEnergia = new ImagePanel("livros/florestadadestruicao/imagens/pergaminho.png");
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
        botaoRolarDadosEnergia.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadosEnergia.setBounds(530,200,120,40);
        botaoRolarDadosEnergia.setFocusable(false);
        botaoRolarDadosEnergia.setCursor(cursor);
        botaoRolarDadosEnergia.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
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
        ImagePanel painelDeSorte = new ImagePanel("livros/florestadadestruicao/imagens/pergaminho.png");
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
        botaoRolarDadosSorte.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRolarDadosSorte.setBounds(915,200,120,40);
        botaoRolarDadosSorte.setFocusable(false);
        botaoRolarDadosSorte.setCursor(cursor);
        botaoRolarDadosSorte.setBorder(BorderFactory.createLineBorder(new Color(128,0,0)));
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

    private void carregarBotoesGravarResetar() {
        botaoGravar = new JButton("Gerar Personagem");
        botaoGravar.setBackground(Color.BLACK);
        botaoGravar.setForeground(Color.WHITE);
        botaoGravar.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoGravar.setBounds(250, 720,300,50);
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
        botaoResetar.setBounds(650, 720,300,50);
        botaoResetar.setFocusable(false);
        botaoResetar.setCursor(cursor);
        botaoResetar.addActionListener(e -> {
            resetarCriacaoPersonagem();
        });
        add(botaoResetar);
    }

    private void desabilitarBotoes(JButton botao){
        botao.setEnabled(false);
    }

    private void resetarCriacaoPersonagem(){
        habilidadeInicial = 0;
        energiaInicial    = 0;
        sorteInicial      = 0;
        pocaoEscolhida    = 0;
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

    }

}
