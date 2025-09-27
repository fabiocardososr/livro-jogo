package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

public class AcoesSecao_308 {

    public static void efeitoAoComerCogumelo(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/troca.mp3", null);

        int auxHabilidade   = DadosLivroCarregado.getPersonagem().getHabilidadeAtual();
        int auxSorte        = DadosLivroCarregado.getPersonagem().getSorteAtual();

        DadosLivroCarregado.getPersonagem().setHabilidadeAtual(auxSorte);
        DadosLivroCarregado.getPersonagem().setSorteAtual(auxHabilidade);
    }
}
