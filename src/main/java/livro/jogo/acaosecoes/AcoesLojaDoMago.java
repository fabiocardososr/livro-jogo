package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.telas.desktop.personalizados.util.ListItem;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;

import java.util.ArrayList;

public class AcoesLojaDoMago {

    /// Usado para montar a lista de itens no JList dos itens à venda do Mago(seção 261).
    public static ListItem[]  retornaListaDeItensAVenda(){

        //Cria array
        ArrayList<ListItem> listaDeItensAVenda = new ArrayList<>();

        /*** Recupera os itens à venda que são fixos (encontrados listados na seção 261) ***/

        //Poção Curativa
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(27)) );

        //Poção de Controle das Plantas
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(8)) );

        //Poção da Imobilidade
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(13)) );

        //Poção de Controle dos Insetos
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(16)) );

        //Poção Antiveneno
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(3)) );

        //Água Benta
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(7)) );

        //Anel de Luz
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(1)) );

        //Botas Saltadoras
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(31)) );

        //Corda de Escalada
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(17)) );

        //Rede de Aprisionamento
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(32)) );

        //Braçadeira da Força
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(12)) );

        //Luva de Destreza de Arremesso
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(22)) );

        //Vara de Encontrar Água
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(34)) );

        //Cabeças de Alho
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(35)) );

        //Fita de Cabeça da Concentração
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(23)) );

        //Cápsulas de Fogo
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(19)) );

        //Filtros Nasais
        listaDeItensAVenda.add( criaItemDaLista(DadosLivroCarregado.getMapItem().get(4)) );


        //Transformando o ArrayList em um simples Array
        return listaDeItensAVenda.toArray(new ListItem[0]);
    }

    //Não crio o objeto Icon diretamente, mas pela classe que redimensiona colocando no tamanho que quero
    private static ListItem criaItemDaLista(Item item){
        RedimensionarImagem imagem = new RedimensionarImagem(item.getEnderecoImagem(), 20, 20);
        return new ListItem(item.getIdItem(), item.getNome(), imagem.getImageIcon(), item.getValorCusto());
    }
}
