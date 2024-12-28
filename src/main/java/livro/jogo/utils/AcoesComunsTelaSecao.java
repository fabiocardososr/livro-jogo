package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AcoesComunsTelaSecao {
    private Personagem personagem;

    public AcoesComunsTelaSecao(Personagem personagem) {
        this.personagem = personagem;
    }

    public void tomarPocao(Item item){
        //Coloque aqui todo o código de tomar a poção

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
}
