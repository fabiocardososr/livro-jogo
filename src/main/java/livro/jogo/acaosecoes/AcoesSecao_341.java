package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_341 {

    public static void pegar5moedasDeOuro(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.somarValorOuro(5);
    }

    public static void ganha1PontoDeSorte(){
        UtilPersonagem.recuperaSorte(1);
    }

    public static void ganharDenteDeDragaoEPocaoDaForca(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);

        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.DENTE_DE_DRAGAO.getIdItem())) {
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.DENTE_DE_DRAGAO.getIdItem()));
        }

        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.POCAO_DA_FORCA.getIdItem())) {
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.POCAO_DA_FORCA.getIdItem()));
        }
    }
}
