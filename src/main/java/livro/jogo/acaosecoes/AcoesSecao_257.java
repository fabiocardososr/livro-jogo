package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_257 {

    //Recupera 4 pontos de energia
    public static void comerPaoElfo(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/mastigacao.mp3", null);
        UtilPersonagem.recuperaEnergia(4);
    }

    public static void ganha1DeSorte(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        UtilPersonagem.recuperaSorte(1);
    }
}
