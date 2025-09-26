package livro.jogo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;

import java.util.ArrayList;

public class UtilItemEquipado {

    public static void removerItem(int idItem){

        DadosLivroCarregado.getItensEquipados().removeIf(item -> item.getIdItem() == idItem);
    }

    public static boolean verificaSeItemEquipado(int idItem){

        for (Item item : DadosLivroCarregado.getItensEquipados()){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }

        return false;
    }

    public static boolean incluirItem(Item item){
        try {
            DadosLivroCarregado.getItensEquipados().add(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Item verificaSeItemEquipadoERetornaItem(int idItem){

        for (Item item : DadosLivroCarregado.getItensEquipados()){
            if ( item.getIdItem() == idItem ){
                return item;
            }
        }

        return null;
    }
}
