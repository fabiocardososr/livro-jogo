package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;

import java.util.ArrayList;
import java.util.Random;

public class AcoesSecao_36 {

    ///Escolhe entre ouro ou 2 itens
    ///sendo ouro, perde tudo
    ///sendo itens faço uma escolha aleatória excluíndo os itens (EXCETO):
    /// "25- Cabo do Martelo de Guerra dos Anões" e
    /// "6 - Cabeça de martelo de bronze com a letra G"
    public static String ladraoRouba(){
        ArrayList<Item> bolsa = DadosLivroCarregado.getBolsa();

        int quantidadeItensBolsa = 0;
        String mensagem = "";

        //Recuperar a quantidade de itens na bolsa.
        //Os itens objetivos do jogo não contam, pois não serão removidos
        for (Item item : bolsa){
            if ( ( item.getIdItem() != ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem())
                    && ( item.getIdItem() != ItensMapeamento.CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES.getIdItem() ) ){
                ++quantidadeItensBolsa;
            }
        }

        //Faça um sorteio entre ouro e itens caso possuam ambos os objetos
        //1 - ouro; 2 - itens
        if ( (quantidadeItensBolsa >= 2) && (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() > 0) ){
            var resultado = 1 + new Random().nextInt(2 );
            switch (resultado){
                case 1 -> mensagem = roubadoOuro();
                case 2 -> mensagem = remover2Itens(bolsa,quantidadeItensBolsa);
            }
        } else if (quantidadeItensBolsa >= 2) {
            mensagem = remover2Itens(bolsa,quantidadeItensBolsa);
        } else if (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() > 0) {
            mensagem = roubadoOuro();
        } else{
            mensagem = removerItem(bolsa,quantidadeItensBolsa);
        }

        return mensagem;
    }

    public static String remover2Itens(ArrayList<Item> bolsa, int qtdItensSemAsExcecoes) {
        var contRemoveItens = 2;    //Será decrementado até que os dois itens sejam escolhidos
        Item item;
        var indiceItemNaBolsaEscolhido =-1;
        String itensRemovidos = ""; //String com os nomes do que foi removido para que seja mostrado na tela

        //Garante que não entrará em loop infinito no while logo abaixo
        if ( qtdItensSemAsExcecoes < 2 )
            return "";

        while (contRemoveItens > 0){
            indiceItemNaBolsaEscolhido = new Random().nextInt( bolsa.size() );
            item = bolsa.get(indiceItemNaBolsaEscolhido);

            if ((item.getIdItem() != ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem())
                    && (item.getIdItem() != ItensMapeamento.CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES.getIdItem())){

                if (itensRemovidos.isEmpty())
                    itensRemovidos = item.getNome();
                else
                    itensRemovidos = itensRemovidos+", "+item.getNome();

                bolsa.remove(item);
                --contRemoveItens;
            }
        }

        return itensRemovidos;
    }

    //Se, excetuando os itens do objetivo, não tiver 2 ou mais para serem removidos. Remova 1 item se puder
    private static String removerItem(ArrayList<Item> bolsa, int qtdItensSemAsExcecoes) {
        var contRemoveItens = 1;    //Será decrementado até que pelo menos 1 item
        var indiceItemNaBolsaEscolhido = 0;
        String itensRemovidos = ""; //String com os nomes do que foi removido para que seja mostrado na tela
        Item item;

        if (qtdItensSemAsExcecoes == 0)
            return itensRemovidos;

        for(Item it : bolsa)
            if ((it.getIdItem() != ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem())
                    && (it.getIdItem() != ItensMapeamento.CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES.getIdItem())){
                itensRemovidos = it.getNome();
                bolsa.remove(it);
                break;
            }

        return itensRemovidos;
    }

    private static String roubadoOuro() {
        int valorEmOuroRoubado = DadosLivroCarregado.getPersonagem().getQuantidadeOuro();
        DadosLivroCarregado.getPersonagem().setQuantidadeOuro(0);
        return valorEmOuroRoubado+" peça(s) de ouro.";
    }
}
