package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.*;

public class AcoesSecao_374 {

    public static boolean verificaSePossuiManopla(){

        Item item = UtilItemEquipado.verificaSeItemEquipadoERetornaItem(ItensMapeamento.MANOPLA_DE_FERRO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }

    public static boolean verificaSePossuiAnelDaLentidao(){

        Item item = UtilItemEquipado.verificaSeItemEquipadoERetornaItem(ItensMapeamento.ANEL_DA_LENTIDAO.getIdItem());

        if (item != null)
            return true;
        else
            return false;
    }

    public static void ganhaManoplaDeHabilidade(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);

        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilItemEquipado.verificaSeItemEquipado(ItensMapeamento.MANOPLA_DE_FERRO.getIdItem()) )
            UtilItemEquipado.incluirItem( UtilItens.retornaItem((ItensMapeamento.MANOPLA_DE_FERRO.getIdItem())) );

    }
}
