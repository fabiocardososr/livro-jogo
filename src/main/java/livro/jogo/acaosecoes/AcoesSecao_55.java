package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_55 {
    public static void vesteASLuvasDEAremesso(){
        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                LUVA_DE_DESTREZA_DE_ARREMESSO.getIdItem());

        if ( item != null)
            UtilPersonagem.equiparDesequiparItem(item);
    }
}
