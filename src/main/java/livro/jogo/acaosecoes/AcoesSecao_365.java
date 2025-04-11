package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_365 {

    public static void removerDaBolsaFiltrosNasais(){
        UtilBolsa.removerItem(ItensMapeamento.FILTROS_NASAIS.getIdItem());
    }
}
