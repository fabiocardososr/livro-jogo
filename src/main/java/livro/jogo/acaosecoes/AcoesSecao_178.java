package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_178 {

    public static void perde1Provisao(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                PROVISAO.getIdItem());

        if ( item != null)
            UtilBolsa.removerItemEspecifico(item);
    }

}
