package livro.jogo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;

import java.util.ArrayList;

public class UtilItemEquipado {
    private static final ArrayList<Item> itensEquipados = DadosLivroCarregado.getItensEquipados();

//    public static void incluirItem(ItensMapeamento itemMapeado){
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(itemMapeado.getEnderecoJson()));
//    }

    public static void removerItem(int idItem){

        itensEquipados.removeIf(item -> item.getIdItem() == idItem);
    }

    public static boolean verificaSeItemEquipado(int idItem){

        for (Item item : itensEquipados){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }

        return false;
    }

    public static boolean incluirItem(Item item){
        try {
            itensEquipados.add(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Item verificaSeItemEquipadoERetornaItem(int idItem){

        for (Item item : itensEquipados){
            if ( item.getIdItem() == idItem ){
                return item;
            }
        }

        return null;
    }
}
