package livro.jogo.telas.desktop;

import livro.jogo.criarLivro.entidades.Livro;
import livro.jogo.utils.ManipularDados;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TelaPrincipal extends Tela {
    private final Livro livro;
    private JButton botaoRegras;
    private JButton botaoCriarPersonagem;
    private JButton botaoCarregarPersonagem;
    private JButton botaoIniciarJogo;

    public TelaPrincipal(int largura, int altura) {
        super(largura,altura); //indico aqui o tamanho da tela

        //Dados do livro
        livro = ManipularDados.getLivro();
        setTitle(livro.getNome());
        carregarIconTela();
        configurandoTelaPrincipal();
    }

    private void carregarIconTela(){
        BufferedImage img;
        try {
            img = ImageIO.read(new File(livro.getImagemCapa()));
            setIconImage(img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void configurandoTelaPrincipal(){

        //Carregar capa do livro
        JLabel labelImgCapaLivro = new JLabel();
        labelImgCapaLivro.setIcon( new ImageIcon(livro.getImagemCapa()));

        //Configurando o "tituloCapaLivro"
        JLabel tituloCapaLivro = new JLabel(livro.getNome());
        tituloCapaLivro.setForeground(Color.WHITE);
        tituloCapaLivro.setFont(new Font(Font.SERIF,Font.BOLD,25));

        //Configuração do estilo "textoCapaLivro"
        JTextPane textoCapaLivro = new JTextPane();
        textoCapaLivro.setBackground(Color.BLACK);
        StyledDocument textoCapaLivroStyle = textoCapaLivro.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(configTexto,22);
        StyleConstants.setForeground(configTexto,Color.WHITE);
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoCapaLivro.setEditable(false);
        textoCapaLivro.setText(livro.getDescricao());

        /* Posicionanado */
        labelImgCapaLivro.setBounds(0,0,490,760);
        tituloCapaLivro.setBounds(750,10,800,100);
        textoCapaLivro.setBounds(500,100,900,450);

        //Adicionando a tela
        add(tituloCapaLivro);
        add(textoCapaLivro);
        add(labelImgCapaLivro);

        /* Carregando botões Inferiores */
        TelaPrincipalAcaoDosBotoes acao = new TelaPrincipalAcaoDosBotoes();
        configurarBotaoRegras(acao);
        configurarBotaoCriarPersonagem(acao);
        configurarCarregarPersonagem(acao);
        configurarBotaoIniciarJogo(acao);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurarBotaoIniciarJogo(TelaPrincipalAcaoDosBotoes acao) {
        botaoIniciarJogo = new JButton("Iniciar Jogo");
        botaoIniciarJogo.setBackground(Color.BLACK);
        botaoIniciarJogo.setForeground(Color.WHITE);
        botaoIniciarJogo.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoIniciarJogo.setBounds(590, 660,720,50);
        botaoIniciarJogo.addActionListener(acao);
        add(botaoIniciarJogo);


    }

    private void configurarCarregarPersonagem(TelaPrincipalAcaoDosBotoes acao) {
        botaoCarregarPersonagem = new JButton("Carregar Personagem");
        botaoCarregarPersonagem.setBackground(Color.BLACK);
        botaoCarregarPersonagem.setForeground(Color.WHITE);
        botaoCarregarPersonagem.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoCarregarPersonagem.setBounds(1090, 600,220,50);
        botaoCarregarPersonagem.addActionListener(acao);
        add(botaoCarregarPersonagem);

    }

    private void configurarBotaoCriarPersonagem(TelaPrincipalAcaoDosBotoes acao) {
        botaoCriarPersonagem = new JButton("Criar Personagem");
        botaoCriarPersonagem.setBackground(Color.BLACK);
        botaoCriarPersonagem.setForeground(Color.WHITE);
        botaoCriarPersonagem.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoCriarPersonagem.setBounds(840, 600,220,50);
        botaoCriarPersonagem.addActionListener(acao);
        add(botaoCriarPersonagem);
    }

    private void configurarBotaoRegras(TelaPrincipalAcaoDosBotoes acao){
        botaoRegras = new JButton("Regras");
        botaoRegras.setBackground(Color.BLACK);
        botaoRegras.setForeground(Color.WHITE);
        botaoRegras.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRegras.setBounds(590, 600,220,50);
        botaoRegras.addActionListener(acao);
        add(botaoRegras);
    }

    private class TelaPrincipalAcaoDosBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botaoRegras){
                TelaRegrasOpcoes telaRegrasOpcoes = new TelaRegrasOpcoes(305,400, livro);
                telaRegrasOpcoes.setVisible(true);
            }

            if (e.getSource() == botaoCriarPersonagem){

            }

            if (e.getSource() == botaoCarregarPersonagem){

            }

            if (e.getSource() == botaoIniciarJogo){

            }
        }
    } //FIM AcaoDosBotoes implements ActionListener

}
