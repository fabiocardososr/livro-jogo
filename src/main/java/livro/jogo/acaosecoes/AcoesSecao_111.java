package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_111 {

    public static void ganha5PecasDeOuro(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(5);
    }
}
