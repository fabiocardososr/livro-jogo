package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_174 {
    public static void recebe1PontosDeSorteEGrandeAnelDeOuro(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(ItensMapeamento.GRANDE_ANEL_DE_OURO);
        UtilPersonagem.recuperaSorte(1);
    }
}
