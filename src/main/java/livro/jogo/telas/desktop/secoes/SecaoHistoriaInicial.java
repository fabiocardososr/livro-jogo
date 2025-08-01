package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SecaoHistoriaInicial extends TelaSecoesBasica {

    public SecaoHistoriaInicial(Secao secao) {
        super(secao);
        carregarComponentesEspecificos(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaBotaoOpcao();
    }

    @Override
    protected void acaoBotoes(Secao secao) {

    }

    private void carregaBotaoOpcao() {

        //Texto
        JLabel textoComecarJornada = new JLabel("<html><center>Pegue as moedas e o mapa.<br>"+
                "Comece sua jornada!</center></html>");
        textoComecarJornada.setBounds(295,495,230,60);
        textoComecarJornada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoComecarJornada.setHorizontalAlignment(SwingConstants.CENTER);
        textoComecarJornada.setFont(new Font(Font.SERIF,Font.BOLD,18));
        textoComecarJornada.setForeground(new Color(128,0,0));
        textoComecarJornada.setToolTipText("Clique aqui para começar sua jornada.");
        textoComecarJornada.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DadosLivroCarregado.getPersonagem().setQuantidadeOuro(30);
                labelOuro.setText("Ouro: 30");
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
        //textoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão
        JLabelOpcoesTelaSecao botaoComecarJornada = new JLabelOpcoesTelaSecao("",650, 170,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoComecarJornada.setBounds(82,440,650,170);
        botaoComecarJornada.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DadosLivroCarregado.getPersonagem().setQuantidadeOuro(30);
                labelOuro.setText("Ouro: 30");
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
                botaoComecarJornada.setIcon(Util.dimensionarImagem(botaoComecarJornada.getWidth(),
                        botaoComecarJornada.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoComecarJornada.setIcon(Util.dimensionarImagem(botaoComecarJornada.getWidth(),
                        botaoComecarJornada.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoComecarJornada);
        add(botaoComecarJornada);
    }
}
