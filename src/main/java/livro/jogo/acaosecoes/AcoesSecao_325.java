package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_325 {

    public static boolean verificaSePossuiCabecaDeAlho(){

        Item cabecaDeAlho = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                CABECA_DE_ALHO.getIdItem());

        if ( cabecaDeAlho != null )
            return true;
        else
            return false;
    }
}
