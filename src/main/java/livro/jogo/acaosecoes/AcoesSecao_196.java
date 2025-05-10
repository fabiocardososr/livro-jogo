package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_196 {

    public static boolean verificaSePossuiRedeDeAprisionamento(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                REDE_DE_APRISIONAMENTO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
