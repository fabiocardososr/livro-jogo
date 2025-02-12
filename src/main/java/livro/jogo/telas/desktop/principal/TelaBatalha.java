package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.AcoesBatalha;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaBatalha extends JDialog {
    private final Inimigo inimigo;
    private static boolean respostaTelaConfirmacao;
    private final TelaBatalha tela = this;
    private final Personagem personagem = DadosLivroCarregado.getPersonagem();
    private final TelaSecoesBasica telaPai; //Tela que chama esta tela. Usada para voltar a aparecer a tela da seção
    private JLabel labelInfoQuemGanhouResultadoBatalha; //Aqui vai ser mostrado o resultado da batalha, quem venceu.
    private JLabel labelInfoRodada; //Informação da rodada que deve ser incrementada a cada rodada de luta
    private int quantidadeRodadas = 1; //Vai preencher o labelInfoRodada
    private boolean podeFugir; //Indica se existe a opção de fuga
    private final AcoesBatalha acoesBatalha;

    public TelaBatalha(Inimigo inimigo, TelaSecoesBasica telaPai, boolean podeFugir) {
        this.inimigo = inimigo;
        this.telaPai = telaPai;
        this.podeFugir = podeFugir;
        acoesBatalha = new AcoesBatalha(this.inimigo);
        var largura = 1050;
        var altura = 850;
        setSize(largura,altura);
        setLayout(null);
        setModal(true);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setCursor(null);
        setBackground(new Color(0,0,0,0));



        //Se na seção existir a opção de fuga
        if ( podeFugir )
            carregaBotaoFuga();

        carregaBotaoSorte();
        carregaPainelResultadoBatalha();
        carregaEnergiaSortePersonagem();
        carregaEnergiaInimigo();
        carregaBotaoLuta();
        carregaImagemMostraResultRolagemDado();
        carregarPainelEsquerdo();
        carregarPainelDireito();
        carregarBotaoSair();
        carregarFaixaTitulo();
        carregarFundoTela(largura,altura);

        System.out.println(this.inimigo);
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
        botaoSorte.setToolTipText("Tente a sorte para ampliar o dano no ataque ou diminuir o seu dano ao se defender.");
        botaoSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSorte.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JOptionPane.showMessageDialog(null,"TENTAR SORTE!");
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
        botaoFuga.setBounds(600,570, largura,altura);
        botaoFuga.setToolTipText("Abandonar a luta? Perde 2 de energia. Esse é o preço da covardia.");
        botaoFuga.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoFuga.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CarregarTelas.telaMensagem("Deseja abandonar a luta?"+
                        "\n\n"+personagem.getNome() +", você perderá 2 pontos de energia.\nEsse é o preço de sua covardia.", tela);

                //AQUI VAI FICAR O DECREMENTO DA ENERGIA
                //dEVE-SE VERIFICAR SE O PERSONAGEM MORREU COM ESSA PERDA DE ENERGIA


                if (respostaTelaConfirmacao) {

                    //Sorte
                    var usarSorte = false;
                    CarregarTelas.telaMensagem("Deseja tentar a sorte para diminuir o dano na fuga?\n\n"+
                            "Obs.: Todo teste de sorte decrementa em 1 seu índice de Sorte independentemente de sucesso ou não.", tela);
                    if (respostaTelaConfirmacao)
                       usarSorte = true;

                    var estaVivo = acoesBatalha.fuga(usarSorte);

                    if ( estaVivo ) {
                        telaPai.atualizaIndicesNaTelaDoPersonagem();
                        telaPai.setVisible(true);
                    }
                    else{
                        CarregarTelas.telaMensagem("Você foi ferido gravemente, sua energia chegou a zero.\n\n"+
                                "Sua aventura acaba aqui.");
                        telaPai.dispose();
                    }
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

        //botaoFuga.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(botaoFuga);
    }

    private void carregaEnergiaInimigo() {
        int largura = 95;
        int altura = 30;
        String textoHabilidade = "<html><center>Habilidade: "+ inimigo.getHabilidade()+"</center></html>";
        String textoEnergia = "<html><center>Energia: "+ inimigo.getEnergia()+"</center></html>";

        //Habilidade
        JLabelOpcoesTelaSecao placaDireitaHabilidade = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        placaDireitaHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        placaDireitaHabilidade.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placaDireitaHabilidade.setBounds(672,505, largura,altura);
        placaDireitaHabilidade.setCursor(null);

        JLabel labelDireitoHabilidade = new JLabel( textoHabilidade );
        labelDireitoHabilidade.setForeground(new Color(139,0,0));
        labelDireitoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        labelDireitoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,11));
        labelDireitoHabilidade.setBounds(680,508, largura-15,altura-10);
        labelDireitoHabilidade.setCursor(null);
        //labelDireitoHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Energia
        JLabelOpcoesTelaSecao placaDireitaEnergia = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        placaDireitaEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        placaDireitaEnergia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placaDireitaEnergia.setBounds(672,536, largura,altura);
        placaDireitaEnergia.setCursor(null);

        JLabel labelDireitoEnergia = new JLabel( textoEnergia );
        labelDireitoEnergia.setForeground(new Color(139,0,0));
        labelDireitoEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        labelDireitoEnergia.setFont(new Font(Font.SERIF,Font.BOLD,12));
        labelDireitoEnergia.setBounds(682,539, largura-20,altura-10);
        labelDireitoEnergia.setCursor(null);

        add(labelDireitoEnergia);
        add(placaDireitaEnergia);
        add(labelDireitoHabilidade);
        add(placaDireitaHabilidade);
    }

    private void carregaEnergiaSortePersonagem() {
        int largura = 95;
        int altura = 30;
        String textoEnergia = "<html><center>Energia: "+personagem.getEnergiaAtual()+"</center></html>";
        String textoSorte = "<html><center>Sorte: "+personagem.getSorteAtual()+"</center></html>";

        //Energia
        JLabelOpcoesTelaSecao placaEsquerdaEnergia = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        placaEsquerdaEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        placaEsquerdaEnergia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placaEsquerdaEnergia.setBounds(286,505, largura,altura);
        placaEsquerdaEnergia.setCursor(null);

        JLabel labelEsquerdoEnergia = new JLabel( textoEnergia );
        labelEsquerdoEnergia.setForeground(new Color(139,0,0));
        labelEsquerdoEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        labelEsquerdoEnergia.setFont(new Font(Font.SERIF,Font.BOLD,12));
        labelEsquerdoEnergia.setBounds(296,508, largura-20,altura-10);
        labelEsquerdoEnergia.setCursor(null);
        //labelEsquerdo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Sorte
        JLabelOpcoesTelaSecao placaEsquerdaSorte = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        placaEsquerdaSorte.setHorizontalAlignment(SwingConstants.CENTER);
        placaEsquerdaSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placaEsquerdaSorte.setBounds(286,535, largura,altura);
        placaEsquerdaSorte.setCursor(null);

        JLabel labelEsquerdoSorte = new JLabel( textoSorte );
        labelEsquerdoSorte.setForeground(new Color(139,0,0));
        labelEsquerdoSorte.setHorizontalAlignment(SwingConstants.CENTER);
        labelEsquerdoSorte.setFont(new Font(Font.SERIF,Font.BOLD,13));
        labelEsquerdoSorte.setBounds(296,538, largura-20,altura-10);
        labelEsquerdoSorte.setCursor(null);

        add(labelEsquerdoSorte);
        add(placaEsquerdaSorte);
        add(labelEsquerdoEnergia);
        add(placaEsquerdaEnergia);
    }

    private void carregaBotaoLuta() {
        int largura = 220;
        int altura = 200;

        JLabelOpcoesTelaSecao botaoEscudoBatalaha = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.ESCUDO_LUTA);
        botaoEscudoBatalaha.setHorizontalAlignment(SwingConstants.CENTER);
        botaoEscudoBatalaha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEscudoBatalaha.setBounds(415,435, largura,altura);

        //Label que informará qual a rodada de combate
        labelInfoRodada = new JLabel("<html><center>Turno<br><b>"+ quantidadeRodadas+"</b></center></html>");
        labelInfoRodada.setBounds(480,495,90,70);
        labelInfoRodada.setForeground(new Color(139,0,0));
        labelInfoRodada.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoRodada.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelInfoRodada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelInfoRodada.setToolTipText("Clique aqui para iniciar a rodada de combate jogando os dados.");
        labelInfoRodada.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                executarAcaoLuta();
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
        add(botaoEscudoBatalaha);
    }

    private void executarAcaoLuta() {


        //Configura a tela para que feche automaticamente em alguns milisegundos
//        Timer timer = new Timer(milisegundos, e -> {
//
//        });
//        timer.setRepeats(false);
//        timer.start();
        //Joga dados para o inimigo
//        TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS,
//                300,300);

        //Atualiza quantidade de rodadas
        quantidadeRodadas += 1;
        labelInfoRodada.setText("<html><center>Turno<br>"+ quantidadeRodadas+"</center></html>");

        //Atualiza a tela
        repaint();
    }

    private void carregaPainelResultadoBatalha() {
        JLabelOpcoesTelaSecao painelResultado = new JLabelOpcoesTelaSecao(null,
                250,150,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_14);
        painelResultado.setHorizontalAlignment(SwingConstants.CENTER);
        painelResultado.setCursor(null);
        painelResultado.setBounds(378,220, 300,200);

        //Informação do resultado da batalha(rodada de rolagem de dados)
        labelInfoQuemGanhouResultadoBatalha = new JLabel("<html><center>Inimigo venceu<br>esta rodada</center></html>");
        labelInfoQuemGanhouResultadoBatalha.setBounds(448,285,155,70);
        labelInfoQuemGanhouResultadoBatalha.setForeground(new Color(139,0,0));
        labelInfoQuemGanhouResultadoBatalha.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoQuemGanhouResultadoBatalha.setFont(new Font(Font.SERIF,Font.BOLD,20));
        //labelInfoQuemResultadoBatalha.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(labelInfoQuemGanhouResultadoBatalha);
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
        JLabel labelEsquerdo = new JLabel("0");
        labelEsquerdo.setBounds(410,395, 50,50);
        labelEsquerdo.setForeground(new Color(139,0,0));
        labelEsquerdo.setHorizontalAlignment(SwingConstants.CENTER);
        labelEsquerdo.setFont(new Font(Font.SERIF,Font.BOLD,35));
        //labelEsquerdo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Imagem direita
        JLabelOpcoesTelaSecao mostradorDireito = new JLabelOpcoesTelaSecao(null,
                100,100,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_LUTA);
        mostradorDireito.setHorizontalAlignment(SwingConstants.CENTER);
        mostradorDireito.setCursor(null);
        mostradorDireito.setBounds(570,380, 90,90);

        JLabel labelDireito = new JLabel("0");
        labelDireito.setBounds(590,395, 50,50);
        labelDireito.setForeground(new Color(139,0,0));
        labelDireito.setHorizontalAlignment(SwingConstants.CENTER);
        labelDireito.setFont(new Font(Font.SERIF,Font.BOLD,35));

        //X
        JLabelOpcoesTelaSecao mostraVersus = new JLabelOpcoesTelaSecao(null,
                50,50,
                ImagensDoLivroFlorestaDaDestruicao.X);
        mostraVersus.setHorizontalAlignment(SwingConstants.CENTER);
        mostraVersus.setCursor(null);
        mostraVersus.setBounds(500,405, 50,50);


        add(mostraVersus);
        add(labelDireito);
        add(labelEsquerdo);
        add(mostradorDireito);
        add(mostradorEsquerdo);

    }

    public static boolean isRespostaTelaConfirmacao() {
        return respostaTelaConfirmacao;
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

        JLabel labelInimigo = new JLabel(inimigo.getNome());
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
                    inimigo.getEnderecoImagem());
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

    private void carregarFaixasDasExtremidades() {
        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-115,-100,300,250);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(655,-108,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(670,550,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-115,560,300,250);


        add(labelFaixaInferiorDireita);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaSuperiorEsquerda);
        add(labelFaixaInferiorEsquerda);
    }
}
