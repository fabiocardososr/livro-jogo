package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_338 {

    public static void colocaCaixaNaBolsa(){
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.CAIXA_DE_PRATA.getIdItem()) ) {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.CAIXA_DE_PRATA.getIdItem()));
        }
    }
}
