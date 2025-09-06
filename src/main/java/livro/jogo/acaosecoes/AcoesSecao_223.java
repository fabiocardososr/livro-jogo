package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_223 {
    public static void receba5PecasDeOuro(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(5);
    }
}
