package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_333 extends TelaSecoesBasica {

    public TelaSecao_333(Secao secao) {
        super(secao);

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/grito_terror.mp3", null);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaBotao();
    }

    @Override
    protected void acaoBotoes(Secao secao) {

    }

    private void sair(){
        util.pararAudioMp3();
        if (referenciaTelaPrincipal != null)
            referenciaTelaPrincipal.setVisible(true);

        dispose();
    }

    private void carregaBotao() {
        //Texto
        JLabel texto = new JLabel("<html><center>Seu novo reino te espera.<br>"+
                "Rei dos Guerreiros-Clones!</center></html>");
        texto.setBounds(225,495,360,60);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,20));
        texto.setForeground(new Color(128,0,0));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sair();
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
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",600, 180,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(100,440,600,180);
        //botaoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.RED));

        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sair();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setIcon(Util.dimensionarImagem(botao.getWidth(),
                        botao.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setIcon(Util.dimensionarImagem(botao.getWidth(),
                        botao.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(texto);
        add(botao);
    }
}
