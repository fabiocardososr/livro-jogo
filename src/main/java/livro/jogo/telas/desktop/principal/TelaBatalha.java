package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ResultadoBatalha;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.*;

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
    private JPanel telaPanelRegrasSorte;
    public JLabel labelTextoTelaSuspensa;

    /* Setado pela função turnoDeBatalha() (classe AcoesBatalha)
       informa que terminou o turno de combate e tem a opção de usar a sorte.*/
    private boolean podeUsarASorte = false;
    private boolean podeUsarEspecialEscudoDeFerro;
    private boolean possuiEscudoFerro28;

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

        /* Verifica se possui Escudo de Ferro (item=28) que dá
          opção de rolagem de dado para reduzir o dano em 1 ponto.*/
        possuiEscudoFerro28 = UtilItemEquipado.verificaSeItemEquipado(28);

        /* Carregar componentes da tela */

        //Carrega botão de aumento/diminuição de velocidade do turno
        carregaBotao2x();

        //Carrega imagem da Poção de Habilidade com Armas(58)
        Item item = UtilItemEquipado.verificaSeItemEquipadoERetornaItem(58);
        if ( (item != null) && (item.getQuantidadeUso()>0) )
            carregaImagemPocaoHabilidadeComArmas();

        //Carrega a tela de mensagem suspensa, mas invisível
        telaMensagemSuspensaBotaoSorte();
        carregaBotaoSorte();
        carregaBotaoFuga();

        /* Se escudo de ferro do imperador (item=28) mostra opção de rolar dados.
           REGRA: Se o resultado for 4, 5 ou 6, os danos causados a você serão reduzidos em 1 ponto*/
        if ( possuiEscudoFerro28 )
            carregaBotaoEspecialEscudoFerroItem28();

        carregaPainelResultadoBatalha();
        carregaEnergiaSortePersonagem();
        carregaEnergiaInimigo();
        atualizarIndicesPersonagemInimigo();
        carregaBotaoLuta();
        carregaImagemMostraResultRolagemDado();
        carregarPainelEsquerdo();
        carregarPainelDireito();
        carregarFaixaTitulo();
        carregarFundoTela(largura,altura);
    }

    private void carregaBotao2x() {
        int largura = 60;
        int altura  = 40;

        JLabelOpcoesTelaSecao botao2x = new JLabelOpcoesTelaSecao(null,
                largura, altura, ImagensDoLivroFlorestaDaDestruicao.X_2);
        botao2x.setHorizontalAlignment(SwingConstants.CENTER);
        botao2x.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao2x.setBounds(770,235, largura, altura);
        botao2x.setToolTipText("Desativado - Velocidade do turno de batalha.");
        add(botao2x);
        botao2x.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( e.getSource() == botao2x ){

                    //Aumenta/diminui velocidade do turno
                    var velocidadeAumentada = acoesBatalha.velocidade2x();
                    if ( velocidadeAumentada ) {
                        botao2x.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.X_2_SELECIONADO.getEnderecoImagem(),
                                botao2x.getWidth(), botao2x.getHeight()).getImageIcon());
                        botao2x.setToolTipText("Ativado - Velocidade do turno de batalha.");
                    }
                    else {
                        botao2x.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.X_2.getEnderecoImagem(),
                                botao2x.getWidth(), botao2x.getHeight()).getImageIcon());
                        botao2x.setToolTipText("Desativado - Velocidade do turno de batalha.");
                    }


                    repaint();

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
    }

    //Item 58 - Poção de Habilidade com Armas - Seção 262
    //O efeito dá 1 ponto de força de ataque por duas batalhas
    //Aqui vai mostrar a imagem informando que o efeito ainda ativo
    private void carregaImagemPocaoHabilidadeComArmas() {
        Item item = UtilItemEquipado.verificaSeItemEquipadoERetornaItem(58);

        if (item == null)
            return;

        int largura = 60;
        int altura  = 60;

        JLabelOpcoesTelaSecao botaoHabComArmas = new JLabelOpcoesTelaSecao(null,
                largura, altura, ImagensDoLivroFlorestaDaDestruicao.EFEITO_POCAO_HAB_ARMAS);
        botaoHabComArmas.setHorizontalAlignment(SwingConstants.CENTER);
        botaoHabComArmas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoHabComArmas.setBounds(305,250, largura, altura);
        //botaoHabComArmas.setToolTipText("Poção De Habilidade com Armas Efeito ativo.");

        var texto = "<html><center><b>Efeito da Poção de Habilidade com Armas</b><br><br>"+
                " Adiciona 1 ponto ao ataque em batalhas. "+
                "<br><br>Efeito(s) Restante(s): "+ item.getQuantidadeUso()+
                " <br>(perde 1 efeito por cada batalha)</center></html>";

        botaoHabComArmas.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //Texto da regra que vai aparecer quando posicionar o mouse em cima do botão
                labelTextoTelaSuspensa.setText(texto);
                telaPanelRegrasSorte.setVisible(true);
                telaPanelRegrasSorte.setBounds(192,250, telaPanelRegrasSorte.getWidth(),
                        telaPanelRegrasSorte.getHeight());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                telaPanelRegrasSorte.setVisible(false);
                repaint();
            }
        });
