package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_114 {

    public static boolean verificaSePossuiCapsulasDeFogo(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                CAPSULA_DE_FOGO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
