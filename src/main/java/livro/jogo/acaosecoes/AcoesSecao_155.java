package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_155 {

    public static boolean verificaSePossuiBeladona(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                BELADONA.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
