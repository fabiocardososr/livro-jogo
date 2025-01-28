package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao1 extends TelaSecoesBasica {
    private JLabelOpcoesTelaSecao botao1;


    public TelaSecao1(Secao secao, JFrame referenciaTelaPrincipal) {
        super(secao, referenciaTelaPrincipal);
        // this.secao = secao;


        textoHistoria.setText( secao.getTexto() );

        //para posicionar a barra de rolagem no início.
        textoHistoria.setCaretPosition(0);

    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
    }

    private void opcao1(Secao secao){
        JLabel label = new JLabel("1");
        label.setForeground(Color.WHITE);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SERIF,Font.BOLD,30));
        label.setBounds(116,592, 50,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        botao1 = new JLabelOpcoesTelaSecao("",40,50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        botao1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao1.setBounds(120,600,40,50);
        botao1.setToolTipText("Clique para escolher esta Opção.");
        botao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botao1){
                    JOptionPane.showMessageDialog(null, "teste");
                }
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
        //botaoOpcao1.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Texto da opção
        JLabel lbTexto = new JLabel(secao.getProximasSecoes().get(1).getTextoOpcao());
        lbTexto.setBounds(170,587,450,60);
        lbTexto.setFont(new Font(Font.SERIF,Font.BOLD,25));
        lbTexto.setForeground(new Color(139,0,0));

        add(lbTexto);
        add(label);
        add(botao1);
    }


}
