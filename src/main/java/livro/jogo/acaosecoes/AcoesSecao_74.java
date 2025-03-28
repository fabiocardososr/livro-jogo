package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_74 {

    public static void receba5PecasDeOuroESinoDeMetal(){
        UtilPersonagem.somarValorOuro(5);
        UtilBolsa.incluirItem(ItensMapeamento.SINO_DE_METAL);
    }
}
