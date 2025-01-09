package livro.jogo.telas.desktop;

import livro.jogo.entidades.Livro;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.personalizados.BotaoFaixa;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
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
    private JLabel labelOpcoesRegras;
    private JLabel labelBotaoCarregarPersonagem;
    private JLabel labelBotaoIniciarJogo;
    private JLabel labelBotaoFechar;
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
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
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-120,-100,300,250);
        labelFaixaSuperiorEsquerda.setCursor(null);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(1255,-100,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(1255,580,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
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
        //StyleConstants.setFontSize(configTexto,17);
        StyleConstants.setForeground(configTexto,new Color(139,0,0));
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoCapaLivro.setEditable(false);
        textoCapaLivro.setFocusable(false);
        textoCapaLivro.setText(livro.getDescricao());

        //Fundo do texto (moldura)
        JLabelOpcoesTelaSecao labelMolduraTextoTelaPrincipal = new JLabelOpcoesTelaSecao(null,900,600,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_TELA_PRINCIPAL);
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
        BotaoFaixa botaoFaixa = new BotaoFaixa("Regras",ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelOpcoesRegras = botaoFaixa.criarBotao(getContentPane(), 480, 520,250,200,
                530, 590,150,50);
        labelOpcoesRegras.addMouseListener(acao);
    }

    private void configurarBotaoIniciarJogo(TelaPrincipalAcaoDosLabelsBotoes acao) {
        BotaoFaixa botaoFaixa = new BotaoFaixa("Iniciar",ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoIniciarJogo = botaoFaixa.criarBotao(getContentPane(),
                710, 520,250,200,
                763, 590,150,50);
        labelBotaoIniciarJogo.addMouseListener(acao);
    }

    private void configurarBotaoCarregarPersonagem(TelaPrincipalAcaoDosLabelsBotoes acao) {
        BotaoFaixa botaoFaixa = new BotaoFaixa("Carregar",ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoCarregarPersonagem = botaoFaixa.criarBotao(getContentPane(),
                940, 520,250,200,
                992, 590,150,50);
        labelBotaoCarregarPersonagem.addMouseListener(acao);
    }

    private void configurarBotaoFechar(TelaPrincipalAcaoDosLabelsBotoes acao) {
        BotaoFaixa botaoFaixa = new BotaoFaixa("Sair",ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoFechar = botaoFaixa.criarBotao(getContentPane(),
                1170, 520,250,200,
                1225, 590,150,50);
        labelBotaoFechar.addMouseListener(acao);
    }

    private class TelaPrincipalAcaoDosLabelsBotoes implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == labelOpcoesRegras){
                carregarTelas.carregarTela(TelasDisponiveisParaCarregamento.TELA_REGRAS_OPCOES,"","","");
            }

            if (e.getSource() == labelBotaoCarregarPersonagem){
                JOptionPane.showMessageDialog(null,"Aqui vai ter uma tela para carregar personagens salvos");
            }

            if (e.getSource() == labelBotaoIniciarJogo){
                carregarTelas.carregarTela(TelasDisponiveisParaCarregamento.TELA_CRIACAO_PERSONAGEM,"","","");
            }

            if (e.getSource() == labelBotaoFechar){
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

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    } //FIM AcaoDosBotoes implements ActionListener

}