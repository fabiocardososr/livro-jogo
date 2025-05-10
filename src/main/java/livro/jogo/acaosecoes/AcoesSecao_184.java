package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_184 {

    public static void entregaSinoAoFrade(){
        UtilBolsa.removerItem(ItensMapeamento.SINO_DE_METAL.getIdItem());
    }

    public static void recupera4PontosDeEnergia(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        UtilPersonagem.recuperaEnergia(4);
    }
}
