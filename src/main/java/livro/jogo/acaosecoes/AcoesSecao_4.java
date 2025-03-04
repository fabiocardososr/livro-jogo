package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilItens;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_4 {

    //Nesta seção o persongagem perde 4 pontos de energia e retorna true se estiver vivo
    public static boolean perde4PontosDeEnergia(){

        return UtilPersonagem.personagemPerdeEnergia(4);

    }

}
