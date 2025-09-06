package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_237 {

    public static void perde2PontoDeSorte(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
        UtilPersonagem.personagemPerdeSorte(2);
    }

    public static boolean verificaSePossuiPocaoCurativa(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                POCAO_CURATIVA.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
