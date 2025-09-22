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

public class TelaSecao_17 extends TelaSecoesBasica {
    public TelaSecao_17(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

    }

    @Override
    protected void acaoBotoes(Secao secao) {

        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (UtilPersonagem.verificaValorOuroSuficiente(1)) {

                    abrirProximaSecao(secao.getProximasSecoes().getFirst().getCodProximaSecao());

                }
                else
                    CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                            ",\nvocê não possui 1 moeda para jogar no poço.\n\nEscolha outra opção");
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

        botaoOpcao2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirProximaSecao(secao.getProximasSecoes().get(1).getCodProximaSecao());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoOpcao2.setIcon(Util.dimensionarImagem(botaoOpcao2.getWidth(),
                        botaoOpcao2.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoOpcao2.setIcon(Util.dimensionarImagem(botaoOpcao2.getWidth(),
                        botaoOpcao2.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem()));
                repaint();
            }
        });

        botaoOpcao3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirProximaSecao(secao.getProximasSecoes().get(2).getCodProximaSecao());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoOpcao3.setIcon(Util.dimensionarImagem(botaoOpcao3.getWidth(),
                        botaoOpcao3.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoOpcao3.setIcon(Util.dimensionarImagem(botaoOpcao3.getWidth(),
                        botaoOpcao3.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem()));
                repaint();
            }
        });

    }
}
