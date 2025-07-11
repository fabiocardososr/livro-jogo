package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_372;
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

public class TelaSecao_372 extends TelaSecoesBasica {
    boolean levantouAArca;
    JLabel textoBotaoRolagemDado;

    public TelaSecao_372(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        labelNumOpcao1.setBounds(116,662, 50,50);
        botaoOpcao1.setBounds(120,670,40,50);
        lbTextoOpcao1.setBounds(170,657,700,60);
        botaoOpcao1.setEnabled(false);

        opcao2(secao);
        labelNumOpcao2.setBounds(116,717, 50,50);
        botaoOpcao2.setBounds(120,725,40,50);
        lbTextoOpcao2.setBounds(170,712,700,60);

        acaoBotoes(secao);
        carregaBotaoRolagemDado();
    }

    private void carregaBotaoRolagemDado() {
        int largura = 550;
        int altura = 120;
        int eixoY = 550;

        //Texto botão repor habilidade
        textoBotaoRolagemDado = new JLabel("<html><center>Rolar os dados</center></html>");
        textoBotaoRolagemDado.setBounds(310,eixoY+26,250,60);
        textoBotaoRolagemDado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoRolagemDado.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoRolagemDado.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoRolagemDado.setFont(new Font(Font.SERIF,Font.BOLD,27));
        textoBotaoRolagemDado.setForeground(new Color(128,0,0));
        textoBotaoRolagemDado.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               conseguiuLevantarArca();
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
        JLabelOpcoesTelaSecao botaoRolagemDado = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoRolagemDado.setBounds(165,eixoY,largura,altura);
        botaoRolagemDado.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                conseguiuLevantarArca();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoRolagemDado.setIcon(Util.dimensionarImagem(botaoRolagemDado.getWidth(),
                        botaoRolagemDado.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoRolagemDado.setIcon(Util.dimensionarImagem(botaoRolagemDado.getWidth(),
                        botaoRolagemDado.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoRolagemDado);
        add(botaoRolagemDado);
    }

    private void conseguiuLevantarArca() {

        if (levantouAArca)
            return;

        levantouAArca = AcoesSecao_372.realizarTesteParaLevantarAArca();

        if ( !levantouAArca ) {
            personagemVivo(AcoesSecao_372.perdeEnergia());
            atualizaIndicesNaTelaDoPersonagem();
        }
        else {
            textoBotaoRolagemDado.setText("Levantou a arca!");
            botaoOpcao1.setEnabled(true);
            botaoOpcao2.setEnabled(false);
        }
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( levantouAArca )
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

        botaoOpcao2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( !levantouAArca )
                    abrirProximaSecao( secao.getProximasSecoes().get(1).getCodProximaSecao() );
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == botaoOpcao2){
                    botaoOpcao2.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem(),
                            botaoOpcao2.getWidth(), botaoOpcao2.getHeight()).getImageIcon());
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == botaoOpcao2){
                    botaoOpcao2.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem(),
                            botaoOpcao2.getWidth(), botaoOpcao2.getHeight()).getImageIcon());
                    repaint();
                }
            }
        });
    }
}
