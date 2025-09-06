package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_78 {

    public static void incluirNaBolsaPoeiraDaLevitacao(){
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.POEIRA_DA_LEVITACAO.getIdItem()));
    }

}
