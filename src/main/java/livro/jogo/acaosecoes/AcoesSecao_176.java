package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_176 {

    public static void recuperaCaboDoMarteloDeGuerra(){

        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES.getIdItem()) )
            UtilBolsa.incluirItem(ItensMapeamento.CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES);

    }
}
