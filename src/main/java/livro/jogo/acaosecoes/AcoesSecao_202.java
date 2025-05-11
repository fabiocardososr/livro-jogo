package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_202 {

    public static void colocaNaBosaBrincosDeOuroDaMulherGato(){
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.BRINCOS_DE_OURO.getIdItem()) )
            UtilBolsa.incluirItem(ItensMapeamento.BRINCOS_DE_OURO);
    }
}
