package livro.jogo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;

import java.util.ArrayList;

public class UtilItemEquipado {
    private static final ArrayList<Item> itensEquipados = DadosLivroCarregado.getItensEquipados();

    public static void incluirItem(ItensMapeamento itemMapeado){
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(itemMapeado.getEnderecoJson()));
    }

    public static void removerItem(int idItem){

        itensEquipados.removeIf(item -> item.getIdItem() == idItem);
    }
}
