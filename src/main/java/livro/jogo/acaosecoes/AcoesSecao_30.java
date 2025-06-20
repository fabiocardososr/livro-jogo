package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_30 {

    //Nesta seção o personagem perde 2 pontos de energia
    public static boolean perde2PontosDeEnergiaEverificaSePersonagemVivo(){
        if ( !UtilPersonagem.personagemPerdeEnergia(2) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem("Devido ao profundo corte na sua testa, você foi ferido gravemente, sua energia chegou a zero.\n\n"+
                    "Sua aventura acaba aqui.");
            return false;
        }

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        return true;
    }
}
