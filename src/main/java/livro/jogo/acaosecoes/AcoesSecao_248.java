package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.*;

public class AcoesSecao_248 {

    public static void ganha1PontoDeSorte(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        UtilPersonagem.recuperaSorte(1);
    }

    public static void equiparEscudo(){
        Item item = DadosLivroCarregado.getMapItem().get(ItensMapeamento.ESCUDO_DE_FERRO.getIdItem());

        //Se já equipado, não deixa incluir outro escudo
        if ( UtilItemEquipado.verificaSeItemEquipado(ItensMapeamento.ESCUDO_DE_FERRO.getIdItem()) )
            return;

        if ( item != null)
            UtilPersonagem.equiparDesequiparItem(item);
    }
}
