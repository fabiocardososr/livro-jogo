package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilItemEquipado;

import java.util.ArrayList;

public class AcoesSecao_12 {

    ///Recupera a espada (Atenção: Existe outra espada idItem(10))
    ///Se a espada comum está na bolsa, consequentemente o personagem estava equipado com uma espada melhor.
    ///Então é necessário antes verificar, pois a espada escolhida a ser devolvida pode ser outra além da espada comum
    public static void recuperaEspadaDoGnomo(){
        ArrayList<Item> bolsa = DadosLivroCarregado.getBolsa();
        boolean espadaComumNaBolsa = false;
        boolean jaPossuiEspadaMagnifica = false;


        //Pesquisa na bolsa a existência da espada comum
        for (Item item: bolsa)
            if (item.getIdItem() == ItensMapeamento.ESPADA.getIdItem()) {
                espadaComumNaBolsa = true;
                break;
            }


        ///Se existir a espada comum(a que o personagem inicia o jogo) na bolsa,
        ///indica que o personagem estava equipado com a Espada Magnífica. Já que a espada comum deve ir para bolsa
        if ( espadaComumNaBolsa ) {
            for (Item item: bolsa)
                if (item.getIdItem() == ItensMapeamento.ESPADA_MAGNIFICA.getIdItem()) {
                    jaPossuiEspadaMagnifica = true;
                    break;
                }
            if ( !jaPossuiEspadaMagnifica )
                UtilItemEquipado.incluirItem( DadosLivroCarregado.getMapItem().
                          get(ItensMapeamento.ESPADA_MAGNIFICA.getIdItem()) );
        }
        else
            UtilItemEquipado.incluirItem(DadosLivroCarregado.getMapItem().
                    get(ItensMapeamento.ESPADA.getIdItem()));

        UtilItemEquipado.removerItem(ItensMapeamento.CENOURA.getIdItem());
        UtilBolsa.removerItem(ItensMapeamento.CENOURA.getIdItem());
    }


}
