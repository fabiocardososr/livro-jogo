package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilItemEquipado;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_133 {

    public static void colocaAnelDaLentidaoNoDedo(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        UtilPersonagem.equiparDesequiparItem(DadosLivroCarregado.getMapItem().
                get(ItensMapeamento.ANEL_DA_LENTIDAO.getIdItem()));
    }

    public static boolean verificaSePossuiAnel(){

        Item item = UtilItemEquipado.verificaSeItemEquipadoERetornaItem(ItensMapeamento.ANEL_DA_LENTIDAO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }
}
