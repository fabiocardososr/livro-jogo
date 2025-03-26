package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_57 {
    public static boolean testarSorte(){
        boolean sorte;

        //Animação de dados rolando
        Util.mostrarAnimacaoDadosRolando();

        sorte = UtilPersonagem.testarSorte();

        if ( sorte )
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        else
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);

        return sorte;

    }
}
