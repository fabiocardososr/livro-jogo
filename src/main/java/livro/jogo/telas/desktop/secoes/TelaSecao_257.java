package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_257;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TelaSecao_257 extends TelaSecoesBasica {
    boolean comeu = false;
    JLabel botaoComerPaoElfo;

    public TelaSecao_257(Secao secao) {
        super(secao);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                AcoesSecao_257.ganha1DeSorte();
                atualizaIndicesNaTelaDoPersonagem();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // Código a ser executado quando o diálogo está fechando
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // Código a ser executado quando o diálogo é fechado
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // Código a ser executado quando o diálogo é minimizado
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // Código a ser executado quando o diálogo é restaurado
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // Código a ser executado quando o diálogo é ativado
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // Código a ser executado quando o diálogo é desativado
            }
        });
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        labelNumOpcao1.setBounds(116,562, 50,50);
        botaoOpcao1.setBounds(120,570,40,50);
        lbTextoOpcao1.setBounds(170,557,700,60);

        criarBotaoComerPaoElfo();
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
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

    private void comerPao(){
        if ( comeu )
            return;

        AcoesSecao_257.comerPaoElfo();
        atualizaIndicesNaTelaDoPersonagem();
        botaoComerPaoElfo.setText("Pão comido!");
        comeu = true;
    }

    private void criarBotaoComerPaoElfo(){
        int largura = 500;
        int altura = 100;
        int eixoY = 470;
        int eixoX = 190;

        botaoComerPaoElfo = new JLabel("<html><center>Comer o pão elfo</center></html>");
        botaoComerPaoElfo.setBounds(eixoX+125,eixoY+18,250,60);
        botaoComerPaoElfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoComerPaoElfo.setHorizontalAlignment(SwingConstants.CENTER);
        botaoComerPaoElfo.setVerticalAlignment(SwingConstants.CENTER);
        botaoComerPaoElfo.setFont(new Font(Font.SERIF,Font.BOLD,25));
        botaoComerPaoElfo.setForeground(new Color(128,0,0));
        botaoComerPaoElfo.setToolTipText("Estragam rápido. Não podem ser guardados.");
        botaoComerPaoElfo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

               comerPao();
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


        //Botão de conferência
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(eixoX,eixoY,largura,altura);
        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                comerPao();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setIcon(Util.dimensionarImagem(botao.getWidth(),
                        botao.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setIcon(Util.dimensionarImagem(botao.getWidth(),
                        botao.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(botaoComerPaoElfo);
        add(botao);
    }
}
