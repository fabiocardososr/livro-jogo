package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_50 {

    //O personagem pega a chave de prata
    public static void incluirChaveDePrataNaBolsa(){
        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.CHAVE_DE_PRATA.getIdItem())) {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.CHAVE_DE_PRATA.getIdItem()));
        }
    }


}
