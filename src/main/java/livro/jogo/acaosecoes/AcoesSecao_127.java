package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_127 {

    public static void pagar3PecasDeOuroAoCentauro(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.reduzirValorOuro(3);
    }
}
