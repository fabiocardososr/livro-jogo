package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_211 {

    public static void bebePocaoAntiveneno(){
        UtilBolsa.removerItem(ItensMapeamento.POCAO_ANTIVENENO.getIdItem());
    }
}
