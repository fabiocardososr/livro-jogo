package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_92 {

    public static boolean consumirPocaoControlePlantas(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                POCAO_CONTROLE_DOS_INSETOS.getIdItem());

        if (item != null) {
            UtilBolsa.removerItemEspecifico(item);
            return true;
        }

        return false;
    }
}
