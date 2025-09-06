package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_305 {

    public static void ganha10OuroEFaca(){

        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.FACA_DE_ARREMESSO.getIdItem()));
        UtilPersonagem.somarValorOuro(10);
    }
}
