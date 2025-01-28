package livro.jogo.telas.desktop.personalizados;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.SaveJogo;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.EfeitoDeItens;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class TelaSecoesBasica extends JDialog {
    private Secao secao;
    private Personagem personagem;
    private final TelaSecoesBasicaAcaoDosLabels acaoLabels = new TelaSecoesBasicaAcaoDosLabels();
    private Item pocaoInicial; //É a poção escolhida na criação do personagem
    private String enderecoImagem = DadosLivroCarregado.getLivro().getImagemComplementar();
    private JFrame referenciaTelaPrincipal;

    private JLabelOpcoesTelaSecao labelMapaBotao;
    private JLabelOpcoesTelaSecao labelBolsa;
    private JLabelOpcoesTelaSecao labelPocaoInicial;
    private JLabelOpcoesTelaSecao labelProvisoes;
    private JLabelOpcoesTelaSecao labelAnotacoes;
    private JLabel labelSalvar;
    private JDialog dialogImagemMapa;
    private JDialog dialogImSecaoAmpliar;
    private JLabel labelImagemSecao;
    private JLabelOpcoesTelaSecao labelSair;
    private JLabelOpcoesTelaSecao labelAumentaTexto;
    private JLabelOpcoesTelaSecao labelDiminuiTexto;
    private JLabelOpcoesTelaSecao labelVoz;
    private JLabelOpcoesTelaSecao labelVozParar;
    protected JTextPane textoHistoria;
    private JLabel lbEnergiaPersonagem;         //Informa o índice de energia atual e máxima
    private JLabel lbHabilidadePersonagem;      //Informa o índice de habilidade atual e máxima
    private JLabel lbSortePersonagem;           //Informa o índice de sorte atual e máxima
    private final EfeitoDeItens efeitoDeItens = new EfeitoDeItens();
    private int tamanhoTexto = 25; //tamanho default para o texto da seção. Pode ser ajustado
    private String enderecoAudioHistoriaInicial; //Se é a histório inicial. Carrega áudio da história inicial
    private final Util util = new Util(); //Usado para a a narração (play /stop)
    private final TelaSecoesBasica thisDialog = this; //Referencia esta tela para passar para a tela de mensaagem quando precisar fechar
    private static boolean respostaTelaMensagem = false; //Setado quando chamada a tela de confirmação e não é para fechar a tela
    protected JLabelOpcoesTelaSecao botaoOpcao1; //Uma das opções da seção
    //protected JLabelOpcoesTelaSecao botaoOpcao2; //Uma das opções da seção

    //public TelaSecoesBasica(){}

    public TelaSecoesBasica(Secao secao, JFrame referenciaTelaPrincipal) {
        setSize(1500,800);
        //Caso necessite alterar layout da tela para uma especifica. Necessário o Container
        Container tela = getContentPane();
        tela.setBackground(new Color(210,180,140));
        //tela.setLayout(null);
        setLayout(null);

        this.secao = secao;
        this.personagem = DadosLivroCarregado.getPersonagem();
        this.referenciaTelaPrincipal = referenciaTelaPrincipal;

        //sendo secao = null significa que é a tela de história inicial do jogo ainda não é uma seção
        if ( (secao != null) && (secao.getEnderecoImagem() != null) ) {
            this.enderecoImagem = secao.getEnderecoImagem();
            setTitle("Seção - " + secao.getCodSecaoLivro());
        }
        else{
            setTitle("Livro - " + DadosLivroCarregado.getLivro().getNome());
            enderecoAudioHistoriaInicial = DadosLivroCarregado.getLivro().getEnderecoAudio();
        }
        setType(Window.Type.UTILITY);

        setLocationRelativeTo(null);
        setModal(true);
        setUndecorated(true);

        //Carregar campo que receberá o texto da história
        carregarTextoHistoria();

        carregaImgSecao();
        carregaPainelPersonagem();
        carregarPainelDireito();
        carregarFaixasDasExtremidades();
        carregarComponentesEspecificos(secao);
        carregaPainelInferior();
    }

    private void carregarFaixasDasExtremidades() {
        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-115,-100,300,250);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(1310,-108,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(1325,650,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-110,675,300,250);


        add(labelFaixaInferiorDireita);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaSuperiorEsquerda);
        add(labelFaixaInferiorEsquerda);
    }

    protected abstract void carregarComponentesEspecificos(Secao secao);

    private void carregarTextoHistoria() {

        //Moldura que engloba o texto da seção
        JLabelOpcoesTelaSecao labelMolduraTexto = new JLabelOpcoesTelaSecao(null,900,600,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_2);
        labelMolduraTexto.setCursor(null);
        //labelMolduraTexto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Texto do livro
        textoHistoria = new JTextPane();
        textoHistoria.setBackground(new Color(210,180,140));
        textoHistoria.setFocusable(false);
        textoHistoria.setFont(new Font(Font.DIALOG,Font.BOLD,tamanhoTexto));

        StyledDocument textoLivroStyle = textoHistoria.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setForeground(configTexto,Color.BLACK);
        textoLivroStyle.setParagraphAttributes(0, textoLivroStyle.getLength(), configTexto, false);
        textoHistoria.setEditable(false);
        JScrollPane scrollTextoHistoria = new JScrollPane(textoHistoria);
        scrollTextoHistoria.setFocusable(true);
        scrollTextoHistoria.setBorder(null);

        //Carregando texto no componente
        if (secao != null)
            textoHistoria.setText( secao.getTexto() );
        else
            textoHistoria.setText( DadosLivroCarregado.getLivro().getHistoria() );

        //para posicionar a barra de rolagem no início.
        textoHistoria.setCaretPosition(0);

        //Aumenta texto
        labelAumentaTexto = new JLabelOpcoesTelaSecao(null, 40,40,
                ImagensDoLivroFlorestaDaDestruicao.SINAL_SOMA);
        labelAumentaTexto.setHorizontalAlignment(SwingConstants.CENTER);
        labelAumentaTexto.setToolTipText("Aumentar texto");
        labelAumentaTexto.addMouseListener(acaoLabels);
        //labelAumentaTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Diminuir texto
        labelDiminuiTexto = new JLabelOpcoesTelaSecao(null, 30,30,
                ImagensDoLivroFlorestaDaDestruicao.SINAL_MENOS);
        labelDiminuiTexto.setHorizontalAlignment(SwingConstants.CENTER);
        labelDiminuiTexto.setToolTipText("Diminuir texto");
        labelDiminuiTexto.addMouseListener(acaoLabels);
        //labelDiminuiTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Botão para narrar
        labelVoz = new JLabelOpcoesTelaSecao(null,45,45,
                ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ);
        labelVoz.addMouseListener(acaoLabels);
        labelVoz.setToolTipText("Narrador");

        //Botão para parar a narração
        labelVozParar = new JLabelOpcoesTelaSecao(null,45,45,
                ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_PARAR);
        labelVozParar.addMouseListener(acaoLabels);
        labelVozParar.setToolTipText("Parar a narração");

        //posicionamento na tela
        scrollTextoHistoria.setBounds(115, 118, 650, 350);
        labelMolduraTexto.setBounds(-10,-40,950,650);
        labelAumentaTexto.setBounds(80,260,30,30);
        labelDiminuiTexto.setBounds(777,260,20,30);

        labelVoz.setBounds(315,510,45,45);
        labelVozParar.setBounds(520,510,45,45);

        //Adicionando a tela
        add(labelVoz);
        add(labelVozParar);
        add(labelAumentaTexto);
        add(labelDiminuiTexto);
        add(scrollTextoHistoria);
        add(labelMolduraTexto);

    }

    private void carregaPainelPersonagem() {
        //Dados do Personagem
        ImagePanel painelPersonagem;
        painelPersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA);
        painelPersonagem.setLayout(null);

        //Nome do personagem
        JLabel lbNomePersonagem = new JLabel(personagem.getNome());
        lbNomePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,25));
        lbNomePersonagem.setForeground(new Color(139,0,0));
        lbNomePersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        //lbNomePersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Faixa pergaminho no personagem
        ImagePanel imgPainelNomePersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FAIXA_NOME_PERSONAGEM_TELA_SECAO);
        imgPainelNomePersonagem.setLayout(null);
        ImagePanel imgPainelHabilidadePersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        imgPainelHabilidadePersonagem.setLayout(null);
        ImagePanel imgPainelEnergiaPersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        imgPainelEnergiaPersonagem.setLayout(null);
        ImagePanel imgPainelSortePersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        imgPainelSortePersonagem.setLayout(null);
        //imgPainelHabilidadePersonagem.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Habilidade
        lbHabilidadePersonagem = new JLabel("Habilidade: "+
                String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                String.valueOf(personagem.getHabilidadeMax()));
        lbHabilidadePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbHabilidadePersonagem.setForeground(new Color(139,0,0));
        lbHabilidadePersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbHabilidadePersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbHabilidadePersonagem.setToolTipText("<html>Reflete a sua capacidade como espadachim e domínio geral das técnicas de luta.<br>Índice: (Atual/Máximo)</html>");

        //Energia
        lbEnergiaPersonagem = new JLabel("Energia: "+
                String.valueOf(personagem.getEnergiaAtual())+ "/"+
                String.valueOf(personagem.getEnergiaMax()));
        lbEnergiaPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbEnergiaPersonagem.setForeground(new Color(139,0,0));
        lbEnergiaPersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbEnergiaPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbEnergiaPersonagem.setToolTipText("<html>Reflete sua constituição física global, sua determinação de sobreviver, força de vontade e aptidão geral.<br>Índice: (Atual/Máximo)</html>");

        //Sorte
        lbSortePersonagem = new JLabel("Sorte: "+
                String.valueOf(personagem.getSorteAtual())+ "/"+
                String.valueOf(personagem.getSorteMax()));
        lbSortePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbSortePersonagem.setForeground(new Color(139,0,0));
        lbSortePersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbSortePersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbSortePersonagem.setToolTipText("<html>Seu índice SORTE indica o quanto você é, naturalmente, uma pessoa de sorte.<br>Índice: (Atual/Máximo)</html>");

        //Aqui define a imagem do Bárbaro(ou Bárara) do personagem
        //1 = Homem; 2 = Mulher
        String enderecoImgPersonagem;
        String toolTip;
        if (personagem.getGenero() == 1) {
            enderecoImgPersonagem = ImagensDoLivroFlorestaDaDestruicao.BARBARO.getEnderecoImagem();
            toolTip = "Este é você!";
        }
        else {
            enderecoImgPersonagem = ImagensDoLivroFlorestaDaDestruicao.BARBARA.getEnderecoImagem();
            toolTip = "Esta é você!";
        }

        ImagePanel imgPersonagem = new ImagePanel(enderecoImgPersonagem);
        imgPersonagem.setLayout(null);
        imgPersonagem.setToolTipText(toolTip);
        imgPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //imgPersonagem.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Bolsa
        labelBolsa = new JLabelOpcoesTelaSecao(null,ImagensDoLivroFlorestaDaDestruicao.BOLSA);
        labelBolsa.setToolTipText("Acesse aqui sua mochila.");
        labelBolsa.addMouseListener(acaoLabels);
        //labelBolsa.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelBolsa.setBounds(930,600,100,90);
        labelBolsa.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.BOLSA.getEnderecoImagem(),
                labelBolsa.getWidth(), labelBolsa.getHeight()).getImageIcon());


        //Posiciona
        imgPersonagem.setBounds(1080,500,205,260);
        lbSortePersonagem.setBounds(967,555,140,50);
        lbEnergiaPersonagem.setBounds(960,520,140,50);
        lbHabilidadePersonagem.setBounds(950,485,140,50);
        imgPainelNomePersonagem.setBounds(895,370,300,150);
        imgPainelHabilidadePersonagem.setBounds(910,490,200,40);
        imgPainelEnergiaPersonagem.setBounds(910,525,200,40);
        imgPainelSortePersonagem.setBounds(910,560,200,40);
        lbNomePersonagem.setBounds(900,415,300,50);
        painelPersonagem.setBounds(875, 367, 340, 375);

        //Adiciona a tela
        add(lbSortePersonagem);
        add(lbEnergiaPersonagem);
        add(lbHabilidadePersonagem);
        add(lbNomePersonagem);
        add(labelBolsa);
        add(imgPainelNomePersonagem);
        add(imgPainelSortePersonagem);
        add(imgPainelEnergiaPersonagem);
        add(imgPainelHabilidadePersonagem);
        add(imgPersonagem);
        add(painelPersonagem);
    }

    private void carregaImgSecao() {

        //Carrega imagem
        labelImagemSecao = new JLabel();
        labelImagemSecao.setHorizontalAlignment(SwingConstants.CENTER);
        ImagePanel imgMolduraParaImgSecao = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA);
        imgMolduraParaImgSecao.setLayout(null);

        //posicionamento
        imgMolduraParaImgSecao.setBounds(875,2,340,375);
        labelImagemSecao.setBounds(915, 45, 261, 289);
        labelImagemSecao.setIcon(new RedimensionarImagem(enderecoImagem, labelImagemSecao.getWidth(),
                labelImagemSecao.getHeight()).getImageIcon());
        labelImagemSecao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelImagemSecao.setToolTipText("Ampliar");
        labelImagemSecao.addMouseListener(acaoLabels);

        //Configura clique na imagem para ampliar em uma nova tela
        JLabel labelImagemTempoaria = new JLabel();
        labelImagemTempoaria.setBounds(0, 0, 590,715);
        labelImagemTempoaria.setIcon(new RedimensionarImagem(enderecoImagem, labelImagemTempoaria.getWidth(),
                labelImagemTempoaria.getHeight()).getImageIcon());
        labelImagemTempoaria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dialogImSecaoAmpliar = carregaImagemEmUmaTela();
        dialogImSecaoAmpliar.add(labelImagemTempoaria);
        dialogImSecaoAmpliar.addMouseListener(acaoLabels);



        //Adiciona componentes na tela atual
        add(labelImagemSecao);
        add(imgMolduraParaImgSecao);
    }

    protected void carregaPainelInferior() {
        ImagePanel imgPainelInferior = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_ABERTO);
        imgPainelInferior.setLayout(null);
        imgPainelInferior.setBounds(1,505,900,290);
        //imgPainelInferior.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(imgPainelInferior);
    }

    private void carregarPainelDireito() {
        ImagePanel imgPainelDireito = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FAIXA);
        imgPainelDireito.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(ImagensDoLivroFlorestaDaDestruicao.MAPA_DA_FLORESTA.getEnderecoImagem());
        JLabel labelImgMapa = new JLabel(imageIcon);
        labelImgMapa.setBounds(2,2,200,200);
        labelImgMapa.setBackground(new Color(210,180,140));
        dialogImagemMapa = carregaImagemEmUmaTela();
        dialogImagemMapa.add(labelImgMapa);
        dialogImagemMapa.addMouseListener(new TelaSecoesBasicaAcaoDosLabels());

        //Configura labelMapa(Botão)
        labelMapaBotao = new JLabelOpcoesTelaSecao("Mapa",50,55,ImagensDoLivroFlorestaDaDestruicao.BUSSOLA);
        labelMapaBotao.addMouseListener(acaoLabels);
        labelMapaBotao.setFont(new Font(Font.SERIF,Font.BOLD,19));

        //Configura a poção inicial (na condição sendo '0' é porque já foi usada)
        pocaoInicial = Util.retornaPocaoInicialDaBolsa();

        if (pocaoInicial == null)
            configuraPocaoVaziaQuandoPocaoInicialConsumida();

        if ( (pocaoInicial != null) && (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem()) ) {
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Habilidade", 30, 35, ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_HABILIDADE);
            labelPocaoInicial.setToolTipText("Repõe os pontos de HABILIDADE");
            labelPocaoInicial.setFont(new Font(Font.SERIF,Font.BOLD,18));
            labelPocaoInicial.setBounds(1285,265,150,100);
        }

        if ( (pocaoInicial != null) && (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DE_ENERGIA.getIdItem()) ) {
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Força", 40, 45,
                    ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_ENERGIA);
            labelPocaoInicial.setToolTipText("Repõe os pontos de ENERGIA");
            labelPocaoInicial.setFont(new Font(Font.SERIF,Font.BOLD,18));
            labelPocaoInicial.setBounds(1290,265,150,100);
        }
        if ( (pocaoInicial != null) && (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DA_FORTUNA.getIdItem()) ) {
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Fortuna", 30, 35,
                    ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_SORTE);
            labelPocaoInicial.setToolTipText("Repõe os pontos de SORTE e acrescenta 1 à SORTE Inicial");
            labelPocaoInicial.setFont(new Font(Font.SERIF,Font.BOLD,18));
            labelPocaoInicial.setBounds(1285,265,150,100);
        }

        if (pocaoInicial != null)
            labelPocaoInicial.addMouseListener(acaoLabels);

        //Provisões
        var textoProvisoes = "<html>Provisões:" + Util.quantidadeProvisoesRestantes() + "</html>";
        labelProvisoes = new JLabelOpcoesTelaSecao(textoProvisoes,40,45,
                ImagensDoLivroFlorestaDaDestruicao.PROVISOES);
        labelProvisoes.addMouseListener(acaoLabels);
        labelProvisoes.setFont(new Font(Font.SERIF,Font.BOLD,18));

        //Anotações
        labelAnotacoes = new JLabelOpcoesTelaSecao("Anotações",30,35,
                ImagensDoLivroFlorestaDaDestruicao.ANOTACOES);
        labelAnotacoes.setFont(new Font(Font.SERIF,Font.BOLD,20));
        labelAnotacoes.addMouseListener(acaoLabels);

        //Ouro
        JLabel labelOuro = new JLabel("Ouro: " + personagem.getQuantidadeOuro());
        labelOuro.setFont(new Font(Font.SERIF,Font.BOLD,19));
        labelOuro.setForeground(new Color(139,0,0));
        //labelOuro.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JLabelOpcoesTelaSecao labelFundoOuro = new JLabelOpcoesTelaSecao(null,170,150,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelFundoOuro.setHorizontalAlignment(SwingConstants.CENTER);

        //Salvar
        labelSalvar = new JLabel("Salvar");
        labelSalvar.setFont(new Font(Font.SERIF,Font.BOLD,20));
        labelSalvar.setForeground(new Color(139,0,0));
        labelSalvar.addMouseListener(acaoLabels);
        labelSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        labelSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSalvar.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabelOpcoesTelaSecao labelFundoMapa = new JLabelOpcoesTelaSecao(null,220,180,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        JLabelOpcoesTelaSecao labelFundoPocaoInicial = new JLabelOpcoesTelaSecao(null,220,180,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        JLabelOpcoesTelaSecao labelFundoProvisoes = new JLabelOpcoesTelaSecao(null,220,180,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        JLabelOpcoesTelaSecao labelFundoAnotacoes = new JLabelOpcoesTelaSecao(null,220,180,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        JLabelOpcoesTelaSecao labelFundoSalvar = new JLabelOpcoesTelaSecao(null,170,150,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelFundoSalvar.setHorizontalAlignment(SwingConstants.CENTER);

        labelSair = new JLabelOpcoesTelaSecao(null,130,110,
                ImagensDoLivroFlorestaDaDestruicao.PORTA_SAIR);
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.addMouseListener(acaoLabels);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Posicionamento
        labelOuro.setBounds(1300,60,85,55);
        labelFundoOuro.setBounds(1238,40,200,100);
        labelMapaBotao.setBounds(1285,157,120,80);
        labelFundoMapa.setBounds(1230,140,200,120);
        labelFundoPocaoInicial.setBounds(1230,260,200,120);
        labelProvisoes.setBounds(1260,375,250,100);
        labelFundoProvisoes.setBounds(1230,380,250,100);
        labelAnotacoes.setBounds(1280,495,250,100);
        labelFundoAnotacoes.setBounds(1230,500,250,100);
        labelSalvar.setBounds(1290,620,100,50);
        labelFundoSalvar.setBounds(1238,600,200,100);
        labelSair.setBounds(1285,700,100,100);
        labelSair.setToolTipText("Sair");


        imgPainelDireito.setBounds(1200,2,280,770);

        //Adiciona a tela
        add(labelOuro);
        add(labelFundoOuro);
        add(labelAnotacoes);
        add(labelFundoAnotacoes);
        add(labelProvisoes);
        add(labelFundoProvisoes);
        add(labelMapaBotao);
        add(labelFundoMapa);

        if (pocaoInicial != null)
            add(labelPocaoInicial);

        add(labelFundoPocaoInicial);
        add(labelSalvar);
        add(labelFundoSalvar);
        add(labelSair);
        add(imgPainelDireito);
    }

    //Substitui a imagem da poção inicial por um recipiente vazio
    private void configuraPocaoVaziaQuandoPocaoInicialConsumida(){
        String complementoTexto = ""; //Complemento do texto quando escolhida a poção da fortuna (sorte)
        boolean consumido = false;

        if (pocaoInicial == null) {
            labelPocaoInicial = new JLabelOpcoesTelaSecao("", 50, 55,
                    ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_VAZIA);
//            labelPocaoInicial.setIcon(Util.dimensionarImagem(50,55,
//                    ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_VAZIA.getEnderecoImagem()));
            labelPocaoInicial.setText("");
            labelPocaoInicial.setHorizontalAlignment(SwingConstants.CENTER);
            labelPocaoInicial.setBounds(1270,265,150,100);
            labelPocaoInicial.addMouseListener(null);
            labelPocaoInicial.setToolTipText("Poção consumida");
            add(labelPocaoInicial);
            return;
        }

        if (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DA_FORTUNA.getIdItem()){
            lbSortePersonagem.setText("Sorte: "+
                    String.valueOf(personagem.getSorteAtual())+ "/"+
                    String.valueOf(personagem.getSorteMax()));
            complementoTexto = " Além do incremento de 1 ponto no seu nível.";
            consumido = true;

        }

        if (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DE_ENERGIA.getIdItem()){

            lbEnergiaPersonagem.setText("Energia: "+
                    String.valueOf(personagem.getEnergiaAtual())+ "/"+
                    String.valueOf(personagem.getEnergiaMax()));
            consumido = true;
        }

        if (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem()){

            lbHabilidadePersonagem.setText("Habilidade: "+
                    String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                    String.valueOf(personagem.getHabilidadeMax()));
            consumido = true;
        }

        //Muda para recipiente vazio
        labelPocaoInicial.setIcon(Util.dimensionarImagem(50,55,
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_VAZIA.getEnderecoImagem()));
        labelPocaoInicial.setText("");
        labelPocaoInicial.setHorizontalAlignment(SwingConstants.CENTER);
        labelPocaoInicial.setBounds(1270,265,150,100);
        labelPocaoInicial.addMouseListener(null);
        labelPocaoInicial.setToolTipText("Poção consumida");


        CarregarTelas.telaMensagem(personagem.getNome().toUpperCase() +
                ", você toma a poção e se sente bem.\n\nSeu índice de " +
                pocaoInicial.getTipoEfeito().name().toLowerCase()
                + " encontra-se no nível máximo."+complementoTexto);
    }

    //Carrega uma tela de diálogo com uma imagem
    private JDialog carregaImagemEmUmaTela(){
        JDialog dialogImagem = new JDialog(this,"",false);
        dialogImagem.setSize(new Dimension(590,715));
        dialogImagem.setLocationRelativeTo(this); //Centralizar baseado na tela que a chama
        dialogImagem.setResizable(false);
        dialogImagem.getContentPane().setBackground(new Color(210,180,140));
        dialogImagem.setUndecorated(true); //retira a barra de ferramentas.
        dialogImagem.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return dialogImagem;
    }

    public boolean isRespostaTelaMensagem() {
        return respostaTelaMensagem;
    }

    public void setRespostaTelaMensagem(boolean respostaTelaMensagem) {
        this.respostaTelaMensagem = respostaTelaMensagem;
    }

    private class TelaSecoesBasicaAcaoDosLabels implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == labelVoz){

                //Não deixar ativar o som mais de uma vez
                if ( !labelVoz.isEnabled() )
                    return;

                if ( ( (enderecoAudioHistoriaInicial == null) ||
                        (enderecoAudioHistoriaInicial.isEmpty()) ) && (secao != null) )
                    util.reproduzirAudioMp3(secao.getEnderecoAudio());
                else
                    util.reproduzirAudioMp3(enderecoAudioHistoriaInicial);

                labelVoz.setEnabled(false);
            }

            if (e.getSource() == labelVozParar){
                util.pararAudioMp3();
                labelVoz.setEnabled(true);
            }

            if (e.getSource() == labelAumentaTexto){
                ++tamanhoTexto;
                textoHistoria.setFont(new Font(Font.DIALOG,Font.BOLD,tamanhoTexto));
            }

            if (e.getSource() == labelDiminuiTexto){
                --tamanhoTexto;
                textoHistoria.setFont(new Font(Font.DIALOG,Font.BOLD,tamanhoTexto));
            }

            if (e.getSource() == labelSair){

                CarregarTelas.telaMensagem("Deseja realmente sair?", thisDialog);
                if (isRespostaTelaMensagem()) {

                    if (referenciaTelaPrincipal != null)
                        referenciaTelaPrincipal.setVisible(true);

                    dispose();
                }

            }

            if (e.getSource() == labelMapaBotao){
                dialogImagemMapa.setVisible(true);
            }

            if (e.getSource() ==  labelImagemSecao){
                dialogImSecaoAmpliar.setVisible(true);
            }

            if (e.getSource() == labelBolsa){
                CarregarTelas.telaBolsa(getContentPane(),1000,800, lbEnergiaPersonagem, lbHabilidadePersonagem,
                        lbSortePersonagem, labelProvisoes, labelPocaoInicial);
            }

            if (e.getSource() == labelPocaoInicial){

                //Caso consumida via bolsa essa variável fica ativa no botão. Então verifica se ainda contém na bolsa.
                pocaoInicial = Util.retornaPocaoInicialDaBolsa();

                if ( pocaoInicial == null ) {
                    CarregarTelas.telaMensagem(personagem.getNome().toUpperCase() +
                            ", você já tomou a poção especial.");
                    return;
                }

                var foiConsumido = efeitoDeItens.acoesDosItens(pocaoInicial.getIdItem());

                if ( foiConsumido ) {
                    configuraPocaoVaziaQuandoPocaoInicialConsumida();
                    pocaoInicial = null;
                }
                else
                    CarregarTelas.telaMensagem(personagem.getNome().toUpperCase()+
                            ", seu índice de "+pocaoInicial.getTipoEfeito().name().toLowerCase()+
                            " encontra-se no nível máximo."+
                            "\n\nNão existe necessidade de tomar a poção.");
            }

            if (e.getSource() ==  labelProvisoes){

                //Testa se personagem encontra-se com energia cheia e o avisa.
                if (Util.retornaDiferencaEntreEnergiaMaxEAtual() == 0){
                    CarregarTelas.telaMensagem(personagem.getNome().toUpperCase()+", sua energia está completa."+
                            "\n\nNão existe necessidade de se alimentar.");
                    return;
                }

                //Testa se ainda existem provisões para comer
                if (Util.quantidadeProvisoesRestantes() > 0) {

                    //Aqui trata a ação de comer a provisão
                    //Item provisão
                    if  (efeitoDeItens.acoesDosItens(ItensMapeamento.PROVISAO.getIdItem()) ) {
                        lbEnergiaPersonagem.setText("Energia: " +
                                String.valueOf(personagem.getEnergiaAtual()) + "/" +
                                String.valueOf(personagem.getEnergiaMax()));
                        labelProvisoes.setText("<html>Provisões:" + Util.quantidadeProvisoesRestantes() + "</html>");
                        CarregarTelas.telaMensagem(personagem.getNome() + ", você recuperou 4 pontos de energia ao comer uma provisão(refeição).");
                    }
                }
                else
                    CarregarTelas.telaMensagem("Você não tem mais provisões para comer.");
            }

            if (e.getSource() ==  labelAnotacoes){
                CarregarTelas.telaAnotacoes(personagem);
            }

            if (e.getSource() ==  labelSalvar){
                CarregarTelas.telaMensagem("Deseja salvar o andamento do jogo?",thisDialog);

                //telaMensagem seta a resposta através da chamada ao
                // método RespostaTelaMensagem() da referência a tela "thisDialog"
                if (isRespostaTelaMensagem())
                    Util.salvarJogoEmArquivo(personagem.getNome(),new SaveJogo(personagem,secao));
            }

            if (e.getSource() ==  dialogImagemMapa){
                dialogImagemMapa.setVisible(false);
                dialogImagemMapa.dispose();
            }

            if (e.getSource() ==  dialogImSecaoAmpliar){
                dialogImSecaoAmpliar.setVisible(false);
                dialogImSecaoAmpliar.dispose();
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