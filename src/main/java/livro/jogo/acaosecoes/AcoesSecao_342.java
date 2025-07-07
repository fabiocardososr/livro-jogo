package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_342 {

    public static boolean verificaSePossuiFitaDeCabecaDaConcentracao(){

        Item fita = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                FITA_DE_CABECA_DA_CONCENTRACAO.getIdItem());

        if ( fita != null )
            return true;
        else
            return false;
    }
}
