package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_287;
import livro.jogo.acaosecoes.AcoesSecao_74_101;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TelaSecao_287 extends TelaSecoesBasica {
    public TelaSecao_287(Secao secao) {
        super(secao);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
                AcoesSecao_287.receba5PecasDeOuroESinoDeMetal();
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
