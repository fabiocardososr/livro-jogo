package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_95 {

    public static boolean verificaSePossuiPoeiraDaLevitacao(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                POEIRA_DA_LEVITACAO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
