package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_345 {

    //Perde energia e retorna TRUE se o personagem ainda vivo
    public static boolean perde4PontosDeEnergia(){

        if ( !UtilPersonagem.personagemPerdeEnergia(4) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nO fungo venenoso fez estrago em seu organismo, seu corpo não suporta tamanho sofrimento."+
                    "\n\nSua aventura acaba aqui!");
            return false;
        }

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        return true;
    }
}
