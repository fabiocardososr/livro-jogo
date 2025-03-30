package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_235 {

    public static void consumirPocaoDaImobilidade(){
        UtilBolsa.removerItem(ItensMapeamento.POCAO_DA_IMOBILIDADE.getIdItem());
    }
}
