package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_232 {

    public static void recebe1PontosDeSorteECaboDoMarteloDosAnoes(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES.getIdItem()));
        UtilPersonagem.recuperaSorte(1);
    }
}
