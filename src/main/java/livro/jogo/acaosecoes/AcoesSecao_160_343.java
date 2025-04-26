package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_160_343 {

    public static boolean verificaSePossuiOuro(){

        if (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() > 0)
            return true;

        return false;
    }

    public static void pagar1MoedaDeOuroAoCorvo(){
        UtilPersonagem.reduzirValorOuro(1);
    }
}
