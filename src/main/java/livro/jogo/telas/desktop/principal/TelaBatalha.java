package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaBatalha extends JDialog {
    private final Inimigo inimigo;
    private static boolean respostaTelaConfirmacao;
    private final TelaBatalha tela = this;
    private final Personagem personagem = DadosLivroCarregado.getPersonagem();
    private JLabel labelEsquerdo;
    private JLabel labelDireito;

    public TelaBatalha(Inimigo inimigo) {
        this.inimigo = inimigo;
        setSize(850,700);
        setLayout(null);
        setTitle("Batalha");
        setModal(true);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setCursor(null);
        getContentPane().setBackground(new Color(210,180,140));

        carregaImagemMostraResultRolagemDado();
        carregarPainelEsquerdo();
        carregarPainelDireito();
        carregarBotaoSair();
        carregarFaixaTitulo();
        carregarFaixasDasExtremidades();

        System.out.println(this.inimigo);
    }

    private void carregaImagemMostraResultRolagemDado() {

        //Imagem esquerda
        JLabelOpcoesTelaSecao mostradorEsquerdo = new JLabelOpcoesTelaSecao(null,
                150,150,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_LUTA);
        mostradorEsquerdo.setHorizontalAlignment(SwingConstants.CENTER);
        mostradorEsquerdo.setCursor(null);
        mostradorEsquerdo.setBounds(225,330, 150,150);

        labelEsquerdo = new JLabel("10");
        labelEsquerdo.setBounds(225,330, 150,150);
        labelEsquerdo.setForeground(new Color(139,0,0));
        labelEsquerdo.setForeground(Color.BLACK);
        labelEsquerdo.setHorizontalAlignment(SwingConstants.CENTER);
        labelEsquerdo.setFont(new Font(Font.SERIF,Font.BOLD,60));




        JLabelOpcoesTelaSecao mostradorDireito = new JLabelOpcoesTelaSecao(null,
                150,150,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_LUTA);
        mostradorDireito.setHorizontalAlignment(SwingConstants.CENTER);
        mostradorDireito.setCursor(null);
        mostradorDireito.setBounds(465,330, 150,150);



        add(labelEsquerdo);
        add(mostradorDireito);
        add(mostradorEsquerdo);

    }

    public static boolean isRespostaTelaConfirmacao() {
        return respostaTelaConfirmacao;
    }

    public void setRespostaTelaConfirmacao(boolean respostaTelaConfirmacao) {
        this.respostaTelaConfirmacao = respostaTelaConfirmacao;
    }

    private void carregarPainelEsquerdo() {
        JLabelOpcoesTelaSecao imgPersonagem;

        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(null);
        panelEsquerdo.setBounds(20,250,220,320);
        panelEsquerdo.setBackground(new Color(0,0,0,0));

        //Moldura
        JLabelOpcoesTelaSecao moldura = new JLabelOpcoesTelaSecao(null,
                220,320,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_12);
        moldura.setHorizontalAlignment(SwingConstants.CENTER);
        moldura.setCursor(null);
        moldura.setBounds(0,0, 210,320);


        //Imagem do personagem se mulher ou homem
        if (personagem.getGenero() == 1)
            imgPersonagem = new JLabelOpcoesTelaSecao(null, 130,190,
                    ImagensDoLivroFlorestaDaDestruicao.BARBARO.getEnderecoImagem());
        else
            imgPersonagem = new JLabelOpcoesTelaSecao(null, 130,190,
                    ImagensDoLivroFlorestaDaDestruicao.BARBARA.getEnderecoImagem());



        imgPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        imgPersonagem.setBounds(0,0,220,320);


        panelEsquerdo.add(imgPersonagem);
        panelEsquerdo.add(moldura);
        add(panelEsquerdo);
    }

    private void carregarPainelDireito() {

        JPanel panelDireito = new JPanel();
        panelDireito.setLayout(null);
        panelDireito.setBounds(610,250,220,320);
        panelDireito.setBackground(new Color(0,0,0,0));

        //Moldura
        JLabelOpcoesTelaSecao moldura = new JLabelOpcoesTelaSecao(null,
                220,320,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_12);
        moldura.setHorizontalAlignment(SwingConstants.CENTER);
        moldura.setCursor(null);
        moldura.setBounds(0,0, 210,320);


        //Imagem do inimigo
        JLabelOpcoesTelaSecao imgPersonagem = new JLabelOpcoesTelaSecao(null, 130,190,
                    inimigo.getEnderecoImagem());
        imgPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        imgPersonagem.setBounds(0,0,220,320);

        panelDireito.add(imgPersonagem);
        panelDireito.add(moldura);
        add(panelDireito);
    }

    private void carregarBotaoSair() {
        JLabel label = new JLabel("Sair");
        label.setForeground(new Color(139,0,0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SERIF,Font.BOLD,30));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setBounds(340,606, 180,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabelOpcoesTelaSecao labelBotaoSair = new JLabelOpcoesTelaSecao(null,230,185,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelBotaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelBotaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelBotaoSair.setBounds(312,545, 230,185);
        labelBotaoSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CarregarTelas.telaMensagem("Deseja abandonar a luta?\n\n A criatura será regenerada, mas você não.", tela);

                if (respostaTelaConfirmacao)
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

        add(label);
        add(labelBotaoSair);
    }

    private void carregarFaixaTitulo(){
        //Label
        JLabel label = new JLabel("Batalha");
        label.setForeground(new Color(139,0,0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SERIF,Font.BOLD,45));
        label.setBounds(328,115, 200,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabelOpcoesTelaSecao labelFaixaFaixaTitulo = new JLabelOpcoesTelaSecao(null,
                400, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_11);
        labelFaixaFaixaTitulo.setBounds(230,10,400,250);
        //labelFaixaFaixaTitulo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(label);
        add(labelFaixaFaixaTitulo);
    }

    private void carregarFaixasDasExtremidades() {
        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250, ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-115,-100,300,250);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(655,-108,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(670,550,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-115,560,300,250);


        add(labelFaixaInferiorDireita);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaSuperiorEsquerda);
        add(labelFaixaInferiorEsquerda);
    }
}
