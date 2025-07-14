package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_383 {

    public static void ganha2moedasEApito(){

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.APITO_DE_MADEIRA.getIdItem()) )
            UtilBolsa.incluirItem(ItensMapeamento.APITO_DE_MADEIRA);

        UtilPersonagem.somarValorOuro(2);
    }

    public static void ganha2PontosDeEnergia(){
        UtilPersonagem.recuperaEnergia(2);
    }
}
