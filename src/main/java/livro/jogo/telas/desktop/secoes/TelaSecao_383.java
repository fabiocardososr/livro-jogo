package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_383;
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

public class TelaSecao_383 extends TelaSecoesBasica {
    JLabel txtBotaoGanhaEnergiaComendoOCoelhoAssado;

    public TelaSecao_383(Secao secao) {
        super(secao);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                AcoesSecao_383.ganha2moedasEApito();
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
        opcao1(secao);
        labelNumOpcao1.setBounds(116,712, 50,50);
        botaoOpcao1.setBounds(120,720,40,50);
        lbTextoOpcao1.setBounds(170,707,700,60);

        acaoBotoes(secao);
        carregaBotaoComerCoelhoAssado();
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

    private void ganharEnergiaComendoOCoelhoAssado(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/mastigacao.mp3", null);
        AcoesSecao_383.ganha2PontosDeEnergia();
        atualizaIndicesNaTelaDoPersonagem();
        txtBotaoGanhaEnergiaComendoOCoelhoAssado.setText("<html><center>Delícia!</center></html>");;
    }

    private void carregaBotaoComerCoelhoAssado() {
        int largura = 500;
        int altura = 100;
        int eixoY = 570;
        int eixoX = 190;

        //Texto botão repor habilidade
        txtBotaoGanhaEnergiaComendoOCoelhoAssado = new JLabel("<html><center>Comer o coelho</center></html>");
        txtBotaoGanhaEnergiaComendoOCoelhoAssado.setBounds(eixoX+125,eixoY+18,250,60);
        txtBotaoGanhaEnergiaComendoOCoelhoAssado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtBotaoGanhaEnergiaComendoOCoelhoAssado.setHorizontalAlignment(SwingConstants.CENTER);
        txtBotaoGanhaEnergiaComendoOCoelhoAssado.setVerticalAlignment(SwingConstants.CENTER);
        txtBotaoGanhaEnergiaComendoOCoelhoAssado.setFont(new Font(Font.SERIF,Font.BOLD,25));
        txtBotaoGanhaEnergiaComendoOCoelhoAssado.setForeground(new Color(128,0,0));
        txtBotaoGanhaEnergiaComendoOCoelhoAssado.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ganharEnergiaComendoOCoelhoAssado();
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
        JLabelOpcoesTelaSecao botaoGanhaEnergiaComendoOCoelhoAssado = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoGanhaEnergiaComendoOCoelhoAssado.setBounds(eixoX,eixoY,largura,altura);
        botaoGanhaEnergiaComendoOCoelhoAssado.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ganharEnergiaComendoOCoelhoAssado();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoGanhaEnergiaComendoOCoelhoAssado.setIcon(Util.dimensionarImagem(botaoGanhaEnergiaComendoOCoelhoAssado.getWidth(),
                        botaoGanhaEnergiaComendoOCoelhoAssado.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoGanhaEnergiaComendoOCoelhoAssado.setIcon(Util.dimensionarImagem(botaoGanhaEnergiaComendoOCoelhoAssado.getWidth(),
                        botaoGanhaEnergiaComendoOCoelhoAssado.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(txtBotaoGanhaEnergiaComendoOCoelhoAssado);
        add(botaoGanhaEnergiaComendoOCoelhoAssado);
    }
}
