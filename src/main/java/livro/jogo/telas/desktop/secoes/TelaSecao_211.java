package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

public class TelaSecao_211 extends TelaSecoesBasica {
    public TelaSecao_211(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
            //AQUI VC PRECISA REMOVER O ITEM POÇÃO ANTIVENENO DA MÃO (EQUIPADO).
        // PORQUE SE ENTROU NESTA SEÇÃO É PORQUE VC TEM A POÇÃO ANTIVENENO
    }

    @Override
    protected void acaoBotoes(Secao secao) {

    }
}
