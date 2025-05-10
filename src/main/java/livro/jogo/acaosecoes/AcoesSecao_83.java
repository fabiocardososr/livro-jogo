package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_83 {

    //Perde energia e retorna TRUE se o personagem ainda vivo
    public static boolean perde2PontosDeEnergia(){

        if ( !UtilPersonagem.personagemPerdeEnergia(2) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nDevido ao seu corpo que já estava debilitado, o veneno da Beladona foi mortal para seu organismo.\nVocê morreu!"+
                    "\n\nSua aventura acaba aqui!");
            return false;
        }

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        return true;
    }

    //Retirar a Beladona da bolsa (foi consumida)
    public static void consumirBeladona(){
        UtilBolsa.removerItem(ItensMapeamento.BELADONA.getIdItem());
    }
}
