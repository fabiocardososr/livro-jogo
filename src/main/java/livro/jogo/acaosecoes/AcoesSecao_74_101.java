package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_74_101 {

    public static void receba5PecasDeOuroESinoDeMetal(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(5);
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.SINO_DE_METAL.getIdItem()));
    }
}
