package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_133 {

    public static void colocaAnelDaLentidaoNoDedo(){
        UtilPersonagem.equiparDesequiparItem(DadosLivroCarregado.getMapItem().
                get(ItensMapeamento.ANEL_DA_LENTIDAO.getIdItem()));
    }
}
