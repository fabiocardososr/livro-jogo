package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;

import java.util.ArrayList;

public class EfeitoDeItens {

//    public EfeitoDeItens(Personagem personagem) {
//        this.personagem = personagem;
//    }

    //Aqui são codificados todos os efeitos dos itens
    public static void acoesDosItens(int idItem){

        switch(idItem) {
            case 45: efeitoItem45(); //Poção de Habilidade
                break;
            case 49: efeitoItem49Provisao(); //Provisões (comida)
                break;
            default: //Não faça nada
        }
    }

    //Poção de Habilidade inicial
    private static void efeitoItem45(){
        System.out.println("Poção de Habilidade!");
    }

    //Provisão
    private static void efeitoItem49Provisao(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();
        ArrayList<Item> itens = personagem.getBolsa();


        //Achar um item do tipo provisão e removê-lo da bolsa
        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.PROVISAO.getIdItem() ) {
                itens.remove(item);
                break;
            }

        //Recuperando o índice atual e o índice máximo em que o personagem pode chegar
        var indiceAtual   = personagem.getEnergiaAtual();
        var indiceMaximo  = personagem.getEnergiaMax();

        //reposição de 4 pontos de energia do personagem
        var indiceAtualComReposicao = indiceAtual + 4;

        //Com a reposição ultrapassando o índice máximo, o índice atual recebe máximo
        if (indiceAtualComReposicao > indiceMaximo)
            indiceAtualComReposicao = indiceMaximo;

        //Atualizando índice
        personagem.setEnergiaAtual(indiceAtualComReposicao);
    }

    //Retorna a quantidade de provisões que estão na bolsa
    private void removerProvisaoDaBolsa(){

    }

    public static ArrayList<Item> retornaItensDaBolsa(){
        return DadosLivroCarregado.getBolsa();
    }



}
