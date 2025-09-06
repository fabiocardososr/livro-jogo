package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_389 {

    public static void abrirArca(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);
        UtilPersonagem.recuperaSorte(1);
        UtilPersonagem.somarValorOuro(8);

        //Existe as seções 201,248 e 293 que chamam a seção 389(atual)
        //e preciso garantir que só vai abrir uma vez a arca
        DadosLivroCarregado.getInfoSecoes().setAbriuArcaSecao389(true);
    }
}
