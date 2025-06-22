package livro.jogo.telas.desktop.secoes;

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
    public TelaSecao_301(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        labelNumOpcao1.setBounds(116,662, 50,50);
        botaoOpcao1.setBounds(120,670,40,50);
        botaoOpcao1.setToolTipText("Resultado do teste: SUCESSO - igual ou menor que a Habilidade");
        lbTextoOpcao1.setBounds(170,657,700,60);

        acaoBotoes(secao);

        carregaBotaoPegarOuro();
    }

    private void pegarOuro(){

    }

    private void carregaBotaoPegarOuro() {
        int largura = 500;
        int altura = 100;
        int eixoY = 570;
        int eixoX = 190;

        //Texto botão repor habilidade
        JLabel textoBotao = new JLabel("<html><center>Pegar ouro</center></html>");
        textoBotao.setBounds(eixoX+125,eixoY+18,250,60);
        textoBotao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotao.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotao.setVerticalAlignment(SwingConstants.CENTER);
        textoBotao.setFont(new Font(Font.SERIF,Font.BOLD,25));
        textoBotao.setForeground(new Color(128,0,0));
        textoBotao.addMouseListener(new MouseListener() {
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
        //textoBotaoConferencia.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão de conferência
        JLabelOpcoesTelaSecao botaoPegarOouro = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoPegarOouro.setBounds(eixoX,eixoY,largura,altura);
        botaoPegarOouro.addMouseListener(new MouseListener() {
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
                botaoPegarOouro.setIcon(Util.dimensionarImagem(botaoPegarOouro.getWidth(),
                        botaoPegarOouro.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoPegarOouro.setIcon(Util.dimensionarImagem(botaoPegarOouro.getWidth(),
                        botaoPegarOouro.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotao);
        add(botaoPegarOouro);
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
