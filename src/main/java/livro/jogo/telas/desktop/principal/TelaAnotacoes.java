package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaAnotacoes extends JDialog {
    private Personagem personagem;
    private JTextPane anotacao;
    private JLabelOpcoesTelaSecao labelBotaoSalvarESair;
    private JLabelOpcoesTelaSecao labelBotaoSair;
    private final TelaAnotacoesBotoes acao = new TelaAnotacoesBotoes();

    public TelaAnotacoes(Personagem personagem) {
        setSize(850,600);
        this.personagem = personagem;
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setModal(true);
        setLayout(null);
        setBackground(new Color(0,0,0,0));

        carregarImagemTitulo();
        carregarBotoes();
        carregarCampoTexto();
        carregarFundo();

    }

    private void carregarBotoes() {
        JLabel labelSalvarESair = new JLabel("Salvar");
        labelSalvarESair.setForeground(new Color(139,0,0));
        labelSalvarESair.setBounds(295,510,50,30);
        labelSalvarESair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSalvarESair.setFont(new Font(Font.DIALOG,Font.BOLD,16));
        labelSalvarESair.setCursor(new Cursor(Cursor.HAND_CURSOR));


        labelBotaoSalvarESair = new JLabelOpcoesTelaSecao("Salvar",180,90,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoSalvarESair.setBounds(230,485,180,90);
        labelBotaoSalvarESair.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelBotaoSalvarESair.addMouseListener(acao);
        labelBotaoSalvarESair.setCursor(new Cursor(Cursor.HAND_CURSOR));


        JLabel labelSair = new JLabel("Sair");
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setBounds(515,510,50,30);
        labelSair.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        labelBotaoSair = new JLabelOpcoesTelaSecao("Sair",180,90,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoSair.setBounds(442,485,180,90);
        labelBotaoSair.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelBotaoSair.addMouseListener(acao);


        add(labelSair);
        add(labelSalvarESair);
        add(labelBotaoSair);
        add(labelBotaoSalvarESair);
    }

    private void carregarImagemTitulo() {
        JLabel label = new JLabel("Anotações");
        label.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        label.setForeground(new Color(139,0,0));
        label.setBounds(300,45,250,60);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        JLabelOpcoesTelaSecao labelImgAnotacao = new JLabelOpcoesTelaSecao("",250,220,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        labelImgAnotacao.setBounds(275,30,300,100);
        //labelImgAnotacao.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelImgAnotacao.setHorizontalAlignment(SwingConstants.CENTER);
        labelImgAnotacao.setCursor(null);

        add(label);
        add(labelImgAnotacao);
    }

    private void carregarCampoTexto() {
        anotacao = new JTextPane();
        anotacao.setBackground(new Color(210,180,140));
        anotacao.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        anotacao.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        anotacao.setFocusable(true);
        StyledDocument textoLivroStyle = anotacao.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setForeground(configTexto,Color.BLACK);
        textoLivroStyle.setParagraphAttributes(0, textoLivroStyle.getLength(), configTexto, false);
        JScrollPane scrollTexto = new JScrollPane(anotacao);
        scrollTexto.setFocusable(true);
        scrollTexto.setBorder(null);
        scrollTexto.setBounds(156,115,537,368);
        scrollTexto.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        //Carregar anotações do personagem
        anotacao.setText(personagem.getAnotacoes());

        add(scrollTexto);
    }

    private void carregarFundo() {
        JLabelOpcoesTelaSecao labelFundo = new JLabelOpcoesTelaSecao("",850,750,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_ANOTACOES);
        labelFundo.setBounds(0,0,850,600);
        //labelFundo.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelFundo.setHorizontalAlignment(SwingConstants.CENTER);
        labelFundo.setCursor(null);
        add(labelFundo);
    }


    private class TelaAnotacoesBotoes implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == labelBotaoSalvarESair){
                personagem.setAnotacoes(anotacao.getText());
                dispose();
            }

            if (e.getSource() == labelBotaoSair){
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
            if (e.getSource() == labelBotaoSalvarESair) {
                labelBotaoSalvarESair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        labelBotaoSalvarESair.getWidth(), labelBotaoSalvarESair.getHeight()).getImageIcon());
            }

            if (e.getSource() == labelBotaoSair) {
                labelBotaoSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        labelBotaoSair.getWidth(), labelBotaoSair.getHeight()).getImageIcon());
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == labelBotaoSalvarESair) {
                labelBotaoSalvarESair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        labelBotaoSalvarESair.getWidth(), labelBotaoSalvarESair.getHeight()).getImageIcon());
            }

            if (e.getSource() == labelBotaoSair) {
                labelBotaoSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        labelBotaoSair.getWidth(), labelBotaoSair.getHeight()).getImageIcon());
            }
        }
    }


}
