package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_68 {

    public static void soma3PontosDeEnergia(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilPersonagem.recuperaEnergia(3);
    }
}
