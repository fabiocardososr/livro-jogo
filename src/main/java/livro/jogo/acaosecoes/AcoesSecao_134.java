package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_134 {

    public static void perde1PontoDeSorte(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
        UtilPersonagem.personagemPerdeSorte(1);
    }
}
