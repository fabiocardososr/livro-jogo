package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JButtonAbrirBatalha;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_49 extends TelaSecoesBasica {
    public TelaSecao_49(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        labelNumOpcao1.setBounds(416,492, 50,50);
        lbTextoOpcao1.setBounds(470,487,700,60);
        botaoOpcao1.setBounds(420,500,40,50);
        botaoOpcao1.setToolTipText("Somente após vencer todos os inimigos você pode escolher esta opção.");


        //Inimigos
        configurandoBotoesBatalha(secao);
    }

    private void configurandoBotoesBatalha(Secao secao) {

        JButtonAbrirBatalha.carregarBotoesBatalha(this, secao.getInimigos().get(0),
                100,460,150,165);

        JButtonAbrirBatalha.carregarBotoesBatalha(this, secao.getInimigos().get(1),
                280,460,150,165);

    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    if ( Util.isVenceuTodosInimigos(secao) )
                            abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nvocê deve vencer os inimigos antes de continuar sua jornada.");
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
}
