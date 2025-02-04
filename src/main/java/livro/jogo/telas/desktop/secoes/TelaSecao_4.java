package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_4;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

public class TelaSecao_4 extends TelaSecoesBasica {

    public TelaSecao_4(Secao secao) {
        super(secao, CarregarTelas.getReferenciaTelaPrincipal());
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        if ( !AcoesSecao_4.perde4PontosDeEnergia() ){
            //VEJA O BOTÃO DA TELA DA SEÇÃO 2 ONDE O PERSONAGEM MORRE. MANTENHA O PADRÃO
            //CarregarTelas.telaMensagem("Você já estava fraco e esta flecha no ombro causou um enorme sangramento. Você morreu!\n\nFim do jogo");
            return;
        }


    }
}