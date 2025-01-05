package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;

import java.util.ArrayList;

public class AcoesDasSecaoEEfeitoDeItens {
    private Personagem personagem = ManipularDadosLivro.getPersonagem();

    //Aqui são codificados todos os efeitos dos itens
    public static void acoesDosItens(Item item){

        switch(item.getIdItem()) {
            case 45: efeitoItem45(item); //Poção de Habilidade
                break;
            case 49: efeitoItem49(item); //Poção de Habilidade
                break;
            default: //Não faça nada
        }
    }

    //Poção de Habilidade inicial
    private static void efeitoItem45(Item item){
        System.out.println(item);
    }

    //Provisão
    private static void efeitoItem49(Item item){
        System.out.println(item);
    }

    //Retorna a quantidade de provisões que estão na bolsa
    public int quantidadeProvisoesRestantes(){
        int quantidadeProvisoes = 0;
        ArrayList<Item> itens = personagem.getBolsa();

        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.PROVISAO.getIdItem() )
                ++quantidadeProvisoes;

        return quantidadeProvisoes;
    }

    //Retorna a quantidade de provisões que estão na bolsa
    private void removerProvisaoDaBolsa(){

    }

    public void tratarQuandoPersonagemComeUmaProvisao(){
        ArrayList<Item> itens = personagem.getBolsa();

        //Remover provisdão da bolsa
        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.PROVISAO.getIdItem() ) {
                System.out.println(item.getDescricao());
                itens.remove(item);
                break;
            }

        //tratar aumento da energia REGRA: 4 PONTOS DE ENERGIA RECUPERADA
        //Caso cheio informar jogador?
    }

    public static ArrayList<Item> retornaItensDaBolsa(){
        return ManipularDadosLivro.getBolsa();
    }



}
