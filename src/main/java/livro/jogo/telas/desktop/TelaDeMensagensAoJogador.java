package livro.jogo.telas.desktop;

import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaDeMensagensAoJogador extends JDialog {
    private Personagem personagem;
    private String texto;
    private JLabelOpcoesTelaSecao botaoSair;
    private JLabelOpcoesTelaSecao botaoConfirmarESair;
    private TelaRegrasOpcoesAcaoDosBotoes acao = new TelaRegrasOpcoesAcaoDosBotoes();
    private JLabelOpcoesTelaSecao botaoSim;
    private JLabelOpcoesTelaSecao botaoNao;
    //private boolean resposta = false; //Recebe o resultado quando a tela for de questionamento
    private TelaSecoesBasica telaQueChamouEsta; //Serve para, por exemplo, fechar a tela quando confirmado o desejo de sair
    private TelaBasica telaBasicaQueChamouEsta; //Serve para, por exemplo, fechar a tela quando confirmado o desejo de sair


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
        //this.fecharTela = fecharTela; //Vai informar a tela "telaQueChamouEsta" o resultado da escolha
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
        labelConfirmarESair.setBounds(308,445,100,50);
        labelConfirmarESair.setForeground(new Color(139,0,0));
        labelConfirmarESair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelConfirmarESair.setHorizontalAlignment(SwingConstants.CENTER);
        labelConfirmarESair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoConfirmarESair = new JLabelOpcoesTelaSecao(null,
                150, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoConfirmarESair.setBounds(246,430,220,90);
        botaoConfirmarESair.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoConfirmarESair.addMouseListener(acao);

        JLabel labelSair = new JLabel("Não");
        labelSair.setBounds(508,445,100,50);
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                150, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(446,430,220,90);
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

                dispose();
            }

            //Confirma o fechamento da tela
//            if ( (e.getSource() == botaoConfirmarESair) && (fecharTela)){
//                telaQueChamouEsta.dispose();
//                dispose();
//            }

            //Caso não se
            if ( e.getSource() == botaoConfirmarESair){
                if (telaQueChamouEsta != null)
                    telaQueChamouEsta.setRespostaTelaMensagem(true);

                if (telaBasicaQueChamouEsta != null)
                    telaBasicaQueChamouEsta.setRespostaTelaMensagem(true);
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
    } //FIM AcaoDosBotoes implements ActionListener

}