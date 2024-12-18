package livro.jogo.telas.desktop;

import livro.jogo.criarLivro.entidades.Livro;
import livro.jogo.criarLivro.utils.ManipularDados;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TelaRegras extends Tela {

    public TelaRegras(int largura, int altura) {
        super(1200,790);
        carregarCompenentesTela();
    }

    private void carregarCompenentesTela(){
        Livro livro = ManipularDados.getLivro();

        //Regra para montar índices do Personagem
        JLabel labelIndiceInical = new JLabel("Montar Índices do Personagem");
        labelIndiceInical.setForeground(Color.WHITE);
        labelIndiceInical.setFont(new Font(Font.SERIF,Font.BOLD,25));

        // exibe um diálogo com uma mensagemÿ
        //JOptionPane.showMessageDialog(null, livro.getRegraCalcularIndicesIniciais());

        JTextPane textoRegraCriarIndicePersonagem = new JTextPane();
        textoRegraCriarIndicePersonagem.setBackground(Color.BLACK);
        StyledDocument textoCapaLivroStyle = textoRegraCriarIndicePersonagem.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(configTexto,20);
        StyleConstants.setForeground(configTexto,Color.WHITE);
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoRegraCriarIndicePersonagem.setEditable(false);
        textoRegraCriarIndicePersonagem.setText(livro.getRegraCalcularIndicesIniciais());
        JScrollPane scrollTextoRegraCriarIndicePersonagem = new JScrollPane(textoRegraCriarIndicePersonagem);
        scrollTextoRegraCriarIndicePersonagem.setFocusable(true);
        //Posicionamento
        labelIndiceInical.setBounds(300,2,800,100);
        scrollTextoRegraCriarIndicePersonagem.setBounds(10,100,1160,300);


        //Adicionar a tela
        add(labelIndiceInical);
        add(scrollTextoRegraCriarIndicePersonagem);



    }
}
