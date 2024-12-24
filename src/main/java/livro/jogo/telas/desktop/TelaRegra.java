package livro.jogo.telas.desktop;

import livro.jogo.telas.desktop.personalizados.TelaBasica;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TelaRegra extends TelaBasica {
    private final String titulo;
    private final String texto;

    public TelaRegra(int largura, int altura, String titulo, String texto) {
        super(largura,altura);
        this.titulo = titulo;
        this.texto = texto;

        carregarCompenentesTela();
    }

    private void carregarCompenentesTela(){

        //Configurando label do título
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setFont(new Font(Font.SERIF,Font.BOLD,35));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        //Criar campo que receberá o texto
        JTextPane textoRegra = new JTextPane();
        textoRegra.setBackground(Color.BLACK);
        StyledDocument textoCapaLivroStyle = textoRegra.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(configTexto,20);
        StyleConstants.setForeground(configTexto,Color.WHITE);
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoRegra.setEditable(false);
        textoRegra.setText(texto);
        textoRegra.setCaretPosition(0); //para posicionar a a barra de rolagem no início.
        JScrollPane scrollTextoRegra = new JScrollPane(textoRegra);
        scrollTextoRegra.setFocusable(true);


        //Posicionamento
        labelTitulo.setBounds(0,2,1200,100);
        scrollTextoRegra.setBounds(10,80,1160,650);

        //Adicionar a tela
        add(labelTitulo);
        add(scrollTextoRegra);
    }
}
