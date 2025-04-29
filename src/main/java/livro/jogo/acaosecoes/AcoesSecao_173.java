package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_173 {

    public static void removerDaBolsaAPoeiraDaLevitacao(){
        UtilBolsa.removerItem(ItensMapeamento.POEIRA_DA_LEVITACAO.getIdItem());
    }

    public static boolean verificaSePossuiAguaBenta(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                AGUA_BENTA.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
