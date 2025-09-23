package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_174 {
    public static void recebe1PontosDeSorteEGrandeAnelDeOuro(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);

        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.GRANDE_ANEL_DE_OURO.getIdItem())) {
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.GRANDE_ANEL_DE_OURO.getIdItem()));
        }

        UtilPersonagem.recuperaSorte(1);
    }
}
