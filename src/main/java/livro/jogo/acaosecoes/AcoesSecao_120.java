package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_120 {

    public static boolean verificaSePossuiCordaDeEscalada(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                CORDA_DE_ESCALADA.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
