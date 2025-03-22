package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_45 {

    //Nesta seção o personagem perde 2 pontos de energia
    public static boolean perde2PontosDeEnergiaEverificaSePersonagemVivo(){
        if ( !UtilPersonagem.personagemPerdeEnergia(2) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nO ferimento em sua coxa é gravíssimo, atingiu a veia femoral.\nO dano foi mortal."+
                    "\nSua aventura acaba aqui!");
            return false;

        }

        return true;
    }

}
