package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_145 {

    public static void perdeTodasAsProvisoes(){
        UtilBolsa.removerTodosItensEspecificado(ItensMapeamento.PROVISAO.getIdItem());
    }
}
