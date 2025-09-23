package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_58 {

    public static void ganha25OuroECabecaDoMarteloDosAnoes(){

        //Se já existir não deixe incluir
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem()) ) {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem()));
        }

        UtilPersonagem.somarValorOuro(25);
    }

    public static void removerDaBolsaAguaBenta(){
        UtilBolsa.removerItem(ItensMapeamento.AGUA_BENTA.getIdItem());
    }

}
