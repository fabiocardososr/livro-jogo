package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_312 {

    public static void pegar25moedasDeOuro(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(25);
    }

    public static void ganharCabecaDeMartelo(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem()) )
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem()));
    }
}
