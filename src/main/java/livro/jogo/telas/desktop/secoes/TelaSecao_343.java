package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_160_343;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

public class TelaSecao_343 extends TelaSecoesBasica {
    public TelaSecao_343(Secao secao) {
        super(secao);

        AcoesSecao_160_343.pagar1MoedaDeOuroAoCorvo();
        atualizaIndicesNaTelaDoPersonagem();
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

    }

    @Override
    protected void acaoBotoes(Secao secao) {

    }
}
