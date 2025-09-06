package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_301 {

    public static void pegar3PecasDeOuro(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(3);
    }

    public static void pegarFlauta(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.FLAUTA_DO_SONO_DO_DRAGAO.getIdItem()));
    }

    public static void pegarBiscoitos(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.BISCOITO_CHEIO_DE_BICHOS.getIdItem()));
    }

    public static void pegarColar(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.COLAR_DE_CRANIO_DE_CAMUNDONGOS.getIdItem()));
    }
}
