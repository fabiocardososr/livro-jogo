package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_178 {

    public static void perde1Provisao(){
        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                PROVISAO.getIdItem());

        if ( item != null)
            UtilBolsa.removerItemEspecifico(item);
    }

}
