package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_200 {

    public static void usarChaveDePrataNaFechadura(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/porta_de_pedra_abrindo.mp3", null);
        UtilBolsa.removerItem(ItensMapeamento.CHAVE_DE_PRATA.getIdItem());
    }
}
