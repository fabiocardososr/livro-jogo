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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TelaSecao_19 extends TelaSecoesBasica {
    public TelaSecao_19(Secao secao) {
        super(secao);
        //Referente ao resultado da batalha com lobisomem na seção 285
        DadosLivroCarregado.getPersonagem().setMaldicaoLobisomem(false);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( UtilPersonagem.personagemPerdeEnergia(1) ) {
                    abrirProximaSecao(secao.getProximasSecoes().getFirst().getCodProximaSecao());
                }
                else {
                    new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
                    CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                            ",\nvocê não resistiu ao ferimento, sua energia chegou a 0.\n\nSua aventura acaba aqui.");
                    dispose();
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
