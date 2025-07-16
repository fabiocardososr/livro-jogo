package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_391 {

    public static boolean verificaSePossuiPocaoCurativa(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                CHAVE_DE_PRATA.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
