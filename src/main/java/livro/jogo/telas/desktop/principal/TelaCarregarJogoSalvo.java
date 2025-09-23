package livro.jogo.telas.desktop.principal;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.ManipulaArqJogoSalvo;
import livro.jogo.utils.Util;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TelaCarregarJogoSalvo extends JDialog {
    private JLabelOpcoesTelaSecao botaoCarregar;
    private JLabelOpcoesTelaSecao botaoApagar;
    private JLabelOpcoesTelaSecao botaoSair;
    private final AcaoBotoes acao = new AcaoBotoes();
    private String nomeArquivo;
    private JList<String> jListNomesArqs;

    public TelaCarregarJogoSalvo(int largura, int altura) {
        setSize(largura+160,altura+30);
        setLayout(null);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setLocationRelativeTo(null);
        //setResizable(false);
        setModal(true);



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
        labelCarregar.setBounds(73, 252, 80,60);
        labelCarregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelCarregar.setFont(new Font(Font.SERIF,Font.BOLD,14));
        labelCarregar.setVerticalTextPosition(SwingConstants.CENTER);
        labelCarregar.setForeground(new Color(139,0,0));

        botaoCarregar = new JLabelOpcoesTelaSecao(null,
                100, 50, ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoCarregar.setBounds(50,260,100,50);
        botaoCarregar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoCarregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoCarregar.addMouseListener(acao);

        JLabel labelApagar = new JLabel("Apagar");
        labelApagar.setBounds(178, 252, 80,60);
        labelApagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelApagar.setFont(new Font(Font.SERIF,Font.BOLD,14));
        labelApagar.setVerticalTextPosition(SwingConstants.CENTER);
        labelApagar.setForeground(new Color(139,0,0));

        botaoApagar = new JLabelOpcoesTelaSecao(null,
                100, 50, ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoApagar.setBounds(150,260,100,50);
        botaoApagar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoApagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoApagar.addMouseListener(acao);

        JLabel labelSair = new JLabel("Sair");
        labelSair.setBounds(290, 252, 80,60);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelSair.setVerticalTextPosition(SwingConstants.CENTER);
        labelSair.setForeground(new Color(139,0,0));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                100, 50, ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(250,260,100,50);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSair.addMouseListener(acao);

        add(labelSair);
        add(botaoSair);
        add(labelCarregar);
        add(botaoCarregar);
        add(labelApagar);
        add(botaoApagar);
    }

    private void carregaListaDeArquivos() {
        ArrayList<String> arrayList = Util.listarJogosSalvos();
        String[] listaNomesArquivos = arrayList.toArray(new String[0]);

        if (jListNomesArqs == null) {
            jListNomesArqs = new JList<String>(listaNomesArquivos);
            jListNomesArqs.setVisibleRowCount(5);
            jListNomesArqs.setBackground(new Color(210, 180, 140));
            JScrollPane scroll = new JScrollPane(jListNomesArqs);
            scroll.setBounds(78, 45, 240, 210);
            scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    //this.thumbColor = new Color(210,105,30);
                    //this.trackColor = new Color(210,180,140);
                }
                @Override
                protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                    g.setColor(new Color(245,222,179));
                    g.fillRect(r.x, r.y, r.width, r.height);
                }
                @Override
                protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
                    g.setColor(new Color(210,180,140));
                    g.fillRect(r.x, r.y, r.width, r.height);
                }


                @Override
                protected JButton createDecreaseButton(int orientation) {
                    //JButton button = new JButton();
                    // button.setBackground(new Color(160,82,45)); // cor da ponta superior
                    // button.setBorder(BorderFactory.createEmptyBorder());
                    return createInvisibleButton();
                }


                private JButton createInvisibleButton() {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(0, 0));
                    button.setMinimumSize(new Dimension(0, 0));
                    button.setMaximumSize(new Dimension(0, 0));
                    button.setVisible(false);
                    return button;
                }


                @Override
                protected JButton createIncreaseButton(int orientation) {
//                JButton button = new JButton();
//                button.setBackground(new Color(160,82,45)); // cor da ponta inferior
//                button.setBorder(BorderFactory.createEmptyBorder());
//                return button;
                    return createInvisibleButton();
                }

            });
            add(scroll);

            jListNomesArqs.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    nomeArquivo = jListNomesArqs.getSelectedValue();
                }
            });
        } else {
            // Atualiza os dados da lista
            jListNomesArqs.setListData(listaNomesArquivos);
        }


    }

    private class AcaoBotoes implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == botaoApagar){

                if (nomeArquivo == null){
                    CarregarTelas.telaMensagem("Escolha um arquivo para apagar.");
                    return;
                }
                try {
                    CarregarTelas.telaMensagem("Jogo apagado!");
                    new ManipulaArqJogoSalvo().apagaJogo(nomeArquivo);
                    nomeArquivo = "";
                    carregaListaDeArquivos();
                }
                catch (Exception exception){
                    CarregarTelas.telaMensagem("Erro ao apagar jogo salvo.");
                }
            }

            if (e.getSource() == botaoCarregar){

                if (nomeArquivo == null){
                    CarregarTelas.telaMensagem("Escolha um arquivo para carregar.");
                    return;
                }

                setVisible(false);
                try {
                    CarregarTelas.telaMensagem("Jogo carregado!");
                    TelaSecoesBasica.setJogoFoiCarregado(true);
                    new ManipulaArqJogoSalvo().carregaJogo(nomeArquivo);
                    nomeArquivo = "";
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
            if (e.getSource() == botaoApagar){
                botaoApagar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoApagar.getWidth(), botaoApagar.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoCarregar){
                botaoCarregar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoCarregar.getWidth(), botaoCarregar.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == botaoSair){
                botaoSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
                repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == botaoApagar){
                botaoApagar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoApagar.getWidth(), botaoApagar.getHeight()).getImageIcon());
                //repaint();
            }

            if (e.getSource() == botaoCarregar){
                botaoCarregar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoCarregar.getWidth(), botaoCarregar.getHeight()).getImageIcon());
                //repaint();
            }

            if (e.getSource() == botaoSair){
                botaoSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
                //repaint();
            }
        }
    }
}
