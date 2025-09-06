package livro.jogo.acaosecoes;

import livro.jogo.entidades.Personagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

public class AcoesSecao_308 {

    public static void efeitoAoComerCogumelo(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/troca.mp3", null);
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        int auxHabilidade   = personagem.getHabilidadeAtual();
        int auxSorte        = personagem.getSorteAtual();

        personagem.setHabilidadeAtual(auxSorte);
        personagem.setSorteAtual(auxHabilidade);
    }
}
