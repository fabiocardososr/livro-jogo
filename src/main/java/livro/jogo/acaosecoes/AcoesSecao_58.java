package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_58 {

    public static void ganha25OuroECabecaDoMarteloDosAnoes(){

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem()) )
            UtilBolsa.incluirItem(ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES);

        UtilPersonagem.somarValorOuro(25);
    }

    public static void removerDaBolsaAguaBenta(){
        UtilBolsa.removerItem(ItensMapeamento.AGUA_BENTA.getIdItem());
    }

}
