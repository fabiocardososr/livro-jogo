package livro.jogo.telas.desktop.principal;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaDeMensagensAoJogador extends JDialog {
    private String texto;
    private JLabelOpcoesTelaSecao botaoSair;
    private JLabelOpcoesTelaSecao botaoConfirmarESair;
    private TelaRegrasOpcoesAcaoDosBotoes acao = new TelaRegrasOpcoesAcaoDosBotoes();
    private TelaSecoesBasica telaQueChamouEsta; //Serve para, por exemplo, fechar a tela quando confirmado o desejo de sair
    private TelaBasica telaBasicaQueChamouEsta; //Serve para, por exemplo, fechar a tela quando confirmado o desejo de sair
    private TelaBatalha telaDialog; //Serve para, por exemplo, fechar a tela quando confirmado o desejo de sair


    public TelaDeMensagensAoJogador( String texto) {
        this.texto = texto;
        int largura = 900;
        int altura = 700;

        setSize(largura,altura);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setModal(true);
        setBackground(new Color(0,0,0,0));
        carregarBotaoOk();
        carregarfundo(largura, altura);
        carregarTexto();
    }

    //Usado para tela de confirmação PARA FECHAR TELA
    public TelaDeMensagensAoJogador(String texto, TelaSecoesBasica dialog) {
        this.texto = texto;
        this.telaQueChamouEsta = dialog;
        int largura = 900;
        int altura = 700;

        setSize(largura,altura);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setModal(true);
        setBackground(new Color(0,0,0,0));

        //Carrega botões de confirmação
        carregarBotoesConfirmacao();

        carregarfundo(largura, altura);
        carregarTexto();
    }

    public TelaDeMensagensAoJogador(String texto, TelaBatalha dialog) {
        this.texto = texto;
        this.telaDialog = dialog;
        int largura = 900;
        int altura = 700;

        setSize(largura,altura);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setModal(true);
        setBackground(new Color(0,0,0,0));

        //Carrega botões de confirmação
        carregarBotoesConfirmacao();

        carregarfundo(largura, altura);
        carregarTexto();
    }

    public void carregarBotaoOk() {

        JLabel labelSair = new JLabel("OK");
        labelSair.setBounds(406,485,100,50);
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(346,470,220,90);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoSair.addMouseListener(acao);

        add(labelSair);
        add(botaoSair);
    }

    public void carregarBotoesConfirmacao() {

        JLabel labelConfirmarESair = new JLabel("Sim");
        labelConfirmarESair.setBounds(326,563,100,50);
        labelConfirmarESair.setForeground(new Color(139,0,0));
        labelConfirmarESair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelConfirmarESair.setHorizontalAlignment(SwingConstants.CENTER);
        labelConfirmarESair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoConfirmarESair = new JLabelOpcoesTelaSecao(null,
                150, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoConfirmarESair.setBounds(300,550,150,90);
        botaoConfirmarESair.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoConfirmarESair.addMouseListener(acao);

        JLabel labelSair = new JLabel("Não");
        labelSair.setBounds(491,563,100,50);
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                150, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(465,550,150,90);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoSair.addMouseListener(acao);



        add(labelSair);
        add(botaoSair);
        add(labelConfirmarESair);
        add(botaoConfirmarESair);


    }

    private void carregarTexto() {

        //Texto
        JTextPane textoHistoria = new JTextPane();
        textoHistoria.setBackground(new Color(210,180,140));
        textoHistoria.setFocusable(false);

        //Use esta linha como exemplo para aumentar ou diminuir tamanho da font
        textoHistoria.setFont(new Font(Font.DIALOG,Font.BOLD,20));

        //Formatando o texto
        StyledDocument textoLivroStyle = textoHistoria.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_CENTER);
        StyleConstants.setForeground(configTexto,Color.WHITE);
        textoLivroStyle.setParagraphAttributes(0, textoLivroStyle.getLength(), configTexto, false);
        textoHistoria.setEditable(false);
        textoHistoria.setFocusable(false);
        textoHistoria.setBackground(new Color(0,0,0,0));
        textoHistoria.setBounds(242,280,430,180);
        textoHistoria.setText(this.texto);
        //textoHistoria.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JScrollPane scrollTextoHistoria = new JScrollPane(textoHistoria);
        scrollTextoHistoria.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        scrollTextoHistoria.setFocusable(true);
        scrollTextoHistoria.setBorder(null);


        add(textoHistoria);
    }

    private void carregarfundo(int largura, int altura) {

        //Fundo
        JLabelOpcoesTelaSecao labelFundo = new JLabelOpcoesTelaSecao("",largura,altura,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_5);
        labelFundo.setBounds(0,0,largura,altura);
        //labelFundo.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        labelFundo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabelOpcoesTelaSecao labelFaixaSuperior = new JLabelOpcoesTelaSecao("",120,90,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_10);
        labelFaixaSuperior.setBounds(397,170,120,90);
        //labelFaixaSuperior.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        labelFaixaSuperior.setHorizontalAlignment(SwingConstants.CENTER);

        add(labelFaixaSuperior);
        add(labelFundo);
    }

    private class TelaRegrasOpcoesAcaoDosBotoes implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == botaoSair){
                if (telaQueChamouEsta != null)
                    telaQueChamouEsta.setRespostaTelaMensagem(false);

                if (telaBasicaQueChamouEsta != null)
                    telaBasicaQueChamouEsta.setRespostaTelaMensagem(false);

                if (telaDialog != null)
                    telaDialog.setRespostaTelaConfirmacao(false);

                dispose();
            }

            //Caso não se
            if ( e.getSource() == botaoConfirmarESair){
                if (telaQueChamouEsta != null)
                    telaQueChamouEsta.setRespostaTelaMensagem(true);

                if (telaBasicaQueChamouEsta != null)
                    telaBasicaQueChamouEsta.setRespostaTelaMensagem(true);

                if (telaDialog != null)
                    telaDialog.setRespostaTelaConfirmacao(true);

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
            if (e.getSource() == botaoConfirmarESair) {
                botaoConfirmarESair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoConfirmarESair.getWidth(), botaoConfirmarESair.getHeight()).getImageIcon());
            }

            if (e.getSource() == botaoSair) {
                botaoSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == botaoConfirmarESair) {
                botaoConfirmarESair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoConfirmarESair.getWidth(), botaoConfirmarESair.getHeight()).getImageIcon());
            }

            if (e.getSource() == botaoSair) {
                botaoSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
            }
        }
    } //FIM AcaoDosBotoes implements ActionListener

}