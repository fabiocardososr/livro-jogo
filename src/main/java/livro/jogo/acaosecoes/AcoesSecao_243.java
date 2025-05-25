package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_243 {

    public static boolean verificaSePossuiMoedas(){
        return DadosLivroCarregado.getPersonagem().getQuantidadeOuro() > 0;
    }

    public static void doarMoedaAoFrade(){
        UtilPersonagem.reduzirValorOuro(1);
    }
}
