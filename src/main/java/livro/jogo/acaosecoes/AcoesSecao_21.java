package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_21 {

    public static void beberPocaoAntiveneno(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/bebendo_pocao.mp3", null);
        UtilBolsa.removerItem(ItensMapeamento.POCAO_ANTIVENENO.getIdItem());
    }
}
