package livro.jogo.telas.desktop;

import livro.jogo.entidades.Livro;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.utils.ManipularDadosLivro;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*System.exit(0) = Fecha aplicação toda*/

public class TelaPrincipal extends TelaBasica {
    private final Livro livro;
    private JButton botaoOpcoesRegras;
    private JButton botaoCarregarPersonagem;
    private JButton botaoIniciarJogo;
    private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private final CarregarTelas carregarTelas = new CarregarTelas(this);

    public TelaPrincipal(int largura, int altura) {
        super(largura,altura); //indico aqui o tamanho da tela

        //Dados do livro
        livro = ManipularDadosLivro.getLivro();
        setTitle(livro.getNome());
        configurandoTelaPrincipal();
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
        //configurarBotaoCriarPersonagem(acao);
        configurarCarregarPersonagem(acao);
        configurarBotaoIniciarJogo(acao);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurarBotaoIniciarJogo(TelaPrincipalAcaoDosBotoes acao) {
        botaoIniciarJogo = new JButton("Iniciar Jogo");
        botaoIniciarJogo.setBackground(Color.BLACK);
        botaoIniciarJogo.setForeground(Color.WHITE);
        botaoIniciarJogo.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoIniciarJogo.setBounds(590, 600,720,50);
        botaoIniciarJogo.setCursor(cursor);
        botaoIniciarJogo.addActionListener(acao);
        add(botaoIniciarJogo);


    }

    private void configurarCarregarPersonagem(TelaPrincipalAcaoDosBotoes acao) {
        botaoCarregarPersonagem = new JButton("Carregar Personagem");
        botaoCarregarPersonagem.setBackground(Color.BLACK);
        botaoCarregarPersonagem.setForeground(Color.WHITE);
        botaoCarregarPersonagem.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoCarregarPersonagem.setBounds(960, 660,350,50);
        botaoCarregarPersonagem.setCursor(cursor);
        botaoCarregarPersonagem.addActionListener(acao);
        add(botaoCarregarPersonagem);

    }

    private void configurarBotaoRegras(TelaPrincipalAcaoDosBotoes acao){
        botaoOpcoesRegras = new JButton("Regras");
        botaoOpcoesRegras.setBackground(Color.BLACK);
        botaoOpcoesRegras.setForeground(Color.WHITE);
        botaoOpcoesRegras.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoOpcoesRegras.setBounds(590, 660,350,50);
        botaoOpcoesRegras.setCursor(cursor);
        botaoOpcoesRegras.addActionListener(acao);
        add(botaoOpcoesRegras);
    }

    private class TelaPrincipalAcaoDosBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botaoOpcoesRegras){
                carregarTelas.carregarTela(TelasDisponiveisParaCarregamento.TELA_REGRAS_OPCOES,"","","");
            }

            if (e.getSource() == botaoCarregarPersonagem){
                JOptionPane.showMessageDialog(null,"Aqui vai ter uma tela para carregar personagens salvos");
            }

            if (e.getSource() == botaoIniciarJogo){
                carregarTelas.carregarTela(TelasDisponiveisParaCarregamento.TELA_CRIACAO_PERSONAGEM,"","","");
            }
        }
    } //FIM AcaoDosBotoes implements ActionListener

}
