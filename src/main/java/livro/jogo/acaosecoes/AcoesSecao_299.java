package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_299 {


    public static void perdeTodasAsProvisoes(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        UtilBolsa.removerTodosItensEspecificado(ItensMapeamento.PROVISAO.getIdItem());
    }
}
