package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SecaoHistoriaInicial extends TelaSecoesBasica {

    //Componentes
    private JLabel texto;
    private JLabelOpcoesTelaSecao botao;

    public SecaoHistoriaInicial(Secao secao, JFrame referenciaTelaPrincipal) {
        super(secao, referenciaTelaPrincipal);
        carregarComponentesEspecificos(secao);


    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaBotaoOpcao();
    }

    private void carregaBotaoOpcao() {
        //Texto
        texto = new JLabel("Começar a jornada!");
        texto.setBounds(315,632,250,50);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,25));
        texto.setForeground(new Color(139,0,0));

        //Botão
        botao = new JLabelOpcoesTelaSecao("",500, 150,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(190,580,500,150);
        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirProximaSecao(1);
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
        });

        add(texto);
        add(botao);
    }
}
