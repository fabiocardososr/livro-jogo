package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_2 extends TelaSecoesBasica {
    public TelaSecao_2(Secao secao) {
        super(secao, CarregarTelas.getReferenciaTelaPrincipal());
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaBotao();
    }

    private void sair(){
        util.pararAudioMp3();
        if (referenciaTelaPrincipal != null)
            referenciaTelaPrincipal.setVisible(true);

        dispose();
    }

    private void carregaBotao() {
        //Texto
        JLabel textoComecarJornada = new JLabel("<html><center>Você foi devorado.<br>"+
                "Sua aventura termina aqui!</center></html>");
        textoComecarJornada.setBounds(305,645,360,60);
        textoComecarJornada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoComecarJornada.setHorizontalAlignment(SwingConstants.CENTER);
        textoComecarJornada.setFont(new Font(Font.SERIF,Font.BOLD,25));
        textoComecarJornada.setForeground(new Color(128,0,0));
        textoComecarJornada.setToolTipText("Clique aqui para começar sua jornada.");
        textoComecarJornada.addMouseListener(new MouseListener() {
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
        //textoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",800, 220,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(82,560,800,230);
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        add(textoComecarJornada);
        add(botao);
    }


}
