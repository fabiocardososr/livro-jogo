package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_273 {

    public static void pegarMedalhao(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.MEDALHAO_DE_OURO.getIdItem())) {
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.MEDALHAO_DE_OURO.getIdItem()));
        }
    }
}
