package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_16 {
    private static boolean consumiuPocaoAntiveneno;

    public static boolean isConsumiuPocaoAntiveneno() {
        return consumiuPocaoAntiveneno;
    }

    //Seta variável para informar que a poção foi consumida. Essa informação é necessária para liberar o botão
    public static boolean consumiuPocaoAntiveneno() {
        consumiuPocaoAntiveneno = true;
        UtilBolsa.removerItem( ItensMapeamento.POCAO_ANTIVENENO.getIdItem() );

        return consumiuPocaoAntiveneno;
    }

    //Pegar a Poção Antiveneno(3) da bolsa
    public static boolean opcao_1(){

        //Se consumiu a Pocao Antiveneno
        if ( isConsumiuPocaoAntiveneno() )
            return true;

        //Se possui a Poção Antiveneno, mas encontra-se na bolsa, informa que ele deve ser equipado antes
        if ( UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.POCAO_ANTIVENENO.getIdItem()) )
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ",\nvocê possui o item. Beba-o.\n\nProcure-o na sua bolsa.");
        else
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ",\nvocê não possui uma poção Antiveneno.");

        return false;
    }

}
