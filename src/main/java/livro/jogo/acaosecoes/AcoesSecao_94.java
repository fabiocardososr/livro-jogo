package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_94 {

    //Retirar a Beladona da bolsa (foi consumida)
    public static void usarCordaDeEscalda(){
        UtilBolsa.removerItem(ItensMapeamento.CORDA_DE_ESCALADA.getIdItem());
    }
}
