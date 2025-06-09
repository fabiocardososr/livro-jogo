package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilItens;
import livro.jogo.utils.UtilPersonagem;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AcoesSecao_354 {

    public static void ganhaPoeiraDaLevitacao(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);

        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.POEIRA_DA_LEVITACAO.getIdItem()) )
            UtilBolsa.incluirItem(UtilItens.retornaItem(11));
    }
}
