package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilItemEquipado;

public class AcoesSecao_295 {

    public static boolean verificaSePossuiElmo(){

        Item item = UtilItemEquipado.verificaSeItemEquipadoERetornaItem(ItensMapeamento.
                ELMO_DE_BRONZE.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
