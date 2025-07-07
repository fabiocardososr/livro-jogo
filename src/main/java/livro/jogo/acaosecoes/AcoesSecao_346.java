package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_346 {

    public static boolean possui10MoedasEMaisQue2Itens(){
        if ( (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() >= 10) &&
                (UtilBolsa.quantidadeDeItensNaBolsa() >= 2) )
            return true;

        return false;
    }
}
