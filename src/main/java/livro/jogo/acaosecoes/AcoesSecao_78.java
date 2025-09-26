package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_78 {

    public static void incluirNaBolsaPoeiraDaLevitacao(){
        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.POEIRA_DA_LEVITACAO.getIdItem())) {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.POEIRA_DA_LEVITACAO.getIdItem()));
        }
    }

}
