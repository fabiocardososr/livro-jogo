package livro.jogo.telas.desktop.personalizados;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BotaoFaixaOpcoes{
    private int posicaoX;
    private int posicaoY;
    private int largura;
    private int altura;

    public BotaoFaixaOpcoes( int posicaoX, int posicaoY, int largura, int altura) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.largura = largura;
        this.altura = altura;
    }

    public JLabelOpcoesTelaSecao criarBotao(){
        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",340, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        //botao.setBounds(450,560,340,80);
        botao.setBounds(posicaoX, posicaoY,largura,altura);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return botao;
    }
}
