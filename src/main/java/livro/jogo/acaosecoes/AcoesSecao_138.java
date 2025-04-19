package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_138 {

    public static boolean verificaSePossuiPocaoControleDePlantas(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                POCAO_CONTROLE_DAS_PLANTAS.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
