package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;

import java.util.ArrayList;

public class EfeitoDeItens {
    private final Personagem personagem;

    public EfeitoDeItens(Personagem personagem) {
        this.personagem = personagem;
    }

    //Aqui são codificados todos os efeitos dos itens
    public void acoesDosItens(int idItem){

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
    private void efeitoItem49Provisao(){
        ArrayList<Item> itens = personagem.getBolsa();

        //Remover provisão da bolsa
        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.PROVISAO.getIdItem() ) {
                System.out.println(item.getNome());
                itens.remove(item);
                break;
            }
        //tratar aumento da energia REGRA:: 4 PONTOS DE ENERGIA RECUPERADA
        //Caso jogador com energia cheia (nível máximo), não faça nada e informe ao usuario
        //Faça uma maneira de não chamar a tela de mensagem aqui, passe alguma informação que seja tratada
        //na tela de secao de modo a não misturar back end com as telas (lembre-se que poderá ser feito para web)
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

    public static ArrayList<Item> retornaItensDaBolsa(){
        return ManipularDadosLivro.getBolsa();
    }



}
