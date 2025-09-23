package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_116 {

    public static void pegarColeiraDeCouroComAplicacoesEmOuro(){
        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.COLEIRA_DE_COURO_COM_APLICACAO_EM_OURO.getIdItem())) {
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.COLEIRA_DE_COURO_COM_APLICACAO_EM_OURO.getIdItem()));
        }
    }

}
