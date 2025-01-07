package livro.jogo.telas.desktop.personalizados;

import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TelaDeMensagensAoJogador extends JDialog {
    private Personagem personagem;
    private String texto;

    public TelaDeMensagensAoJogador(Personagem personagem, String texto) {
        this.personagem = personagem;
        this.texto = texto;
        int largura = 1000;
        int altura = 700;

        setSize(largura,altura);
        //Caso necessite alterar layout da tela para uma especifica. Necessário o Container
        //setBackground(new Color(210,180,140));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setModal(true);
        setBackground(new Color(0,0,0,0));

        carregarfundo(largura, altura);
        carregarTexto();
    }

    public TelaDeMensagensAoJogador( String texto) {
        this.texto = texto;
        int largura = 1000;
        int altura = 700;

        setSize(largura,altura);
        //Caso necessite alterar layout da tela para uma especifica. Necessário o Container
        //setBackground(new Color(210,180,140));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setModal(true);
        setBackground(new Color(0,0,0,0));

        carregarfundo(largura, altura);
        carregarTexto();
    }

    private void carregarTexto() {

        //Texto
        JTextPane textoHistoria = new JTextPane();
        textoHistoria.setBackground(new Color(210,180,140));
        textoHistoria.setFocusable(false);

        //Use esta linha como exemplo para aumentar ou diminuir tamanho da font
        textoHistoria.setFont(new Font(Font.DIALOG,Font.BOLD,18));


        StyledDocument textoLivroStyle = textoHistoria.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setForeground(configTexto,Color.WHITE);
        textoLivroStyle.setParagraphAttributes(0, textoLivroStyle.getLength(), configTexto, false);
        textoHistoria.setEditable(false);
        textoHistoria.setFocusable(false);
        textoHistoria.setBackground(new Color(0,0,0,0));
        textoHistoria.setBounds(330,200,350,350);
        textoHistoria.setText(this.texto);
        textoHistoria.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JScrollPane scrollTextoHistoria = new JScrollPane(textoHistoria);
        scrollTextoHistoria.setFocusable(true);
        scrollTextoHistoria.setBorder(null);

        add(textoHistoria);
    }

    private void carregarfundo(int largura, int altura) {

        //Fundo
        JLabelOpcoesTelaSecao labelFundo = new JLabelOpcoesTelaSecao("",largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_5);
        labelFundo.setBounds(0,0,largura,altura);
        //labelFundo.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        labelFundo.setHorizontalAlignment(SwingConstants.CENTER);

        add(labelFundo);
    }
}
