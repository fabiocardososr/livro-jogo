package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_319 {

    public static boolean verificaSePossuiBotasSaltadoras(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                BOTAS_SALTADORAS.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
