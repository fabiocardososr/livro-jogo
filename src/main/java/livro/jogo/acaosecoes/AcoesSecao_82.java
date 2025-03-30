package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_82 {

    public static boolean possuiPocaoDaImobilidade(){
        return UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.POCAO_DA_IMOBILIDADE.getIdItem());
    }
}
