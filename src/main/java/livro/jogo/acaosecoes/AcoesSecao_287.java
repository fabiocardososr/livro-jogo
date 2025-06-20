package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_287 {

    public static void receba5PecasDeOuroESinoDeMetal(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(5);
        UtilBolsa.incluirItem(ItensMapeamento.SINO_DE_METAL);
    }
}
