package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_137 {

    public static boolean verificaSePossuiLuvaDeDestrezaDeArremesso(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                LUVA_DE_DESTREZA_DE_ARREMESSO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
