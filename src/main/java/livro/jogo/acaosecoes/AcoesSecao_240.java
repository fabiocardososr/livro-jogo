package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_240 {

    public static boolean verificaSePossuiFiltrosNasais(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                FILTROS_NASAIS.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
