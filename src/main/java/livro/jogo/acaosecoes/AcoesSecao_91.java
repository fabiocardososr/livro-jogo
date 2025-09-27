package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_91 {

    public static void ganha1PontoDeSorte(){
        UtilPersonagem.recuperaSorte(1);
    }

    public static void achaEequipaColarOlhoDeAmbar(){

        //Recuperando o colar do hashMap montado no carregamento do jogo
        Item item = DadosLivroCarregado.getMapItem().get(ItensMapeamento.COLAR_OLHO_DE_AMBAR.getIdItem());

        //Equipar o colar
        UtilPersonagem.equiparDesequiparItem(item);
    }
}
