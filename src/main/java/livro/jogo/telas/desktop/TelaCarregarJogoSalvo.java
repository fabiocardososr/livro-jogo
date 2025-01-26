package livro.jogo.telas.desktop;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.util.CarregarJogoSalvo;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.utils.Util;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TelaCarregarJogoSalvo extends JDialog {
    private JLabelOpcoesTelaSecao botaoCarregar;
    private JLabelOpcoesTelaSecao botaoSair;
    private final AcaoBotoes acao = new AcaoBotoes();
    private String nomeArquivo;

    public TelaCarregarJogoSalvo(int largura, int altura) {
        setSize(largura,altura);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        //setUndecorated(true);
        //setBackground(new Color(0,0,0,0));

        carregaListaDeArquivos();
        carregarBotoes();
    }

    private void carregarBotoes() {
        JLabel labelCarregar = new JLabel("Carregar");
        labelCarregar.setBounds(30, 162, 80,60);
        labelCarregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelCarregar.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelCarregar.setVerticalTextPosition(SwingConstants.CENTER);
        labelCarregar.setForeground(new Color(139,0,0));

        JLabel labelSair = new JLabel("Sair");
        labelSair.setBounds(158, 162, 80,60);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelSair.setVerticalTextPosition(SwingConstants.CENTER);
        labelSair.setForeground(new Color(139,0,0));

        botaoCarregar = new JLabelOpcoesTelaSecao(null,
                100, 50, ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoCarregar.setBounds(10,170,100,50);
        botaoCarregar.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoCarregar.addMouseListener(acao);

        botaoSair = new JLabelOpcoesTelaSecao(null,
                100, 50, ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(120,170,100,50);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        botaoSair.addMouseListener(acao);

        add(labelSair);
        add(botaoSair);
        add(labelCarregar);
        add(botaoCarregar);
    }

    private void carregaListaDeArquivos() {
        ArrayList<String> arrayList = Util.listarJogosSalvos();
        String[] listaNomesArquivos = arrayList.toArray(new String[0]);

        JList<String> jListNomesArqs = new JList<String>(listaNomesArquivos);
        jListNomesArqs.setVisibleRowCount(5);
        JScrollPane scroll = new JScrollPane(jListNomesArqs);
        scroll.setBounds(10,10,223,150);
        add(scroll);

        jListNomesArqs.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                nomeArquivo = jListNomesArqs.getSelectedValue();
            }
        });
    }

    private class AcaoBotoes implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == botaoCarregar){

                if (nomeArquivo == null){
                    CarregarTelas.telaMensagem("Escolha um arquivo para carregar.");
                    return;
                }

                setVisible(false);
                try {
                    CarregarTelas.telaMensagem("Jogo carregado!");
                    new CarregarJogoSalvo(nomeArquivo);
                }
                catch (Exception exception){
                    CarregarTelas.telaMensagem("Erro ao carregar jogo salvo.");
                }
                dispose();
            }

            if (e.getSource() == botaoSair){
                dispose();
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
    }
}
