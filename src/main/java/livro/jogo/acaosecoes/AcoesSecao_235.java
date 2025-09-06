package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_235 {

    public static void consumirPocaoDaImobilidade(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.removerItem(ItensMapeamento.POCAO_DA_IMOBILIDADE.getIdItem());
        UtilPersonagem.recuperaSorte(1);
    }
}
