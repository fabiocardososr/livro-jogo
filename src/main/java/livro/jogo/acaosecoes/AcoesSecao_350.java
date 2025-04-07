package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_350 {

    public static void removerDaCapsulasDeFogo(){
        UtilBolsa.removerItem(ItensMapeamento.CAPSULA_DE_FOGO.getIdItem());
    }
}
