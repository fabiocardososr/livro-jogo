package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_191 {

    public static boolean verificaSePossuiSinoDeMetal(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                SINO_DE_METAL.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
