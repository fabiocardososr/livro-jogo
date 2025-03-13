package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

public class TelaSecao_16 extends TelaSecoesBasica {
    public TelaSecao_16(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        opcao2(secao);
        acaoBotoes(secao);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
//        if (e.getSource() == botaoOpcao1){
//            if ( Util.isVenceuTodosInimigos(secao) )
//                abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
//            else
//                CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
//                        ",\nvocê deve vencer o inimigo antes de continuar sua jornada.");
//        }
    }
}
