package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_262 {

    //No caso bebe a poção, mas como tem efeito temporário, faço de conta que equipei o item
    public static void tomaPocaoDeHabilidadeComArmas(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/bebendo_pocao.mp3", null);
            UtilPersonagem.equiparDesequiparItem(DadosLivroCarregado.getMapItem().get(58));
    }
}
