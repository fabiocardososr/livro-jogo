package livro.jogo.telas.desktop;

import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.utils.Util;

import javax.swing.*;
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
        JLabel labelSalvarESair = new JLabel("Salvar e Sair");
        labelSalvarESair.setForeground(new Color(139,0,0));
        labelSalvarESair.setBounds(274,488,190,60);
        labelSalvarESair.setFont(new Font(Font.DIALOG,Font.BOLD,16));
        labelSalvarESair.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel labelSair = new JLabel("Sair");
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setBounds(518,486,190,60);
        labelSair.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));

        labelBotaoSalvarESair = new JLabelOpcoesTelaSecao("Salvar e Sair",190,110,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoSalvarESair.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelBotaoSalvarESair.addMouseListener(acao);
        labelBotaoSalvarESair.setBounds(225,465,190,110);
        labelBotaoSalvarESair.setCursor(new Cursor(Cursor.HAND_CURSOR));


        labelBotaoSair = new JLabelOpcoesTelaSecao("Salvar e Sair",190,110,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoSair.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelBotaoSair.addMouseListener(acao);
        labelBotaoSair.setBounds(438,465,190,110);

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
        scrollTexto.setBounds(160,115,533,368);

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

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


}
