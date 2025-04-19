package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_159;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TelaSecao_159 extends TelaSecoesBasica {
    public TelaSecao_159(Secao secao) {
        super(secao);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                //Se personagem morto, fecha a tela de seção e abre a principal
                personagemVivo( AcoesSecao_159.perde3PontosDeEnergia() );

                //Atualiza label que mostra os níveis de habilidade, sorte e energia do personagem na tela
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

    }
}
