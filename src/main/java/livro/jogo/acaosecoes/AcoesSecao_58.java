package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_58 {

    public static void ganha25OuroECabecaDoMarteloDosAnoes(){

        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem()) )
            UtilBolsa.incluirItem(ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES);

        UtilPersonagem.somarValorOuro(25);
    }

}