//kkkk
        add(botaoHabComArmas);
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

    //Mensagem suspensa para o botão de sorte (explicando regras)
    //posicaoX e posicaoY é a posição do componente ao qual a tela vai mostrar a regra
    private void telaMensagemSuspensaBotaoSorte(){
        var largura = 680;
        var altura = 500;

        telaPanelRegrasSorte = new JPanel();
        telaPanelRegrasSorte.setLayout(null);
        telaPanelRegrasSorte.setBounds(192,100,largura,altura);
        telaPanelRegrasSorte.setBackground(new Color(0,0,0,0));
        //telaPanelRegrasSorte.setBorder(BorderFactory.createLineBorder(Color.RED));

        labelTextoTelaSuspensa = new JLabel("");
        labelTextoTelaSuspensa.setBounds(125,130,largura-250,altura-250);
        labelTextoTelaSuspensa.setForeground(new Color(139,0,0));
        labelTextoTelaSuspensa.setHorizontalAlignment(SwingConstants.CENTER);
        labelTextoTelaSuspensa.setFont(new Font(Font.SERIF,Font.BOLD,19));
        labelTextoTelaSuspensa.setCursor(null);
        //labelTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        telaPanelRegrasSorte.add(labelTextoTelaSuspensa);

        JLabelOpcoesTelaSecao fundo = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_14);
        fundo.setHorizontalAlignment(SwingConstants.CENTER);
        fundo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fundo.setBounds(0,0, largura,altura);
        fundo.setHorizontalAlignment(SwingConstants.CENTER);

        telaPanelRegrasSorte.add(fundo);

        add(telaPanelRegrasSorte);

        //Esconde a tela de regras(panel).
        //Somente irá aparecer quando posicionar o mouse em cima do botão
        telaPanelRegrasSorte.setVisible(false);
    }

    private void botaoMostraRegras(int posicaoX, int posicaoY, String texto){
        //Interrogação
        JLabelOpcoesTelaSecao botaoInterrogacaoSorte = new JLabelOpcoesTelaSecao(null,
                15,20,
                ImagensDoLivroFlorestaDaDestruicao.INTERROGACAO_2);
        botaoInterrogacaoSorte.setBounds(posicaoX,posicaoY, 15,20);
        botaoInterrogacaoSorte.setHorizontalAlignment(SwingConstants.CENTER);
        botaoInterrogacaoSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botaoInterrogacaoSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoInterrogacaoSorte.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //Texto da regra que vai aparecer quando posicionar o mouse em cima do botão
                labelTextoTelaSuspensa.setText(texto);
                telaPanelRegrasSorte.setVisible(true);
                botaoInterrogacaoSorte.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.INTERROGACAO_2_SELECIONADA.getEnderecoImagem(),
                        botaoInterrogacaoSorte.getWidth(), botaoInterrogacaoSorte.getHeight()).getImageIcon());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                telaPanelRegrasSorte.setVisible(false);
                botaoInterrogacaoSorte.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.INTERROGACAO_2.getEnderecoImagem(),
                        botaoInterrogacaoSorte.getWidth(), botaoInterrogacaoSorte.getHeight()).getImageIcon());
                repaint();
            }
        });
        //botaoInterrogacaoSorte.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(botaoInterrogacaoSorte);
    }

    private void carregaBotaoSorte() {
        int largura = 90;
        int altura  = 90;
        int posicaoX = 360;
        int posicaoY = 570;


        //Botao que exibe a regra da sorte
        var texto = "<html><center>Testar Sorte</center>" +
                "Rola 2 dados compara com sua sorte.<br>"+
                        "- IGUAL ou MENOR a seu índice: SUCESSO.<br>" +
                        "Sucesso no teste de sorte<br>"+
                        "- Venceu batalha: causa +2 de dano.<br>"+
                        "- Perdeu batalha: recupera +1.<br>"+
                        "Fracasso no teste de sorte<br>"+
                        "- Venceu batalha: inimigo recupera +1.<br>"+
                        "- Perdeu batalha: você toma +1 de dano.<br>"+
                        "</html>";
        botaoMostraRegras(posicaoX-5,posicaoY+30, texto);

        //Botão sorte
        JLabelOpcoesTelaSecao botaoSorte = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.TREVO);
        botaoSorte.setHorizontalAlignment(SwingConstants.CENTER);
        botaoSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSorte.setBounds(posicaoX,posicaoY, largura,altura);
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
                botaoSorte.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.TREVO_SELECIONADO.getEnderecoImagem(),
                        botaoSorte.getWidth(), botaoSorte.getHeight()).getImageIcon());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoSorte.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.TREVO.getEnderecoImagem(),
                        botaoSorte.getWidth(), botaoSorte.getHeight()).getImageIcon());
                repaint();
            }
        });

        add(botaoSorte);
    }

    private void carregaBotaoFuga() {
        int posicaoX = 480;
        int posicaoY = 635;
        int largura = 90;
        int altura  = 90;

        //Botão que exibe regra da fuga
        var texto = "<html><center>Fugir da Batalha</center>"+
                    "- A Criatura causa ferimento(+2 dano).<br><br>"+
                    "Opção: Testar a Sorte<br>"+
                    "- Sucesso: toma apenas 1 de dano<br>"+
                    "- Falha: +1 de dano (total: +3 dano)"+
                "</html>";
        botaoMostraRegras(posicaoX-10, posicaoY+45, texto);

        JLabelOpcoesTelaSecao botaoFuga = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.HOMEM_CORRENDO);
        botaoFuga.setHorizontalAlignment(SwingConstants.CENTER);
        botaoFuga.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoFuga.setBounds(posicaoX,posicaoY, largura,altura);
        //botaoFuga.setToolTipText("Abandonar a luta? Perde 2 de energia. Esse é o preço da covardia.");
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
                botaoFuga.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.HOMEM_CORRENDO_SELECIONADO.getEnderecoImagem(),
                        botaoFuga.getWidth(), botaoFuga.getHeight()).getImageIcon());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoFuga.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.HOMEM_CORRENDO.getEnderecoImagem(),
                        botaoFuga.getWidth(), botaoFuga.getHeight()).getImageIcon());
                repaint();
            }
        });

        //botaoFuga.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(botaoFuga);
    }

    private void carregaBotaoEspecialEscudoFerroItem28() {
        int largura = 60;
        int altura  = 60;
        int posicaoX = 620;
        int posicaoY = 575;

        //Botão que exibe regra do Escudo de ferro item 28
        var texto = "<html><center>Especial Escudo de Ferro"+
                "<br><br>Se resultado da rolagem = 4, 5 ou 6:<br>Dano será reduzido em 1 ponto."+
                "</center></html>";
        botaoMostraRegras(posicaoX+50, posicaoY+45, texto);

        JLabelOpcoesTelaSecao botaoEspecialEscudoDeFerro = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.UM_DADO);
        botaoEspecialEscudoDeFerro.setHorizontalAlignment(SwingConstants.CENTER);
        botaoEspecialEscudoDeFerro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEspecialEscudoDeFerro.setBounds(posicaoX,posicaoY, largura,altura);
        //botaoEspecialEscudoDeFerro.setToolTipText("REGRA: Resultado 4, 5 ou 6 os danos causados a você serão reduzidos em 1 ponto.");
        botaoEspecialEscudoDeFerro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEspecialEscudoDeFerro.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( labelInfoRodada.isEnabled() )

                    if ( (podeUsarEspecialEscudoDeFerro) && (labelInfoRodada.isEnabled()) ){
                        if ( acoesBatalha.testarDefesaEscudoDeFerroItem28() ) {
                            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
                            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                                    ",\n\nSucesso no teste com o escudo. Será reduzido em 1 ponto seu dano.");
                            UtilPersonagem.recuperaEnergia(1);
                            atualizarIndicesPersonagemInimigo();
                        }
                        else {
                            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                                    ",\n\nFalha! O escudo não foi capaz de aparar parte do dano.");
                        }
                        podeUsarEspecialEscudoDeFerro = false;
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
                botaoEspecialEscudoDeFerro.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.UM_DADO_SELECIONADO.getEnderecoImagem(),
                        botaoEspecialEscudoDeFerro.getWidth(), botaoEspecialEscudoDeFerro.getHeight()).getImageIcon());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoEspecialEscudoDeFerro.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.UM_DADO.getEnderecoImagem(),
                        botaoEspecialEscudoDeFerro.getWidth(), botaoEspecialEscudoDeFerro.getHeight()).getImageIcon());
                repaint();
            }
        });

        //botaoFuga.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(botaoEspecialEscudoDeFerro);
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

        //Botão mostrar regras
        var texto = "<html><center>Turno de Batalha</center>" +
                "Rola 2 dados para cada lutador.<br>"+
                "Habilidade + dados = Força de Ataque<br>" +
                "- Força de Ataque maior vence<br>"+
                "- Testar Sorte (ver regra de sorte)"+
                "</html>";
        botaoMostraRegras(590,600,texto);

        JLabelOpcoesTelaSecao botaoEscudoBatalha = new JLabelOpcoesTelaSecao(null,
                largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.ESCUDO_LUTA);
        botaoEscudoBatalha.setHorizontalAlignment(SwingConstants.CENTER);
        botaoEscudoBatalha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEscudoBatalha.setBounds(416,450, largura,altura);
        botaoEscudoBatalha.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( (e.getSource() == botaoEscudoBatalha) && ( labelInfoRodada.isEnabled()) ){
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
                botaoEscudoBatalha.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.ESCUDO_LUTA_SELECIONADO.getEnderecoImagem(),
                        botaoEscudoBatalha.getWidth(), botaoEscudoBatalha.getHeight()).getImageIcon());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoEscudoBatalha.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.ESCUDO_LUTA.getEnderecoImagem(),
                        botaoEscudoBatalha.getWidth(), botaoEscudoBatalha.getHeight()).getImageIcon());
                repaint();
            }
        });

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
                botaoEscudoBatalha.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.ESCUDO_LUTA_SELECIONADO.getEnderecoImagem(),
                        botaoEscudoBatalha.getWidth(), botaoEscudoBatalha.getHeight()).getImageIcon());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoEscudoBatalha.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.ESCUDO_LUTA.getEnderecoImagem(),
                        botaoEscudoBatalha.getWidth(), botaoEscudoBatalha.getHeight()).getImageIcon());
                repaint();
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

            if ( (possuiEscudoFerro28) && (resultadoBatalha == ResultadoBatalha.PERSONAGEM_PERDEU_TURNO) )
                podeUsarEspecialEscudoDeFerro = true;
            else
                podeUsarEspecialEscudoDeFerro = false;

            //Se o personagem morreu
            if (resultadoBatalha == ResultadoBatalha.PERSONAGEM_MORTO) {
                CarregarTelas.telaMensagem("Você foi derrotado.\n\nVocê está morto. Sua aventura acaba aqui.");
                telaPai.dispose();
                dispose();
                return;
            }

            //Se o inimigo morreu
            if (resultadoBatalha == ResultadoBatalha.INIMIGO_MORTO) {
                CarregarTelas.telaMensagem("Congratulações!\n\n"+personagem.getNome()+", você conseguiu sobrepujar o inimigo."+
                        "\n\nVocê venceu a batalha!");
                inimigoSecao.setEnergia(0);
                dispose();
                return;
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
        faixaNomePeronsagem.setCursor(null);

        JLabel labelPersonagem = new JLabel(personagem.getNome());
        labelPersonagem.setBounds(278,305,110,40);
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
        faixaNomeInimigo.setCursor(null);

        JLabel labelInimigo = new JLabel(inimigoTemporario.getNome());
        labelInimigo.setBounds(665,306,110,40);
        labelInimigo.setForeground(new Color(139,0,0));
        labelInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        labelInimigo.setFont(new Font(Font.SERIF,Font.BOLD,16));
        //labelPersonagem.setBorder(BorderFactory.createLineBorder(Color.RED));

        JPanel panelDireito = new JPanel();
        panelDireito.setLayout(null);
        panelDireito.setBounds(655,348,140,180);
        panelDireito.setBackground(new Color(0,0,0,0));

        //Moldura
        JLabelOpcoesTelaSecao moldura = new JLabelOpcoesTelaSecao(null,
                130,160,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_12);
        moldura.setHorizontalAlignment(SwingConstants.CENTER);
        moldura.setCursor(null);
        moldura.setBounds(0,0, 130,160);

        //Imagem do inimigo
        JLabelOpcoesTelaSecao imgPersonagem = new JLabelOpcoesTelaSecao(null, 75,110,
                    inimigoTemporario.getEnderecoImagem());
        imgPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        imgPersonagem.setBounds(30,25,75,110);
        imgPersonagem.setCursor(null);
        //imgPersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        panelDireito.add(imgPersonagem);
        panelDireito.add(moldura);
        add(labelInimigo);
        add(faixaNomeInimigo);
        add(panelDireito);
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
