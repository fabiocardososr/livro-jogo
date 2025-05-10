package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_194 {

    public static void ganhaBeladona(){

        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.BELADONA.getIdItem()) )
            UtilBolsa.incluirItem(ItensMapeamento.BELADONA);

        UtilPersonagem.somarValorOuro(25);
    }
}
