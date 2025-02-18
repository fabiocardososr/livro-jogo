package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ResultadoBatalha;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.AcoesBatalha;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaBatalha extends JDialog {
    //É a referência ao inimigo da seção que não quero alterar, pois, caso o jogador fuja, o inimigo deverá está completo na volta
    private final Inimigo inimigoSecao;
    private static boolean respostaTelaConfirmacao;
    private final TelaBatalha tela = this;
    private final Personagem personagem = DadosLivroCarregado.getPersonagem();
    private final TelaSecoesBasica telaPai; //Tela que chama esta tela. Usada para voltar a aparecer a tela da seção
    private JLabel labelPainelMensagens; //Aqui vai ser mostrado o resultado da batalha, quem venceu.
    private JLabel labelInfoRodada; //Informação da rodada que deve ser incrementada a cada rodada de luta
    private int quantidadeRodadas = 1; //Vai preencher o labelInfoRodada
    private final AcoesBatalha acoesBatalha;
    private JLabel labelEnergiaPersonagem;
    private JLabel labelSortePersonagem;
    private JLabel labelHabilidadeInimigo;
    private JLabel labelEnergiaInimigo;
    private ResultadoBatalha resultadoBatalha = ResultadoBatalha.INICIO; //Recebe o resultado de cada turno
    private JLabel labelMostradorResultDadosPersonagem;
    private JLabel labelMostradorResultDadosInimigo;
    private JPanel panelBotao; //Ao derrotar o inimigo, pego compontente que está no panel e mudo a imagem
    private final Inimigo inimigoTemporario; //Toda a perda de energia será neste. Em caso de morte seto no da seção

    /* Setado pela função turnoDeBatalha() (classe AcoesBatalha)
       informa que terminou o turno de combate e tem a opção de usar a sorte.*/
    private boolean podeUsarASorte = false;

    public TelaBatalha(Inimigo inimigoSecao, TelaSecoesBasica telaPai, JPanel panelBotao) {
        this.inimigoSecao    = inimigoSecao;
        this.telaPai    = telaPai;
        this.panelBotao = panelBotao;

        //Cria nova instância do inimigo, pois se o jogador fugir e voltar o inimigo estará regenerado
        this.inimigoTemporario = new Inimigo(inimigoSecao.getIdInimigo(),inimigoSecao.getNome(),
                                inimigoSecao.getHabilidade(),inimigoSecao.getEnergia(),inimigoSecao.getEnderecoImagem());

        acoesBatalha    = new AcoesBatalha(this.inimigoTemporario, this, telaPai);
        var largura     = 1050;
        var altura      = 850;
        setSize(largura,altura);
        setLayout(null);
        setModal(true);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setCursor(null);
        setBackground(new Color(0,0,0,0));

        /* Carregar componentes da tela */
        carregaBotaoFuga();
        carregaBotaoSorte();
        carregaPainelResultadoBatalha();
        carregaEnergiaSortePersonagem();
        carregaEnergiaInimigo();
        atualizarIndicesPersonagemInimigo();
        carregaBotaoLuta();
        carregaImagemMostraResultRolagemDado();
        carregarPainelEsquerdo();
        carregarPainelDireito();
        //carregarBotaoSair();
        carregarFaixaTitulo();
        carregarFundoTela(largura,altura);
    }

    public JPanel getPanelBotao() {
        return panelBotao;
    }

    public int getQuantidadeRodadas() {
        return quantidadeRodadas;
    }

    public JLabel getLabelMostradorResultDadosPersonagem() {
        return labelMostradorResultDadosPersonagem;
    }

    public JLabel getLabelMostradorResultDadosInimigo() {
        return labelMostradorResultDadosInimigo;
    }

    //Seta a variável e a mesma é usada para indicar a tela que pode liberar o uso da sorte
    public void podeUsarASorte() {
        this.podeUsarASorte = true;
    }

    public void atualizarIndicesPersonagemInimigo(){
        String textoHabilidadeInimigo = "<html><center>Habilidade: "+ inimigoTemporario.getHabilidade()+"</center></html>";
        String textoEnergiaInimigo = "<html><center>Energia: "+ inimigoTemporario.getEnergia()+"</center></html>";
        String textoEnergiaPersonagem = "<html><center>Energia: "+personagem.getEnergiaAtual()+"</center></html>";
        String textoSortePersonagem = "<html><center>Sorte: "+personagem.getSorteAtual()+"</center></html>";

        labelEnergiaPersonagem.setText(textoEnergiaPersonagem);
        labelSortePersonagem.setText(textoSortePersonagem);
        labelHabilidadeInimigo.setText( textoHabilidadeInimigo );
        labelEnergiaInimigo.setText( textoEnergiaInimigo );
    }

    public JLabel getLabelPainelMensagens() {
        return labelPainelMensagens;
    }

    public static boolean isRespostaTelaConfirmacao() {
        return respostaTelaConfirmacao;
    }

    private void carregaBotaoSorte() {
        int largura = 90;
        int altura  = 90;

        JLabelOpcoesTelaSecao botaoSorte = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.TREVO);
        botaoSorte.setHorizontalAlignment(SwingConstants.CENTER);
        botaoSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSorte.setBounds(360,570, largura,altura);
        botaoSorte.setToolTipText("<html>TESTANDO A SORTE<br>SUCESSO:<br>- QUANDO VENCER O TURNO: +2 no dano causado.<br>- PERDEU O TURNO: -1 de dano.<br>"+
                "FRACASSO:<br>- QUANDO VENCER O TURNO: -1 no dano infligido.<br>- QUANDO PERDER O TURNO: +1 de dano."+
                "<br><br>REGRA: Serão rolados 2 dados.<br>- SUCESSO: Resultado IGUAL ou MENOR do que seu índice de sorte<br>"+
                "- FRACASSO: Resultado MAIOR do que seu índice de sorte</html>");
        botaoSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSorte.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( (podeUsarASorte) && (personagem.getSorteAtual() > 0) && (labelInfoRodada.isEnabled()) ) {
                    testarSorte();

                    //Só pode usar uma vez
                    podeUsarASorte = false;
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
        });

        //botaoFuga.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(botaoSorte);
    }

    private void carregaBotaoFuga() {
        int largura = 90;
        int altura  = 90;

        JLabelOpcoesTelaSecao botaoFuga = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.HOMEM_CORRENDO);
        botaoFuga.setHorizontalAlignment(SwingConstants.CENTER);
        botaoFuga.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoFuga.setBounds(480,635, largura,altura);
        botaoFuga.setToolTipText("Abandonar a luta? Perde 2 de energia. Esse é o preço da covardia.");
        botaoFuga.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoFuga.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( labelInfoRodada.isEnabled() )
                    acoesBatalha.acaoAoClicarNoBotaoFuga(telaPai);
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
        });

        //botaoFuga.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(botaoFuga);
    }

    private void carregaEnergiaInimigo() {
        int largura = 95;
        int altura = 30;

        //Habilidade
        JLabelOpcoesTelaSecao placaDireitaHabilidade = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        placaDireitaHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        placaDireitaHabilidade.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placaDireitaHabilidade.setBounds(672,505, largura,altura);
        placaDireitaHabilidade.setCursor(null);

        labelHabilidadeInimigo = new JLabel( "" );
        labelHabilidadeInimigo.setForeground(new Color(139,0,0));
        labelHabilidadeInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        labelHabilidadeInimigo.setFont(new Font(Font.SERIF,Font.BOLD,11));
        labelHabilidadeInimigo.setBounds(680,508, largura-15,altura-10);
        labelHabilidadeInimigo.setCursor(null);
        //labelDireitoHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Energia
        JLabelOpcoesTelaSecao placaDireitaEnergia = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        placaDireitaEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        placaDireitaEnergia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placaDireitaEnergia.setBounds(672,536, largura,altura);
        placaDireitaEnergia.setCursor(null);

        labelEnergiaInimigo = new JLabel( "" );
        labelEnergiaInimigo.setForeground(new Color(139,0,0));
        labelEnergiaInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        labelEnergiaInimigo.setFont(new Font(Font.SERIF,Font.BOLD,12));
        labelEnergiaInimigo.setBounds(682,539, largura-20,altura-10);
        labelEnergiaInimigo.setCursor(null);

        add(labelEnergiaInimigo);
        add(placaDireitaEnergia);
        add(labelHabilidadeInimigo);
        add(placaDireitaHabilidade);
    }

    private void carregaEnergiaSortePersonagem() {
        int largura = 95;
        int altura = 30;

        //Energia
        JLabelOpcoesTelaSecao placaEsquerdaEnergia = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        placaEsquerdaEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        placaEsquerdaEnergia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placaEsquerdaEnergia.setBounds(286,505, largura,altura);
        placaEsquerdaEnergia.setCursor(null);

        labelEnergiaPersonagem = new JLabel( "" );
        labelEnergiaPersonagem.setForeground(new Color(139,0,0));
        labelEnergiaPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelEnergiaPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,12));
        labelEnergiaPersonagem.setBounds(296,508, largura-20,altura-10);
        labelEnergiaPersonagem.setCursor(null);
        //labelEsquerdo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Sorte
        JLabelOpcoesTelaSecao placaEsquerdaSorte = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        placaEsquerdaSorte.setHorizontalAlignment(SwingConstants.CENTER);
        placaEsquerdaSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placaEsquerdaSorte.setBounds(286,535, largura,altura);
        placaEsquerdaSorte.setCursor(null);

        labelSortePersonagem = new JLabel( "" );
        labelSortePersonagem.setForeground(new Color(139,0,0));
        labelSortePersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelSortePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,13));
        labelSortePersonagem.setBounds(296,538, largura-20,altura-10);
        labelSortePersonagem.setCursor(null);

        add(labelSortePersonagem);
        add(placaEsquerdaSorte);
        add(labelEnergiaPersonagem);
        add(placaEsquerdaEnergia);
    }

    private void carregaBotaoLuta() {
        int largura = 225;
        int altura = 200;

        JLabelOpcoesTelaSecao botaoEscudoBatalha = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.ESCUDO_LUTA);
        botaoEscudoBatalha.setHorizontalAlignment(SwingConstants.CENTER);
        botaoEscudoBatalha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEscudoBatalha.setBounds(416,450, largura,altura);

        //Label que informará qual a rodada de combate
        labelInfoRodada = new JLabel("<html><center>Turno<br><b>"+ quantidadeRodadas+"</b></center></html>");
        labelInfoRodada.setBounds(477,500,95,100);
        labelInfoRodada.setForeground(new Color(139,0,0));
        labelInfoRodada.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoRodada.setFont(new Font(Font.SERIF,Font.BOLD,35));
        labelInfoRodada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelInfoRodada.setToolTipText("Clique aqui para iniciar a rodada de combate jogando os dados.");
        //labelInfoRodada.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelInfoRodada.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( labelInfoRodada.isEnabled() ) {
                    podeUsarASorte = false;
                    executarAcaoLuta();
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
        });

        add(labelInfoRodada);
        add(botaoEscudoBatalha);
    }

    private void testarSorte(){
        Thread novaThread = new Thread(() -> {

            //Desabilita o botão para não ser clicando enquanto não finalizar
            labelInfoRodada.setEnabled(false);

            //Testar a sorte (resultadoBatalha vem da função executarAcaoLuta() que é chamada antes)
            resultadoBatalha = acoesBatalha.testarSorte( resultadoBatalha );

            //Se o personagem morreu
            if (resultadoBatalha == ResultadoBatalha.PERSONAGEM_MORTO) {
                CarregarTelas.telaMensagem("Você foi derrotado.\n\nVocê está morto. Sua aventura acaba aqui.");
                telaPai.dispose();
                dispose();
            }

            //Se o inimigo morreu
            if (resultadoBatalha == ResultadoBatalha.INIMIGO_MORTO) {
                CarregarTelas.telaMensagem("Congratulações!\n\n"+personagem.getNome()+", você conseguiu sobrepujar o inimigo."+
                        "\n\nVocê venceu a batalha!");
                inimigoSecao.setEnergia(0);
                dispose();
            }

            //Habilita
            labelInfoRodada.setEnabled(true);
        });
        // Iniciando a Thread
        novaThread.start();
    }

    private void executarAcaoLuta() {

        /*Thread que executa um turno de batalha
          Foi necessário colocar em uma Thread porque preciso atualizar o painel de mensagens
          Com todos os passos de modo que o jogador possa acompanhar*/
        Thread novaThread = new Thread(() -> {

            //Desabilita o botão para não ser clicando enquanto não finalizar
            labelInfoRodada.setEnabled(false);

            //Lógica de um turno de batalha
            resultadoBatalha = acoesBatalha.turnoDeBatalha();

            //Se o personagem morreu
            if (resultadoBatalha == ResultadoBatalha.PERSONAGEM_MORTO) {
                CarregarTelas.telaMensagem("Você foi derrotado.\n\nVocê está morto. Sua aventura acaba aqui.");
                telaPai.dispose();
                dispose();
            }

            //Se o inimigo morreu
            if (resultadoBatalha == ResultadoBatalha.INIMIGO_MORTO) {
                CarregarTelas.telaMensagem("Congratulações!\n\n"+personagem.getNome()+", você conseguiu sobrepujar o inimigo."+
                        "\n\nVocê venceu a batalha!");
                inimigoSecao.setEnergia(0);
                dispose();
            }

            //Atualiza quantidade de rodadas
            quantidadeRodadas += 1;
            labelInfoRodada.setText("<html><center>Turno<br>"+ quantidadeRodadas+"</center></html>");
            repaint();

            //Habilita
            labelInfoRodada.setEnabled(true);
        });
        // Iniciando a Thread
        novaThread.start();
    }

    private void carregaPainelResultadoBatalha() {
        JLabelOpcoesTelaSecao painelResultado = new JLabelOpcoesTelaSecao(null,
                250,160,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_14);
        painelResultado.setHorizontalAlignment(SwingConstants.CENTER);
        painelResultado.setCursor(null);
        painelResultado.setBounds(378,220, 300,210);

        //Informação do resultado da batalha(rodada de rolagem de dados)
        labelPainelMensagens = new JLabel("Início da batalha!");
        labelPainelMensagens.setBounds(448,285,155,80);
        labelPainelMensagens.setForeground(new Color(139,0,0));
        labelPainelMensagens.setHorizontalAlignment(SwingConstants.CENTER);
        labelPainelMensagens.setFont(new Font(Font.SERIF,Font.BOLD,20));
        //labelPainelMensagens.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(labelPainelMensagens);
        add(painelResultado);
    }

    private void carregarFundoTela(int largura, int altura) {
        JLabelOpcoesTelaSecao textura = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_13);
        textura.setHorizontalAlignment(SwingConstants.CENTER);
        textura.setCursor(null);
        textura.setBounds(0,0, largura,altura);
        add(textura);
    }

    private void carregaImagemMostraResultRolagemDado() {

        //Imagem esquerda
        JLabelOpcoesTelaSecao mostradorEsquerdo = new JLabelOpcoesTelaSecao(null,
                100,100,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_LUTA);
        mostradorEsquerdo.setHorizontalAlignment(SwingConstants.CENTER);
        mostradorEsquerdo.setCursor(null);
        mostradorEsquerdo.setBounds(390,380, 90,90);
        //mostradorEsquerdo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Label esquerdo
        labelMostradorResultDadosPersonagem = new JLabel("0");
        labelMostradorResultDadosPersonagem.setBounds(410,395, 50,50);
        labelMostradorResultDadosPersonagem.setForeground(new Color(139,0,0));
        labelMostradorResultDadosPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelMostradorResultDadosPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,35));
        //labelEsquerdo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Imagem direita
        JLabelOpcoesTelaSecao mostradorDireito = new JLabelOpcoesTelaSecao(null,
                100,100,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_LUTA);
        mostradorDireito.setHorizontalAlignment(SwingConstants.CENTER);
        mostradorDireito.setCursor(null);
        mostradorDireito.setBounds(570,380, 90,90);

        labelMostradorResultDadosInimigo = new JLabel("0");
        labelMostradorResultDadosInimigo.setBounds(590,395, 50,50);
        labelMostradorResultDadosInimigo.setForeground(new Color(139,0,0));
        labelMostradorResultDadosInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        labelMostradorResultDadosInimigo.setFont(new Font(Font.SERIF,Font.BOLD,35));

        //X
        JLabelOpcoesTelaSecao mostraVersus = new JLabelOpcoesTelaSecao(null,
                50,50,
                ImagensDoLivroFlorestaDaDestruicao.X);
        mostraVersus.setHorizontalAlignment(SwingConstants.CENTER);
        mostraVersus.setCursor(null);
        mostraVersus.setBounds(500,405, 50,50);


        add(mostraVersus);
        add(labelMostradorResultDadosInimigo);
        add(labelMostradorResultDadosPersonagem);
        add(mostradorDireito);
        add(mostradorEsquerdo);

    }

    public void setRespostaTelaConfirmacao(boolean respostaTelaConfirmacao) {
        this.respostaTelaConfirmacao = respostaTelaConfirmacao;
    }

    private void carregarPainelEsquerdo() {
        JLabelOpcoesTelaSecao imgPersonagem;

        //Faixa onde ficará o nome do personagem
        JLabelOpcoesTelaSecao faixaNomePeronsagem = new JLabelOpcoesTelaSecao("",150,170,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        faixaNomePeronsagem.setBounds(258,290,150,80);

        JLabel labelPersonagem = new JLabel(personagem.getNome());
        labelPersonagem.setBounds(280,305,110,40);
        labelPersonagem.setForeground(new Color(139,0,0));
        labelPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,16));
        //labelPersonagem.setBorder(BorderFactory.createLineBorder(Color.RED));

        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(null);
        panelEsquerdo.setBounds(267,348,140,180);
        panelEsquerdo.setBackground(new Color(0,0,0,0));
        //panelEsquerdo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Moldura
        JLabelOpcoesTelaSecao moldura = new JLabelOpcoesTelaSecao(null,
                130,160,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_12);
        moldura.setHorizontalAlignment(SwingConstants.CENTER);
        moldura.setCursor(null);
        moldura.setBounds(0,0, 130,160);


        //Imagem do personagem se mulher ou homem
        if (personagem.getGenero() == 1)
            imgPersonagem = new JLabelOpcoesTelaSecao(null, 75,90,
                    ImagensDoLivroFlorestaDaDestruicao.BARBARO.getEnderecoImagem());
        else
            imgPersonagem = new JLabelOpcoesTelaSecao(null, 75,90,
                    ImagensDoLivroFlorestaDaDestruicao.BARBARA.getEnderecoImagem());


        imgPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        imgPersonagem.setBounds(30,35,75,90);
        imgPersonagem.setCursor(null);
        //imgPersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        panelEsquerdo.add(imgPersonagem);
        panelEsquerdo.add(moldura);
        add(labelPersonagem);
        add(faixaNomePeronsagem);
        add(panelEsquerdo);

    }

    private void carregarPainelDireito() {

        //Faixa onde ficará o nome do personagem
        JLabelOpcoesTelaSecao faixaNomeInimigo = new JLabelOpcoesTelaSecao("",150,170,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        faixaNomeInimigo.setBounds(645,290,150,80);

        JLabel labelInimigo = new JLabel(inimigoTemporario.getNome());
        labelInimigo.setBounds(665,305,110,40);
        labelInimigo.setForeground(new Color(139,0,0));
        labelInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        labelInimigo.setFont(new Font(Font.SERIF,Font.BOLD,16));
        //labelPersonagem.setBorder(BorderFactory.createLineBorder(Color.RED));

        JPanel panelDireito = new JPanel();
        panelDireito.setLayout(null);
        panelDireito.setBounds(652,348,140,180);
        panelDireito.setBackground(new Color(0,0,0,0));

        //Moldura
        JLabelOpcoesTelaSecao moldura = new JLabelOpcoesTelaSecao(null,
                130,160,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_12);
        moldura.setHorizontalAlignment(SwingConstants.CENTER);
        moldura.setCursor(null);
        moldura.setBounds(0,0, 130,160);

        //Imagem do inimigo
        JLabelOpcoesTelaSecao imgPersonagem = new JLabelOpcoesTelaSecao(null, 75,90,
                    inimigoTemporario.getEnderecoImagem());
        imgPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        imgPersonagem.setBounds(30,35,75,90);
        imgPersonagem.setCursor(null);
        //imgPersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        panelDireito.add(imgPersonagem);
        panelDireito.add(moldura);
        add(labelInimigo);
        add(faixaNomeInimigo);
        add(panelDireito);
    }

    private void carregarBotaoSair() {
        JLabel label = new JLabel("Sair");
        label.setForeground(new Color(139,0,0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SERIF,Font.BOLD,30));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setBounds(442,700, 180,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabelOpcoesTelaSecao labelBotaoSair = new JLabelOpcoesTelaSecao(null,230,185,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelBotaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelBotaoSair.setBounds(414,640, 230,185);
        labelBotaoSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CarregarTelas.telaMensagem("Deseja abandonar a luta?\n\n A criatura será regenerada, mas você não.", tela);

                if (respostaTelaConfirmacao) {

                    if (telaPai != null)
                        telaPai.setVisible(true);

                    dispose();
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
        });

        add(label);
        add(labelBotaoSair);
    }

    private void carregarFaixaTitulo(){
        //Label
        JLabel label = new JLabel("Batalha");
        label.setForeground(new Color(139,0,0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SERIF,Font.BOLD,45));
        label.setBounds(428,115, 200,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabelOpcoesTelaSecao labelFaixaFaixaTitulo = new JLabelOpcoesTelaSecao(null,
                400, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_11);
        labelFaixaFaixaTitulo.setBounds(328,10,400,250);
        //labelFaixaFaixaTitulo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(label);
        add(labelFaixaFaixaTitulo);
    }
}
