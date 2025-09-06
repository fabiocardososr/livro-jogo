package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_163 {

    //Perde energia e retorna TRUE se o personagem ainda vivo
    public static boolean perde3PontosDeEnergia(){

        if ( !UtilPersonagem.personagemPerdeEnergia(3) )
        {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nUma nuvem escura e estranha apareceu e lançou um raio.\nVocê perdeu 3 pontos de energia."+
                    "\nVocê morreu. Sua aventura acaba aqui!");
            return false;
        }

        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
        return true;
    }
}
