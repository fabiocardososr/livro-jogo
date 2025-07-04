package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_336 {

    public static boolean verificaSePossuiPocaoAntiveneno(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                POCAO_ANTIVENENO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
