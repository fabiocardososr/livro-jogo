package livro.jogo.telas.desktop.principal;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.utils.CarregarJogoSalvo;
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
        setSize(largura+160,altura+50);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        setUndecorated(true);
        setBackground(new Color(0,0,0,0));

        carregaListaDeArquivos();
        carregarBotoes();
        carregarFundo(largura, altura);
    }

    private void carregarFundo(int largura, int altura) {
        JLabelOpcoesTelaSecao labelMolduraTextoTelaPrincipal = new JLabelOpcoesTelaSecao(null,largura+150,altura,
                ImagensDoLivroFlorestaDaDestruicao.FUNDO_BOLSA);

        labelMolduraTextoTelaPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
        labelMolduraTextoTelaPrincipal.setBounds(0,0, largura+150, altura);
        add(labelMolduraTextoTelaPrincipal);
    }

    private void carregarBotoes() {
        JLabel labelCarregar = new JLabel("Carregar");
        labelCarregar.setBounds(103, 252, 80,60);
        labelCarregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelCarregar.setFont(new Font(Font.SERIF,Font.BOLD,14));
        labelCarregar.setVerticalTextPosition(SwingConstants.CENTER);
        labelCarregar.setForeground(new Color(139,0,0));

        botaoCarregar = new JLabelOpcoesTelaSecao(null,
                100, 50, ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoCarregar.setBounds(80,260,100,50);
        botaoCarregar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoCarregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoCarregar.addMouseListener(acao);

        JLabel labelSair = new JLabel("Sair");
        labelSair.setBounds(270, 252, 80,60);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelSair.setVerticalTextPosition(SwingConstants.CENTER);
        labelSair.setForeground(new Color(139,0,0));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                100, 50, ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(230,260,100,50);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        jListNomesArqs.setBackground(new Color(210,180,140));
        JScrollPane scroll = new JScrollPane(jListNomesArqs);
        scroll.setBounds(78,45,240,210);

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
