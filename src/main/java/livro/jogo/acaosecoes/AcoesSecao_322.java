package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_322 {

    public static void pegarMedalhao(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);

        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.MEDALHAO_DE_OURO_EM_UMA_FITA_DE_SEDA.getIdItem())) {
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.MEDALHAO_DE_OURO_EM_UMA_FITA_DE_SEDA.getIdItem()));
        }
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
