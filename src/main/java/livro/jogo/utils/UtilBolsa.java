package livro.jogo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.personalizados.util.ListItem;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;

import java.util.ArrayList;
import java.util.HashMap;

public class UtilBolsa {

    /// Usado, por exemplo, para montar a lista de itens(combo).
    /// Exemplo seção 12 que pede uma lista e deve ser escolhido
    /// 2 itens para descartar
    public static ListItem[]  retornaListaDeBensNaBolsa(){
        //Recupera os itens da bolsa
        ArrayList<ListItem> listaDeItensNaBolsa = new ArrayList<>();
        Item item;
        for (int i=0; i<DadosLivroCarregado.getBolsa().size(); i++) {
            item = DadosLivroCarregado.getBolsa().get(i);

            //Não crio o objeto Icon diretamente, mas pela classe que redimensiona colocando n otalamnho que quero
            RedimensionarImagem imagem = new RedimensionarImagem(item.getEnderecoImagem(), 20, 20);
            ListItem itemDaLista = new ListItem(item.getIdItem(), item.getNome(), imagem.getImageIcon());
            listaDeItensNaBolsa.add(itemDaLista);
        }
        //Transformando o ARrayList em um simples Array
        return listaDeItensNaBolsa.toArray(new ListItem[0]);
    }

    public static boolean incluirItem(Item item){
        try {
            DadosLivroCarregado.getBolsa().add(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Remove item da bolsa passando apenas o código do item
    public static void removerItem(int idItem){

        for (Item item : DadosLivroCarregado.getBolsa())
            if (item.getIdItem() == idItem) {
                DadosLivroCarregado.getBolsa().remove(item);
                break;
            }
    }

    //Remove TODOS os itens daquele (exemplo secao 145 perde todas as provisões) tipo da bolsa passando apenas o código do item
    public static void removerTodosItensEspecificado(int idItem){

        //O laço tem que ser do último para o início
        for (int i = DadosLivroCarregado.getBolsa().size() - 1; i >= 0; i--)
            if (DadosLivroCarregado.getBolsa().get(i).getIdItem() == idItem)
                DadosLivroCarregado.getBolsa().remove(i);
    }

    //Remove item passando o objeto item
    public static void removerItemEspecifico(Item item){

        //Estando na bolsa
        for (Item it : DadosLivroCarregado.getBolsa()){
            if ( it.getIdItem() == item.getIdItem() ){
                DadosLivroCarregado.getBolsa().remove(it);
                break;
            }
        }



    }


    //Verifica se o personagem possui determinado item na bolsa ou equipado
    public static boolean verificarExistenciaDeItemNaBolsaOuEquipado(int idItem){

        //Estando na bolsa
        for (Item item : DadosLivroCarregado.getBolsa()){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }

        //Equipado
        for (Item item : DadosLivroCarregado.getItensEquipados()){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }

        return false;
    }

    //Verifica se o personagem possui determinado item na bolsa
    public static boolean verificarExistenciaDeItemNaBolsa(int idItem){

        //Estando na bolsa
        for (Item item : DadosLivroCarregado.getBolsa()){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }

        return false;
    }

    //Verifica se o personagem possui determinado item e o retorna ao invés de true ou false
    public static Item verificarExistenciaDeItemNaBolsaERetornaItem(int idItem){

        //Estando na bolsa
        for (Item item : DadosLivroCarregado.getBolsa()){
            if ( item.getIdItem() == idItem ){
                return item;
            }
        }

        return null;
    }

    //Serve para popular uma lista dos itens para algum tipo de escolha
    // (por exemplo, secao 12 pede para escolher 2 itens e dar para o Gnomo)
    public static HashMap<String, Integer> listarNomesItensNaBolsa(){
        HashMap<String, Integer> listaNomesDeItens = new HashMap<>();

        for (Item item : DadosLivroCarregado.getBolsa()){
            listaNomesDeItens.put(item.getNome(),item.getIdItem());
        }

        return listaNomesDeItens;
    }

    //Verifica se o personagem possui determinado item equipado
    public static boolean verificarExistenciaDeItemEquipado(int idItem){

        for (Item item : DadosLivroCarregado.getItensEquipados()){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }
        return false;
    }

    public static int quantidadeDeItensNaBolsa(){
        return DadosLivroCarregado.getBolsa().size();
    }

}
