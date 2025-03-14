package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_18 extends TelaSecoesBasica {
    public TelaSecao_18(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);

        acaoBotoes(secao);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirProximaSecao(secao.getProximasSecoes().getFirst().getCodProximaSecao());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoOpcao1.setIcon(Util.dimensionarImagem(botaoOpcao1.getWidth(),
                        botaoOpcao1.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoOpcao1.setIcon(Util.dimensionarImagem(botaoOpcao1.getWidth(),
                        botaoOpcao1.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem()));
                repaint();
            }
        });
    }
}
