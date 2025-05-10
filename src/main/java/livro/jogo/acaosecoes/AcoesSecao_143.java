package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_143 {

    //Perde energia e retorna TRUE se o personagem ainda vivo
    public static boolean perde2PontosDeEnergia(){

        if ( !UtilPersonagem.personagemPerdeEnergia(2) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nSua imprudência em cortar um fungo desconhecido o levou a sufocar até a morte."+
                    "\n\nSua aventura acaba aqui!");
            return false;
        }

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        return true;
    }
}
