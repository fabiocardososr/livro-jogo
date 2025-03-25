package livro.jogo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.personalizados.util.ListItem;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;

import java.util.ArrayList;
import java.util.HashMap;

public class UtilBolsa {
    private static final ArrayList<Item> bolsa = DadosLivroCarregado.getBolsa();
    private static final ArrayList<Item> itensEquipados = DadosLivroCarregado.getItensEquipados();


    /// Usado, por exemplo, para montar a lista de itens(combo).
    /// Exemplo seção 12 que pede uma lista e deve ser escolhido
    /// 2 itens para descartar
    public static ListItem[]  retornaListaDeBensNaBolsa(){
        //Recupera os itens da bolsa
        ArrayList<ListItem> listaDeItensNaBolsa = new ArrayList<>();
        Item item;
        for (int i=0; i<bolsa.size(); i++) {
            item = bolsa.get(i);

            //Não crio o objeto Icon diretamente, mas pela classe que redimensiona colocando n otalamnho que quero
            RedimensionarImagem imagem = new RedimensionarImagem(item.getEnderecoImagem(), 20, 20);
            ListItem itemDaLista = new ListItem(item.getIdItem(), item.getNome(), imagem.getImageIcon());
            listaDeItensNaBolsa.add(itemDaLista);
        }
        //Transformando o ARrayList em um simples Array
        return listaDeItensNaBolsa.toArray(new ListItem[0]);
    }

    public static boolean incluirItem(ItensMapeamento itemMapeado){
        try {
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(itemMapeado.getEnderecoJson()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void removerItem(int idItem){

        bolsa.removeIf(item -> item.getIdItem() == idItem);
    }

    //Verifica se o personagem possui determinado item na bolsa ou equipado
    public static boolean verificarExistenciaDeItemNaBolsaOuEquipado(int idItem){

        //Estando na bolsa
        for (Item item : bolsa){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }

        //Equipado
        for (Item item : itensEquipados){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }

        return false;
    }

    //Verifica se o personagem possui determinado item na bolsa
    public static boolean verificarExistenciaDeItemNaBolsa(int idItem){

        //Estando na bolsa
        for (Item item : bolsa){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }

        return false;
    }

    //Serve para popular uma lista dos itens para algum tipo de escolha
    // (por exemplo, secao 12 pede para escolher 2 itens e dar para o Gnomo)
    public static HashMap<String, Integer> listarNomesItensNaBolsa(){
        HashMap<String, Integer> listaNomesDeItens = new HashMap<>();

        for (Item item : bolsa){
            listaNomesDeItens.put(item.getNome(),item.getIdItem());
        }

        return listaNomesDeItens;
    }

    //Verifica se o personagem possui determinado item equipado
    public static boolean verificarExistenciaDeItemEquipado(int idItem){

        for (Item item : itensEquipados){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }
        return false;
    }

}
