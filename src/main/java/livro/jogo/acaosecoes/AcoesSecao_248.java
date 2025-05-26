package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_248 {

    public static void ganha1PontoDeSorte(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        UtilPersonagem.recuperaSorte(1);
    }

    public static void equiparEscudo(){
        Item item = DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_28.json");

        if ( item != null)
            UtilPersonagem.equiparDesequiparItem(item);
    }
}
