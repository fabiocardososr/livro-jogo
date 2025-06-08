package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.Util;

public class TelaSecao_367 extends TelaSecoesBasica {
    public TelaSecao_367(Secao secao) {
        super(secao);
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/estalo.mp3", null);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

    }

    @Override
    protected void acaoBotoes(Secao secao) {

    }
}
