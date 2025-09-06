package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_277 {

    //Perde energia e retorna TRUE se o personagem ainda vivo
    public static boolean perde1PontosDeEnergia(){

        if ( !UtilPersonagem.personagemPerdeEnergia(1) )
        {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nO golpe da árvore com o galho foi fatal para você."+
                    "\n\nSua aventura acaba aqui!");
            return false;
        }

        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
        return true;
    }
}
