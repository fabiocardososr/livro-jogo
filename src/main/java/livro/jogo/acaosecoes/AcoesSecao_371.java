package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_371 {

    public static void ganhar3Moedas(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(3);
    }

    public static boolean pegarMao(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.MAO_DE_ARGILA.getIdItem()));
        return true;
    }
}
