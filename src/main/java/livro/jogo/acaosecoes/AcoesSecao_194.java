package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilItens;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_194 {

    public static void ganhaBeladona(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);

        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.BELADONA.getIdItem()) )
            UtilBolsa.incluirItem(UtilItens.retornaItem(ItensMapeamento.BELADONA.getIdItem()));
    }
}
