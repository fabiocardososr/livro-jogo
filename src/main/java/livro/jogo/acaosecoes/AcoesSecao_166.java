package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_166 {

    public static void doa1MoedaParaOFrade(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.reduzirValorOuro(1);
    }

    public static void ganha2PontosDeSorte(){
        UtilPersonagem.recuperaSorte(2);
    }
}
