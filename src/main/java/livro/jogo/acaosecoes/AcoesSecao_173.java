package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_173 {

    public static void removerDaBolsaAPoeiraDaLevitacao(){
        UtilBolsa.removerItem(ItensMapeamento.POEIRA_DA_LEVITACAO.getIdItem());
    }
}
