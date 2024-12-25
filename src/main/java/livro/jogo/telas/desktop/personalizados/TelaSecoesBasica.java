package livro.jogo.telas.desktop.personalizados;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Secao;
import livro.jogo.utils.ManipularDadosLivro;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TelaSecoesBasica extends TelaBasica{
    private final Secao secao;
    private String enderecoImagem = ManipularDadosLivro.getLivro().getImagemComplementar();

    public TelaSecoesBasica(Secao secao) {
        super(1500,800); //Tamanho comum para todas as telas de seções
        this.secao = secao;

        if (secao.getEnderecoImagem() != null)
            this.enderecoImagem = secao.getEnderecoImagem();

        setTitle("Seção - "+secao.getCodSecaoLivro());
        setType(Window.Type.UTILITY);

        //Carregar campo que receberá o texto da história
        carregarTextoHistoriaEImagem();
        carregarPainelDireito();
        carregaPainelInferior();
        carregarPainelComImagemDoMapa();
    }

    private void carregarPainelComImagemDoMapa() {
        ImagePanel imgPainelMapa = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MAPA_DA_FLORESTA);
        imgPainelMapa.setBounds(887,455,320,295);
        add(imgPainelMapa);
    }

    private void carregaPainelInferior() {
        ImagePanel imgPainelInferior = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_ABERTO);
        imgPainelInferior.setBounds(5,465,900,295);
        add(imgPainelInferior);
    }

    private void carregarPainelDireito() {
        ImagePanel imgPainelDireito = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FAIXA);
        imgPainelDireito.setBounds(1200,2,280,770);
        add(imgPainelDireito);
    }

    private void carregarTextoHistoriaEImagem() {

        //Texto do livro
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
        JScrollPane scrollTextoHistoria = new JScrollPane(textoHistoria);
        scrollTextoHistoria.setFocusable(true);


        //Carregando texto no componente
        textoHistoria.setText( secao.getTexto() );

        //Carrega imagem
        ImagePanel imagemSecao = new ImagePanel(this.enderecoImagem);

        //posicionamento na tela
        scrollTextoHistoria.setBounds(15, 15, 870, 450);
        imagemSecao.setBounds(887, 15, 320, 450);


        //Adicionando a tela
        add(imagemSecao);
        add(scrollTextoHistoria);
    }


}
