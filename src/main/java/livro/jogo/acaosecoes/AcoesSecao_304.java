package livro.jogo.acaosecoes;

import livro.jogo.utils.Util;
import livro.jogo.utils.UtilItens;

public class AcoesSecao_304 {


    //Retorna true se o personagem possui ao menos 5 provisões para passar para a próxima seção
    public static boolean verificaSePossuiProvisoes(){

        if (UtilItens.quantidadeProvisoesRestantes() >= 5 )
            return true;

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        return false;
    }
}
