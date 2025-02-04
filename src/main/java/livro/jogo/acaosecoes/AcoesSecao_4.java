package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

public class AcoesSecao_4 {

    //Nesta seção o persongagem perde 4 pontos de energia e retorna true se estiver vivo
    public static boolean perde4PontosDeEnergia(){

        return Util.perdeEnergia(4);

    }

}
