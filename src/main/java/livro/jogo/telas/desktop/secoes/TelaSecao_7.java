package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JButtonAbrirBatalha;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_7 extends TelaSecoesBasica {

    public TelaSecao_7(Secao secao) {
        super(secao, CarregarTelas.getReferenciaTelaPrincipal());
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        //Redimensionando o botão da opção 1 (direcionando para a próxima seção)
        labelNumOpcao.setBounds(116,732, 50,50);
        lbTextoOpcao1.setBounds(170,727,700,60);
        botaoOpcao1.setBounds(120,740,40,50);
        botaoOpcao1.setToolTipText("Somente após vencer todos os inimigos você pode escolher esta opção.");

        //Configurando botões
        acaoBotoes(secao);

        //Inclusao dos botões que representam os inimigos a serem atacados
        configurandoBotoesBatalha(secao);
    }

    private void configurandoBotoesBatalha(Secao secao) {
        JButtonAbrirBatalha.carregarBotoesBatalha(this, secao.getInimigos().get(0),
                100,570,150,165);

        JButtonAbrirBatalha.carregarBotoesBatalha(this, secao.getInimigos().get(1),
                365,570,150,165);

        JButtonAbrirBatalha.carregarBotoesBatalha(this, secao.getInimigos().get(2),
                630,570,150,165);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    if ( isVenceuBatalha() )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ", você deve vencer os inimigos antes de continuar sua jornada.");
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


}
