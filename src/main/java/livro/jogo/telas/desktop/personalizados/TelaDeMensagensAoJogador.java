package livro.jogo.telas.desktop.personalizados;

import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;

import javax.swing.*;
import java.awt.*;

public class TelaDeMensagensAoJogador extends JDialog {
    private Personagem personagem;
    private String texto;

    public TelaDeMensagensAoJogador(int largura, int altura, Personagem personagem, String texto) {
        this.personagem = personagem;
        this.texto = texto;

        setSize(largura,altura);
        //Caso necessite alterar layout da tela para uma especifica. Necessário o Container
        //setBackground(new Color(210,180,140));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setModal(true);
        setBackground(new Color(0,0,0,0));
    }

    public TelaDeMensagensAoJogador(int largura, int altura, String texto) {
        this.texto = texto;

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
    }

    private void carregarfundo(int largura, int altura) {

        //Fundo
        JLabelOpcoesTelaSecao labelFundo = new JLabelOpcoesTelaSecao("",largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_5);
        labelFundo.setBounds(0,0,largura,altura);
        //labelFundo.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        labelFundo.setHorizontalAlignment(SwingConstants.CENTER);

        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                180, 200, ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        //labelFaixaSuperiorEsquerda.setBounds(-80,-80,180,200);
        labelFaixaSuperiorEsquerda.setBounds(-50,-50,180,200);
        labelFaixaSuperiorEsquerda.setHorizontalAlignment(SwingConstants.CENTER);
        labelFaixaSuperiorEsquerda.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(1310,-108,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(1325,650,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-110,675,300,250);

        //add(labelFaixaInferiorEsquerda);
        //add(labelFaixaInferiorDireita);
        //add(labelFaixaSuperiorDireita);
        //add(labelFaixaSuperiorEsquerda);
        add(labelFundo);
    }
}
