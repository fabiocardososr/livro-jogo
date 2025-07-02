package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_322 {

    public static void pegarMedalhao(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(ItensMapeamento.MEDALHAO_DE_OURO_EM_UMA_FITA_DE_SEDA);
    }

    public static boolean verificaSePossuiCordaDeEscalada(){

        Item item = UtilBolsa.verificarExistenciaDeItemNaBolsaERetornaItem(ItensMapeamento.
                CORDA_DE_ESCALADA.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
