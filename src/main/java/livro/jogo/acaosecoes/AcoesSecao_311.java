package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_311 {

    public static void pegar2moedasDeOuro(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(2);
    }
}
