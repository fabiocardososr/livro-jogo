package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_50 {

    //O personagem pega a chave de prata
    public static void incluirChaveDePrataNaBolsa(){
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.CHAVE_DE_PRATA.getIdItem()));
    }


}
