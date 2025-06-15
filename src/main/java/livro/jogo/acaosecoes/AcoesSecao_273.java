package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_273 {

    public static void pegarMedalhao(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(ItensMapeamento.MEDALHAO_DE_OURO);

    }
}
