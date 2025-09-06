package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_219 {

    public static void recebe1PontosDeSorteEDardoDePrata(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.DARDO_FEITO_DE_PRATA.getIdItem()));
        UtilPersonagem.recuperaSorte(1);
    }
}
