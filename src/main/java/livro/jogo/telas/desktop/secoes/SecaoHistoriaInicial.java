package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SecaoHistoriaInicial extends TelaSecoesBasica {

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
        JLabel textoComecarJornada = new JLabel("<html><center>Pegue as 30 moedas cedidas pelo Anão.\n"+
                "Comece sua jornada!</center></html>");
        textoComecarJornada.setBounds(310,650,360,60);
        textoComecarJornada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoComecarJornada.setHorizontalAlignment(SwingConstants.CENTER);
        textoComecarJornada.setFont(new Font(Font.SERIF,Font.BOLD,20));
        textoComecarJornada.setForeground(new Color(139,0,0));
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
        JLabelOpcoesTelaSecao botaoComecarJornada = new JLabelOpcoesTelaSecao("",800, 220,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoComecarJornada.setBounds(82,560,800,220);
        //botaoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.RED));

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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        add(textoComecarJornada);
        add(botaoComecarJornada);
    }
}
