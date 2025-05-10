package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_53 {
    public static void perdeTodoSeuOuro(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        UtilPersonagem.perdeTodoOuro();
    }
}
