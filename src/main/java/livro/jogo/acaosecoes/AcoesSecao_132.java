package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_132 {

    public static boolean verificaSePossuiFlautaDoSonoDoDragao(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                FLAUTA_DO_SONO_DO_DRAGAO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
