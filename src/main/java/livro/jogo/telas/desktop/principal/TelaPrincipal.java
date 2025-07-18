package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Livro;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

/*System.exit(0) = Fecha aplicação toda*/

public class TelaPrincipal extends TelaBasica {
    private final Livro livro;
    private JLabel labelRegras;
    private JLabelOpcoesTelaSecao botaoRegras;
    private JLabel labelIniciarJogo;
    private JLabelOpcoesTelaSecao botaoIniciarJogo;
    private JLabel labelCarregarPersonagem;
    private JLabelOpcoesTelaSecao botaoCarregarPersonagem;
    private JLabel labelSair;
    private JLabelOpcoesTelaSecao botaoSair;
    private final int LARGURA_BOTOES_INFERIORES = 180;
    private final int ALTURA_BOTOES_INFERIORES = 130;
    private final CarregarTelas carregarTelas = new CarregarTelas(this);
    protected final Util util = new Util(); //Usado para a a narração (play /stop)
    private JLabelOpcoesTelaSecao labelVoz;
    private JLabelOpcoesTelaSecao labelVozParar;
    private TelaPrincipalAcaoDosLabelsBotoes acaoBotoes = new TelaPrincipalAcaoDosLabelsBotoes();;

    public TelaPrincipal(int largura, int altura) {
        super(largura,altura); //indico aqui o tamanho da tela
        getContentPane().setBackground(new Color(210,180,140));
        setUndecorated(true);

        //Dados do livro
        livro = DadosLivroCarregado.getLivro();
        setTitle(livro.getNome());
        configurandoTelaPrincipal();

        //para o áudio caso esteja sendo reproduzido
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent  e) {
                util.pararAudioMp3();
            }
        });
    }

    private void configurandoTelaPrincipal(){

        //Carregar capa do livro
        JLabelOpcoesTelaSecao labelImgCapaLivro = new JLabelOpcoesTelaSecao("",
                500, 622, ImagensDoLivroFlorestaDaDestruicao.CAPA);
        labelImgCapaLivro.setBounds(0,0,500,622);
        //labelImgCapaLivro.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        labelImgCapaLivro.setCursor(null);

        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao("",
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-130,-110,300,250);
        labelFaixaSuperiorEsquerda.setCursor(null);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao("",
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(1100,-110,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao("",
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(1095,500,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao("",
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-120,500,300,250);
        labelFaixaInferiorEsquerda.setCursor(null);

        //labelFaixaSuperiorEsquerda.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Configuração do estilo "textoCapaLivro"
        JTextPane textoCapaLivro = new JTextPane();
        textoCapaLivro.setBackground(new Color(210,180,140));
        textoCapaLivro.setFont(new Font(Font.SERIF,Font.BOLD,18));
        StyledDocument textoCapaLivroStyle = textoCapaLivro.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setForeground(configTexto,new Color(139,0,0));
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoCapaLivro.setEditable(false);
        textoCapaLivro.setFocusable(false);
        textoCapaLivro.setText(livro.getDescricao());
        JScrollPane scrollTextoIntrodutorio = new JScrollPane(textoCapaLivro);
        scrollTextoIntrodutorio.setBounds(602,108,560,272);

        //Fundo do texto (moldura)
        JLabelOpcoesTelaSecao labelMolduraTextoTelaPrincipal = new JLabelOpcoesTelaSecao("",
                800, 500, ImagensDoLivroFlorestaDaDestruicao.MOLDURA_TELA_PRINCIPAL);
        labelMolduraTextoTelaPrincipal.setCursor(null);

        //Botão para narrar
        labelVoz = new JLabelOpcoesTelaSecao(null,45,45,
                ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ);
        labelVoz.addMouseListener(acaoBotoes);
        labelVoz.setToolTipText("Narrador");

        //Botão para parar a narração
        labelVozParar = new JLabelOpcoesTelaSecao(null,45,45,
                ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_PARAR);
        labelVozParar.addMouseListener(acaoBotoes);
        labelVozParar.setToolTipText("Parar a narração");
        labelVoz.setBounds(810,420,45,45);
        labelVozParar.setBounds(912,420,45,45);

        /* Posicionanado */
        labelMolduraTextoTelaPrincipal.setBounds(480,-100,800,660);
        //labelMolduraTextoTelaPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Adicionando a tela
        add(labelVoz);
        add(labelVozParar);
        add(labelFaixaSuperiorEsquerda);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaInferiorDireita);
        add(labelFaixaInferiorEsquerda);

        add(scrollTextoIntrodutorio);
        add(labelImgCapaLivro);
        add(labelMolduraTextoTelaPrincipal);

        /* Carregando botões Inferiores e de voz */
        configurarBotaoRegras(acaoBotoes);
        configurarBotaoIniciarJogo(acaoBotoes);
        configurarBotaoCarregarPersonagem(acaoBotoes);
        configurarBotaoFechar(acaoBotoes);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurarBotaoRegras(TelaPrincipalAcaoDosLabelsBotoes acao){

        botaoRegras = new JLabelOpcoesTelaSecao("",
                LARGURA_BOTOES_INFERIORES,
                ALTURA_BOTOES_INFERIORES, ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoRegras.setBounds(505,470, LARGURA_BOTOES_INFERIORES, ALTURA_BOTOES_INFERIORES);
        botaoRegras.setFont(new Font(Font.SERIF,Font.BOLD,19));
        botaoRegras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoRegras.addMouseListener(acao);

        labelRegras = new JLabel("Regras");
        labelRegras.setForeground(new Color(139,0,0));
        labelRegras.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelRegras.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegras.setVerticalAlignment(SwingConstants.CENTER);
        labelRegras.setBounds(522,503,150,50);
        labelRegras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelRegras.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelRegras.addMouseListener(acao);

        add(labelRegras);
        add(botaoRegras);
    }

    private void configurarBotaoIniciarJogo(TelaPrincipalAcaoDosLabelsBotoes acao) {
        botaoIniciarJogo = new JLabelOpcoesTelaSecao("",
                LARGURA_BOTOES_INFERIORES,
                ALTURA_BOTOES_INFERIORES, ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoIniciarJogo.setBounds(695,470, LARGURA_BOTOES_INFERIORES, ALTURA_BOTOES_INFERIORES);
        botaoIniciarJogo.setFont(new Font(Font.SERIF,Font.BOLD,19));
        botaoIniciarJogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoIniciarJogo.addMouseListener(acao);

        labelIniciarJogo = new JLabel("Iniciar");
        labelIniciarJogo.setForeground(new Color(139,0,0));
        labelIniciarJogo.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelIniciarJogo.setHorizontalAlignment(SwingConstants.CENTER);
        labelIniciarJogo.setVerticalAlignment(SwingConstants.CENTER);
        labelIniciarJogo.setBounds(715,503,150,50);
        labelIniciarJogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelRegras.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelIniciarJogo.addMouseListener(acao);

        add(labelIniciarJogo);
        add(botaoIniciarJogo);
    }

    private void configurarBotaoCarregarPersonagem(TelaPrincipalAcaoDosLabelsBotoes acao) {

        botaoCarregarPersonagem = new JLabelOpcoesTelaSecao("",
                LARGURA_BOTOES_INFERIORES,
                ALTURA_BOTOES_INFERIORES, ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoCarregarPersonagem.setBounds(885,470, LARGURA_BOTOES_INFERIORES, ALTURA_BOTOES_INFERIORES);
        botaoCarregarPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,19));
        botaoCarregarPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoCarregarPersonagem.addMouseListener(acao);

        labelCarregarPersonagem = new JLabel("Carregar");
        labelCarregarPersonagem.setForeground(new Color(139,0,0));
        labelCarregarPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelCarregarPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelCarregarPersonagem.setVerticalAlignment(SwingConstants.CENTER);
        labelCarregarPersonagem.setBounds(902,503,150,50);
        labelCarregarPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelRegras.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelCarregarPersonagem.addMouseListener(acao);

        add(labelCarregarPersonagem);
        add(botaoCarregarPersonagem);
    }

    private void configurarBotaoFechar(TelaPrincipalAcaoDosLabelsBotoes acao) {
        botaoSair = new JLabelOpcoesTelaSecao("",
                LARGURA_BOTOES_INFERIORES,
                ALTURA_BOTOES_INFERIORES, ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoSair.setBounds(1075,470, LARGURA_BOTOES_INFERIORES, ALTURA_BOTOES_INFERIORES);
        botaoSair.setFont(new Font(Font.SERIF,Font.BOLD,19));
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSair.addMouseListener(acao);

        labelSair = new JLabel("Sair");
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setVerticalAlignment(SwingConstants.CENTER);
        labelSair.setBounds(1090,503,150,50);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelRegras.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelSair.addMouseListener(acao);

        add(labelSair);
        add(botaoSair);
    }

    private class TelaPrincipalAcaoDosLabelsBotoes implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == labelVoz){

                //Não deixar ativar o som mais de uma vez
                if ( !labelVoz.isEnabled() )
                    return;

                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/introducao.mp3", null);
                labelVoz.setEnabled(false);
            }

            if (e.getSource() == labelVozParar){
                util.pararAudioMp3();
                labelVoz.setEnabled(true);
            }

            if ( (e.getSource() == labelRegras) || (e.getSource() == botaoRegras) ){
                carregarTelas.carregarTela(TelasDisponiveisParaCarregamento.TELA_REGRAS_OPCOES,"","","");
            }

            if ( (e.getSource() == labelCarregarPersonagem) ||  (e.getSource() == botaoCarregarPersonagem)){
                carregarTelas.carregarTela(TelasDisponiveisParaCarregamento.TELA_CARREGAR_JOGO,"","","");
            }

            if ( (e.getSource() == labelIniciarJogo) || (e.getSource() == botaoIniciarJogo) ){
                carregarTelas.carregarTela(TelasDisponiveisParaCarregamento.TELA_CRIACAO_PERSONAGEM,"","","");
            }

            if ( (e.getSource() == labelSair) || (e.getSource() == botaoSair) ){
                System.exit(0);
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

            if (e.getSource() == labelVoz){
                labelVoz.setIcon(Util.dimensionarImagem(labelVoz.getWidth(),
                        labelVoz.getHeight(), ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_SELECIONADO.getEnderecoImagem()));
                repaint();
            }

            if (e.getSource() == labelVozParar){
                labelVozParar.setIcon(Util.dimensionarImagem(labelVozParar.getWidth(),
                        labelVozParar.getHeight(), ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_PARAR_SELECIONADO.getEnderecoImagem()));
                repaint();
            }

            if ( (e.getSource() == labelRegras) || (e.getSource() == botaoRegras) ){
                botaoRegras.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoRegras.getWidth(), botaoRegras.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelIniciarJogo) || (e.getSource() == botaoIniciarJogo) ){
                botaoIniciarJogo.setIcon(new RedimensionarImagem(
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoIniciarJogo.getWidth(), botaoIniciarJogo.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelCarregarPersonagem) ||  (e.getSource() == botaoCarregarPersonagem)){
                botaoCarregarPersonagem.setIcon(new RedimensionarImagem(
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoCarregarPersonagem.getWidth(), botaoCarregarPersonagem.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelSair) || (e.getSource() == botaoSair) ){
                botaoSair.setIcon(new RedimensionarImagem(
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
                repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == labelVoz){
                labelVoz.setIcon(Util.dimensionarImagem(labelVoz.getWidth(),
                        labelVoz.getHeight(), ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ.getEnderecoImagem()));
                repaint();
            }

            if (e.getSource() == labelVozParar){
                labelVozParar.setIcon(Util.dimensionarImagem(labelVozParar.getWidth(),
                        labelVozParar.getHeight(), ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_PARAR.getEnderecoImagem()));
                repaint();
            }

            if ( (e.getSource() == labelRegras) || (e.getSource() == botaoRegras) ){
                botaoRegras.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoRegras.getWidth(), botaoRegras.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelIniciarJogo) || (e.getSource() == botaoIniciarJogo) ){
                botaoIniciarJogo.setIcon(new RedimensionarImagem(
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoIniciarJogo.getWidth(), botaoIniciarJogo.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelCarregarPersonagem) ||  (e.getSource() == botaoCarregarPersonagem)){
                botaoCarregarPersonagem.setIcon(new RedimensionarImagem(
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoCarregarPersonagem.getWidth(), botaoCarregarPersonagem.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelSair) || (e.getSource() == botaoSair) ){
                botaoSair.setIcon(new RedimensionarImagem(
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
                repaint();
            }

        }
    } //FIM AcaoDosBotoes implements ActionListener

}