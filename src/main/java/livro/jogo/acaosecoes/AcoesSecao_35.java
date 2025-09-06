package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_35 {

    //Nesta seção o personagem perde 4 pontos de energia
    public static boolean perde4PontosDeEnergiaEverificaSePersonagemVivo(){
        if ( !UtilPersonagem.personagemPerdeEnergia(4) )
        {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nO ferimento causado pela queimadura foi muito grave, sua energia chegou a zero.\n\n"+
                    "Sua aventura acaba aqui.");
            return false;
        }

        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
        return true;
    }
}
