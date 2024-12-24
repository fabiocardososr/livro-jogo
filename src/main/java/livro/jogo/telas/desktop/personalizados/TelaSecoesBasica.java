package livro.jogo.telas.desktop.personalizados;

import livro.jogo.entidades.Secao;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TelaSecoesBasica extends TelaBasica{
    private final Secao secao;

    public TelaSecoesBasica(Secao secao) {
        super(1500,800); //Tamanho comum para todas as telas de seções
        this.secao = secao;
        setTitle("Seção - "+secao.getCodSecaoLivro());
        setType(Window.Type.UTILITY);

        //JOptionPane.showMessageDialog(null,this.secao.getTexto());

        //Carregar campo que receberá o texto da história
        carregarTextoHistoria();
    }

    private void carregarTextoHistoria() {
        JTextPane textoHistoria = new JTextPane();

        textoHistoria.setBackground(Color.BLACK);
        StyledDocument textoCapaLivroStyle = textoHistoria.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(configTexto,20);
        StyleConstants.setForeground(configTexto,Color.WHITE);
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoHistoria.setEditable(false);
        textoHistoria.setCaretPosition(0); //para posicionar a a barra de rolagem no início.
        JScrollPane scrollTextoRegra = new JScrollPane(textoHistoria);
        scrollTextoRegra.setFocusable(true);

        //Carregando texto no componente
        textoHistoria.setText( secao.getTexto() );


        //Posicionamento

        scrollTextoRegra.setBounds(10,80,1200,600);

        //Adicionando a tela
        add(scrollTextoRegra);
    }


}
