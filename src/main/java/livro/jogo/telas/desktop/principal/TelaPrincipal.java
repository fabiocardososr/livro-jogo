package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Livro;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private final int LARGURA_BOTOES_INFERIORES = 220;
    private final int ALTURA_BOTOES_INFERIORES = 150;
    private final CarregarTelas carregarTelas = new CarregarTelas(this);

    public TelaPrincipal(int largura, int altura) {
        super(largura,altura); //indico aqui o tamanho da tela
        getContentPane().setBackground(new Color(210,180,140));
        setUndecorated(true);

        //Dados do livro
        livro = DadosLivroCarregado.getLivro();
        setTitle(livro.getNome());
        configurandoTelaPrincipal();
    }

    private void configurandoTelaPrincipal(){
        //Carregar capa do livro
        JLabel labelImgCapaLivro = new JLabel();
        labelImgCapaLivro.setIcon( new ImageIcon(livro.getImagemCapa()));

        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao("",
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-120,-100,300,250);
        labelFaixaSuperiorEsquerda.setCursor(null);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao("",
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(1255,-100,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao("",
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(1255,580,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao("",
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-130,580,300,250);
        labelFaixaInferiorEsquerda.setCursor(null);

        //labelFaixaSuperiorEsquerda.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Configuração do estilo "textoCapaLivro"
        JTextPane textoCapaLivro = new JTextPane();
        textoCapaLivro.setBackground(new Color(210,180,140));
        textoCapaLivro.setFont(new Font(Font.SERIF,Font.BOLD,17));
        StyledDocument textoCapaLivroStyle = textoCapaLivro.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setForeground(configTexto,new Color(139,0,0));
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoCapaLivro.setEditable(false);
        textoCapaLivro.setFocusable(false);
        textoCapaLivro.setText(livro.getDescricao());

        //Fundo do texto (moldura)
        JLabelOpcoesTelaSecao labelMolduraTextoTelaPrincipal = new JLabelOpcoesTelaSecao("",
                900, 600, ImagensDoLivroFlorestaDaDestruicao.MOLDURA_TELA_PRINCIPAL);
        labelMolduraTextoTelaPrincipal.setCursor(null);

        /* Posicionanado */
        labelImgCapaLivro.setBounds(-5,-10,490,730);
        textoCapaLivro.setBounds(635,145,633,325);
        labelMolduraTextoTelaPrincipal.setBounds(500,-40,900,660);
        //labelMolduraTextoTelaPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Adicionando a tela
        //add(tituloCapaLivro);
        add(labelFaixaSuperiorEsquerda);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaInferiorDireita);
        add(labelFaixaInferiorEsquerda);

        add(textoCapaLivro);
        add(labelImgCapaLivro);
        add(labelMolduraTextoTelaPrincipal);

        /* Carregando botões Inferiores */
        TelaPrincipalAcaoDosLabelsBotoes acaoBotoes = new TelaPrincipalAcaoDosLabelsBotoes();
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
        botaoRegras.setBounds(490,560, LARGURA_BOTOES_INFERIORES, ALTURA_BOTOES_INFERIORES);
        botaoRegras.setFont(new Font(Font.SERIF,Font.BOLD,19));
        botaoRegras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoRegras.addMouseListener(acao);

        labelRegras = new JLabel("Regras");
        labelRegras.setForeground(new Color(139,0,0));
        labelRegras.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelRegras.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegras.setVerticalAlignment(SwingConstants.CENTER);
        labelRegras.setBounds(525,603,150,50);
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
        botaoIniciarJogo.setBounds(720,560, LARGURA_BOTOES_INFERIORES, ALTURA_BOTOES_INFERIORES);
        botaoIniciarJogo.setFont(new Font(Font.SERIF,Font.BOLD,19));
        botaoIniciarJogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoIniciarJogo.addMouseListener(acao);

        labelIniciarJogo = new JLabel("Iniciar");
        labelIniciarJogo.setForeground(new Color(139,0,0));
        labelIniciarJogo.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelIniciarJogo.setHorizontalAlignment(SwingConstants.CENTER);
        labelIniciarJogo.setVerticalAlignment(SwingConstants.CENTER);
        labelIniciarJogo.setBounds(758,603,150,50);
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
        botaoCarregarPersonagem.setBounds(950,560, LARGURA_BOTOES_INFERIORES, ALTURA_BOTOES_INFERIORES);
        botaoCarregarPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,19));
        botaoCarregarPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoCarregarPersonagem.addMouseListener(acao);

        labelCarregarPersonagem = new JLabel("Carregar");
        labelCarregarPersonagem.setForeground(new Color(139,0,0));
        labelCarregarPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelCarregarPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelCarregarPersonagem.setVerticalAlignment(SwingConstants.CENTER);
        labelCarregarPersonagem.setBounds(988,603,150,50);
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
        botaoSair.setBounds(1180,560, LARGURA_BOTOES_INFERIORES, ALTURA_BOTOES_INFERIORES);
        botaoSair.setFont(new Font(Font.SERIF,Font.BOLD,19));
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSair.addMouseListener(acao);

        labelSair = new JLabel("Sair");
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,30));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setVerticalAlignment(SwingConstants.CENTER);
        labelSair.setBounds(1217,603,150,50);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelRegras.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelSair.addMouseListener(acao);

        add(labelSair);
        add(botaoSair);
    }

    private class TelaPrincipalAcaoDosLabelsBotoes implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
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