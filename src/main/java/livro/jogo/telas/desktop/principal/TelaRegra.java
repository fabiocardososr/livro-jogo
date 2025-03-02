package livro.jogo.telas.desktop.principal;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaRegra extends JDialog {
    private final String titulo;
    private final String texto;
    private int tamanhoTexto = 20; //Default(20)Aumentar ou diminuir texto
    private JTextPane textoRegra;
    private JLabelOpcoesTelaSecao labelAumentaTexto;
    private JLabelOpcoesTelaSecao labelDiminuiTexto;

    public TelaRegra(int largura, int altura, String titulo, String texto) {
        setSize(largura,altura);
        this.titulo = titulo;
        this.texto = texto;

        setModal(true);
        setUndecorated(true);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(210,180,140));
        getContentPane().setCursor(null);
        carregarCompenentesTela();
    }

    private void carregarCompenentesTela(){
        TelaAcaoDosLabels acao = new TelaAcaoDosLabels();

        //Texto do título
        JLabel labelDescricaoTitulo = new JLabel(titulo);
        labelDescricaoTitulo.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelDescricaoTitulo.setForeground(new Color(139,0,0));
        labelDescricaoTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        //Imagem que compõe o título
        JLabelOpcoesTelaSecao labelImgTitulo = new JLabelOpcoesTelaSecao(null,
                300, 210,ImagensDoLivroFlorestaDaDestruicao.FAIXA_2);
        labelImgTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelImgTitulo.setCursor(null);
        //labelImgTitulo.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        JLabelOpcoesTelaSecao painelTexto = new JLabelOpcoesTelaSecao("",1050,650,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_2);
        painelTexto.setBounds(50,100,1100,650);
        painelTexto.setHorizontalAlignment(SwingConstants.CENTER);
        painelTexto.setCursor(null);
        //painelTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Criar campo que receberá o texto
        textoRegra = new JTextPane();
        textoRegra.setBackground(new Color(210,180,140));
        textoRegra.setFont(new Font(Font.SERIF,Font.BOLD, tamanhoTexto));
        StyledDocument textoCapaLivroStyle = textoRegra.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setForeground(configTexto,new Color(139,0,0));
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoRegra.setEditable(false);
        textoRegra.setFocusable(false);
        textoRegra.setText(texto);
        textoRegra.setCaretPosition(0); //para posicionar a a barra de rolagem no início.
        JScrollPane scrollTextoRegra = new JScrollPane(textoRegra);
        scrollTextoRegra.setFocusable(true);

        //Aumenta texto
        labelAumentaTexto = new JLabelOpcoesTelaSecao(null, 40,35,
                ImagensDoLivroFlorestaDaDestruicao.SINAL_SOMA);
        labelAumentaTexto.setHorizontalAlignment(SwingConstants.CENTER);
        labelAumentaTexto.setToolTipText("Aumentar texto");
        labelAumentaTexto.addMouseListener(acao);
        //labelAumentaTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Diminuir texto
        labelDiminuiTexto = new JLabelOpcoesTelaSecao(null, 30,30,
                ImagensDoLivroFlorestaDaDestruicao.SINAL_MENOS);
        labelDiminuiTexto.setHorizontalAlignment(SwingConstants.CENTER);
        labelDiminuiTexto.setToolTipText("Diminuir texto");
        labelDiminuiTexto.addMouseListener(acao);
        //labelDiminuiTexto.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Botão sair
        JLabel labelDescricaoSair = new JLabel("Sair");
        labelDescricaoSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelDescricaoSair.setForeground(new Color(139,0,0));
        labelDescricaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabelOpcoesTelaSecao labelSair = new JLabelOpcoesTelaSecao(null,
                200, 100,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               dispose();
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
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));


        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                320, 400,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-145,-95,300,250);
        labelFaixaSuperiorEsquerda.setCursor(null);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(1010,-95,300,250);
        labelFaixaSuperiorDireita.setCursor(null);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(1015,640,300,250);
        labelFaixaInferiorDireita.setCursor(null);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-125,645,300,250);
        labelFaixaInferiorEsquerda.setCursor(null);


        //Posicionamento
        labelImgTitulo.setBounds(0,10,1200,130);
        labelDescricaoTitulo.setBounds(502,20, 200,100);
        labelAumentaTexto.setBounds(172,365,50,50);
        labelDiminuiTexto.setBounds(980,367,50,50);
        scrollTextoRegra.setBounds(210,205,781,396);
        labelSair.setBounds(492,700,220,80);
        labelDescricaoSair.setBounds(585,695,80,80);

        //Adicionar a tela
        add(labelDescricaoSair);
        add(labelSair);
        add(labelFaixaInferiorEsquerda);
        add(labelFaixaInferiorDireita);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaSuperiorEsquerda);
        add(labelDescricaoTitulo);
        add(labelImgTitulo);
        add(labelAumentaTexto);
        add(labelDiminuiTexto);
        add(scrollTextoRegra);
        add(painelTexto);
    }

    private class TelaAcaoDosLabels implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == labelAumentaTexto){
                ++tamanhoTexto;
                textoRegra.setFont(new Font(Font.DIALOG,Font.BOLD,tamanhoTexto));
            }

            if (e.getSource() == labelDiminuiTexto){
                --tamanhoTexto;
                textoRegra.setFont(new Font(Font.DIALOG,Font.BOLD,tamanhoTexto));
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
