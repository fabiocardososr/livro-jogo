package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_301;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_301 extends TelaSecoesBasica {
    private JLabel textoBotaoPagarMoedas;
    private JLabel textoBotaoPagarFlauta;
    private JLabel textoBotaoPagarBiscoitos;
    private JLabel textoBotaoPagarColar;
    private boolean pegouOuro, pegouFlauta, pegouBiscoitos, pegouColar;

    public TelaSecao_301(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        //Opção 1
        labelNumOpcao1.setBounds(86,562, 50,50);
        botaoOpcao1.setBounds(90,570,40,50);
        lbTextoOpcao1.setBounds(140,557,700,60);

        carregaBotaoPegarOuro();
        carregaBotaoPegarFlauta();
        carregaBotaoPegarBiscoitos();
        carregaBotaoPegarColar();
    }

    private void pegarOuro(){

        if ( pegouOuro )
            return;

        AcoesSecao_301.pegar3PecasDeOuro();
        pegouOuro = true;
        atualizaIndicesNaTelaDoPersonagem();
        textoBotaoPagarMoedas.setText("<html><center>Adquirido!</center></html>");
    }

    private void pegarFlauta(){

        if ( pegouFlauta )
            return;

        AcoesSecao_301.pegarFlauta();
        pegouFlauta = true;
        textoBotaoPagarFlauta.setText("<html><center>Adquirido!</center></html>");
    }

    private void pegarBiscoitos(){

        if ( pegouBiscoitos )
            return;

        AcoesSecao_301.pegarBiscoitos();
        pegouBiscoitos = true;
        textoBotaoPagarBiscoitos.setText("<html><center>Adquirido!</center></html>");
    }

    private void pegarColar(){

        if ( pegouColar )
            return;

        AcoesSecao_301.pegarColar();
        pegouColar = true;
        textoBotaoPagarColar.setText("<html><center>Adquirido!</center></html>");
    }

    private void carregaBotaoPegarOuro() {
        int largura = 345;
        int altura  = 70;
        int eixoY   = 435;
        int eixoX   = 90;

        //Texto botão repor habilidade
        textoBotaoPagarMoedas = new JLabel("<html><center>Pegar ouro</center></html>");
        textoBotaoPagarMoedas.setBounds(eixoX+100,eixoY+16,150,30);
        textoBotaoPagarMoedas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoPagarMoedas.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoPagarMoedas.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoPagarMoedas.setFont(new Font(Font.SERIF,Font.BOLD,22));
        textoBotaoPagarMoedas.setForeground(new Color(128,0,0));
        textoBotaoPagarMoedas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pegarOuro();
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
        //textoBotaoPagarMoedas.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão de conferência
        JLabelOpcoesTelaSecao botaoPegarOuro = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoPegarOuro.setBounds(eixoX,eixoY,largura,altura);
        botaoPegarOuro.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pegarOuro();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoPegarOuro.setIcon(Util.dimensionarImagem(botaoPegarOuro.getWidth(),
                        botaoPegarOuro.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoPegarOuro.setIcon(Util.dimensionarImagem(botaoPegarOuro.getWidth(),
                        botaoPegarOuro.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoPagarMoedas);
        add(botaoPegarOuro);
    }

    private void carregaBotaoPegarFlauta() {
        int largura = 345;
        int altura = 70;
        int eixoY = 420;
        int eixoX = 90;

        //Texto botão repor habilidade
        textoBotaoPagarFlauta = new JLabel("<html><center>Pegar flauta</center></html>");
        textoBotaoPagarFlauta.setBounds(eixoX+100,eixoY+97,150,30);
        textoBotaoPagarFlauta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoPagarFlauta.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoPagarFlauta.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoPagarFlauta.setFont(new Font(Font.SERIF,Font.BOLD,20));
        textoBotaoPagarFlauta.setForeground(new Color(128,0,0));
        textoBotaoPagarFlauta.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pegarFlauta();
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
        //textoBotaoPagarFlauta.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão de conferência
        JLabelOpcoesTelaSecao botaoPegarFlauta = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoPegarFlauta.setBounds(eixoX,eixoY+80,largura,altura);
        botaoPegarFlauta.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pegarFlauta();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoPegarFlauta.setIcon(Util.dimensionarImagem(botaoPegarFlauta.getWidth(),
                        botaoPegarFlauta.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoPegarFlauta.setIcon(Util.dimensionarImagem(botaoPegarFlauta.getWidth(),
                        botaoPegarFlauta.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoPagarFlauta);
        add(botaoPegarFlauta);
    }

    private void carregaBotaoPegarBiscoitos() {
        int largura = 345;
        int altura  = 70;
        int eixoY   = 435;
        int eixoX   = 430;

        //Texto botão repor habilidade
        textoBotaoPagarBiscoitos = new JLabel("<html><center>Pegar biscoitos</center></html>");
        textoBotaoPagarBiscoitos.setBounds(eixoX+100,eixoY+17,150,30);
        textoBotaoPagarBiscoitos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoPagarBiscoitos.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoPagarBiscoitos.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoPagarBiscoitos.setFont(new Font(Font.SERIF,Font.BOLD,20));
        textoBotaoPagarBiscoitos.setForeground(new Color(128,0,0));
        textoBotaoPagarBiscoitos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pegarBiscoitos();
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
        //textoBotaoPagarMoedas.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão de conferência
        JLabelOpcoesTelaSecao botaoPegarBiscoitos = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoPegarBiscoitos.setBounds(eixoX,eixoY,largura,altura);
        botaoPegarBiscoitos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pegarBiscoitos();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoPegarBiscoitos.setIcon(Util.dimensionarImagem(botaoPegarBiscoitos.getWidth(),
                        botaoPegarBiscoitos.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoPegarBiscoitos.setIcon(Util.dimensionarImagem(botaoPegarBiscoitos.getWidth(),
                        botaoPegarBiscoitos.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoPagarBiscoitos);
        add(botaoPegarBiscoitos);
    }

    private void carregaBotaoPegarColar() {
        int largura = 345;
        int altura  = 70;
        int eixoY   = 420;
        int eixoX   = 430;

        //Texto botão repor habilidade
        textoBotaoPagarColar = new JLabel("<html><center>Pegar colar</center></html>");
        textoBotaoPagarColar.setBounds(eixoX+100,eixoY+97,150,30);
        textoBotaoPagarColar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoPagarColar.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoPagarColar.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoPagarColar.setFont(new Font(Font.SERIF,Font.BOLD,20));
        textoBotaoPagarColar.setForeground(new Color(128,0,0));
        textoBotaoPagarColar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pegarColar();
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
        //textoBotaoPagarMoedas.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão de conferência
        JLabelOpcoesTelaSecao botaoPegarColar = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoPegarColar.setBounds(eixoX,eixoY+80,largura,altura);
        botaoPegarColar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pegarColar();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoPegarColar.setIcon(Util.dimensionarImagem(botaoPegarColar.getWidth(),
                        botaoPegarColar.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoPegarColar.setIcon(Util.dimensionarImagem(botaoPegarColar.getWidth(),
                        botaoPegarColar.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoPagarColar);
        add(botaoPegarColar);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    botaoOpcao1.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem(),
                            botaoOpcao1.getWidth(), botaoOpcao1.getHeight()).getImageIcon());
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    botaoOpcao1.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem(),
                            botaoOpcao1.getWidth(), botaoOpcao1.getHeight()).getImageIcon());
                    repaint();
                }
            }
        });
    }
}
