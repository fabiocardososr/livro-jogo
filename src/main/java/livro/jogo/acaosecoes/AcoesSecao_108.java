package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_108 {

    //Perde energia e retorna TRUE se o personagem ainda vivo
    public static boolean perde3PontosDeEnergia(){

        if ( !UtilPersonagem.personagemPerdeEnergia(3) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\ndevido ao seu corpo que já estava debilitado e a dor insuportável, seu corpo não suporta tamanho sofrimento."+
                    "\n\nSua aventura acaba aqui!");
            return false;
        }

        return true;
    }
}
