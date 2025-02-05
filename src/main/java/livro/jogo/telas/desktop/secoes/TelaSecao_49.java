package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

public class TelaSecao_49 extends TelaSecoesBasica {
    public TelaSecao_49(Secao secao) {
        super(secao, CarregarTelas.getReferenciaTelaPrincipal());
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

    }
}
