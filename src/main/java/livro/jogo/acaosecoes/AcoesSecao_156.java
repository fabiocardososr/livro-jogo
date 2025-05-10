package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_156 {

    //Perde energia e retorna TRUE se o personagem ainda vivo
    public static boolean perde3PontosDeEnergia(){

        if ( !UtilPersonagem.personagemPerdeEnergia(3) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nSaltar 5 metros ao chão não foi uma boa ideia. A queda foi mortal para você!"+
                    "\n\nSua aventura acaba aqui!");
            return false;
        }
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        return true;
    }
}
