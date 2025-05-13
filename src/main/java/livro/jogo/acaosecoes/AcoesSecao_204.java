package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_204 {

    public static boolean verificaSePossuiTodasAsPartesDoMarteloDosAnoes(){

        Item cabecaDoMartelo = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                CABECA_DE_MARTELO_DOS_ANOES.getIdItem());

        Item caboDoMartelo = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES.getIdItem());

        if ( (cabecaDoMartelo != null) && (caboDoMartelo != null) )
            return true;
        else
            return false;
    }
}
