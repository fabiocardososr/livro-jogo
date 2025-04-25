package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_158 {

    public static void consumirFaixaDaConcentracao(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                FITA_DE_CABECA_DA_CONCENTRACAO.getIdItem());

        if (item != null)
            UtilBolsa.removerItemEspecifico(item);
    }
}
