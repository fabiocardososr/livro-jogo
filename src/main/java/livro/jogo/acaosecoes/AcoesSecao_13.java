package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_13 {

    //Nesta seção o persongagem perde 3 pontos de sorte
    public static void perde3PontosDeSorte(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        UtilPersonagem.personagemPerdeSorte(3);
    }
}
